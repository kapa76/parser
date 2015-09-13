package org.parser.persistence.kernel.factory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parser.common.HtmlVacPage;
import org.parser.common.ParsedVacancies;
import org.parser.persistence.kernel.ServiceHH;
import org.parser.persistence.model.*;
import org.parser.persistence.repository.hibernate.*;
import org.parser.threads.ThreadVacancy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
@Repository
@Transactional
public class ServiceHHImpl implements ServiceHH {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceHHImpl.class);

    private final String START_PAGE = "http://hh.ru/search/vacancy?text=&salary=&currency_code=RUR&experience=doesNotMatter&order_by=relevance&search_period=30&items_on_page=20&no_magic=true";

    private int SYSTEM_TYPE = 0;
    private int VACANCY_TYPE = 0;
    private int RESUME_TYPE = 1;
    private String PREFIX = "http://hh.ru";

    //private HttpClient client;

    private ExecutorService threads = Executors.newCachedThreadPool();
    private int sizePool = 20;
    private int qty_queue = 0;

    @Autowired
    private VacancyRepository vacancyService;

    @Autowired
    public HistoryRepository historyRepository;

    @Autowired
    public HistoryLoadRepository historyLoadRepository;

    @Autowired
    private TaskLinkProcessedRepositoy taskLinkProcessedRepositoy;

    @Autowired
    private SearchWordRepository searchWordRepository;

    @Autowired
    private WordListRepository wordListRepository;

    private String get_html_by_link(String url) throws IOException {
        String content = "";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        int returnCode = response.getStatusLine().getStatusCode();
        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        }
        return content;
    }

    @Override
    public void startVacancy() throws IOException {
        try {
            if (!processTask()) {
                //если все обработано начинаем новый поиск
                buildListVacancy();
            }
        } catch(InterruptedException e){
            logger.debug("Interrupt exception: " + e.getMessage());
        }
    }

    private boolean processTask() throws IOException, InterruptedException {
        boolean value = false;
        //если нет не обработанных вакансий то начинаем поиск заново.
        boolean processed = false;
        List<TaskLink> taskLinkListNotProcessed = new ArrayList<>();
        taskLinkListNotProcessed = taskLinkProcessedRepositoy.getNotProcessedLink(0);
        if (taskLinkListNotProcessed != null && taskLinkListNotProcessed.size() > 0) {
            processed = true;
        }

        int processListSize = taskLinkListNotProcessed.size();
        int processedQty = 0;
        while(processed){
            //for (TaskLink task : taskLinkListNotProcessed) {

                List<ThreadVacancy> localTh = new ArrayList<ThreadVacancy>();

                for(int i = 0; i < sizePool; i++) {

                    ThreadVacancy vacReader = new ThreadVacancy(new String(taskLinkListNotProcessed.get(processedQty).getHtml()), taskLinkListNotProcessed.get(processedQty));

                    vacReader.setVacancyService(vacancyService);
                    vacReader.setTaskLinkProcessedRepositoy(taskLinkProcessedRepositoy);
                    vacReader.setHistoryRepository(historyRepository);
                    vacReader.setHistoryLoadRepository(historyLoadRepository);

                    localTh.add(vacReader);
                    processedQty++;
                    if(processedQty == processListSize - 1) {

                        processed = false;
                        break;
                    }
                }

                List<Future<String>> answers = threads.invokeAll(localTh);

                //корень поиска - начальная страница со всеми списками
                /* start_vacancy_load(new String(task.getHtml()), task);
                task.setProcessed(true);
                taskLinkProcessedRepositoy.update(task); */

            value = true;
        }

        return value;
    }

    private void buildListVacancy() throws IOException {
        if (!searchWordRepository.isExist(SYSTEM_TYPE)) {
            buildListForSearch();
        }

        //есть заполненные слова для поиска профессии и отрасли
        List<SearchWords> searchWords = searchWordRepository.findAll(SYSTEM_TYPE);
        Map<String, List<WordList>> mapSeach = new HashMap<>();

        //получили мапу для построения запросов поиска
        for (SearchWords sword : searchWords) {
            List<WordList> ll = wordListRepository.findWordByIdSearch(sword.getId());
            mapSeach.put(sword.getWord_name(), ll);
        }

        for (String key : mapSeach.keySet()) {
            List<WordList> ll = mapSeach.get(key);
            for (WordList value : ll) {

                String htmlUrl = startFindBySearchWords(key, value.getName());
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(htmlUrl);
                HttpResponse response = client.execute(request);

                int returnCode = response.getStatusLine().getStatusCode();
                if (returnCode == 200) {
                    String htmlPage = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                    saveSearchPageToTaskList(htmlPage, htmlUrl, VACANCY_TYPE);
                }
            }
        }
    }

    private void saveSearchPageToTaskList(String htmlPage, String link, int type) {
        TaskLink tlink = new TaskLink();
        tlink.setHtml(htmlPage.getBytes());
        tlink.setLink(link);
        tlink.setProcessed(false);
        tlink.setRecordType(type);
        tlink.setSystemType(SYSTEM_TYPE);
        Document doc = Jsoup.parse(htmlPage);
        String company_name = doc.select("div.resumesearch__result-count").text();
        company_name = company_name.replaceAll(" ", "").replaceAll("\\W", "");
        Integer qty = Integer.parseInt(company_name);
        tlink.setQty_entity(qty);
        taskLinkProcessedRepositoy.create(tlink);
    }

    private String startFindBySearchWords(String key, String value) {
        return "http://hh.ru/search/vacancy?items_on_page=100&" + key + "=" + value;
    }

    private void buildListForSearch() throws IOException {
        //получение списка отраслей для фильтрации

        String content = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.hh.ru/industries");
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

            Gson gson = new Gson();
            JsonElement obj = gson.fromJson(content, JsonElement.class);

            JsonArray ar = obj.getAsJsonArray();
            List<String> listNumberIndustry = new ArrayList<>();
            Iterator<JsonElement> itemRoot = ar.iterator();
            SearchWords sw = new SearchWords("industry", SYSTEM_TYPE);
            searchWordRepository.create(sw);
            while (itemRoot.hasNext()) {
                JsonElement elem = itemRoot.next();
                listNumberIndustry.add(elem.getAsJsonObject().get("id").getAsString());  //корень забрали

                JsonArray subRoot = elem.getAsJsonObject().get("industries").getAsJsonArray();
                Iterator<JsonElement> item = subRoot.iterator();

                while (item.hasNext()) {
                    JsonElement e = item.next();
                    String name = e.getAsJsonObject().get("id").getAsString();
                    WordList wl = new WordList(name, sw.getId());
                    wordListRepository.create(wl);
                }
            }
        }
    }

    private ParsedVacancies parse_page(String htmlLoaded) throws IOException {

        Document doc = Jsoup.parse(htmlLoaded);
        ParsedVacancies parsedElems = new ParsedVacancies(doc);

        Elements elems = doc.select("div.search-result-item");

        parsedElems.setSize(elems.size());
        Iterator<Element> iterator = elems.iterator();
        Integer qty = 0;
        while (iterator.hasNext()) {
            Element elem = iterator.next();
            String vacancyPageUrl = elem.select("div.search-result-item__head").first().getElementsByAttribute("href").first().attr("href");
            String contentPage = get_html_by_link(vacancyPageUrl);
            try {
                parse_html_page(contentPage, vacancyPageUrl);
                qty++;
                parsedElems.setQty(qty);
            } catch (Exception e) {
                logHistory(e.getMessage(), vacancyPageUrl, SYSTEM_TYPE, VACANCY_TYPE);
                logger.debug(e.getMessage());
            }
        }
        return parsedElems;
    }

    private List<HtmlVacPage> parse_page_test(String htmlLoaded, String link) throws IOException {
        List<HtmlVacPage> value = new LinkedList<>();

        Document doc = Jsoup.parse(htmlLoaded);
        ParsedVacancies parsedElems = new ParsedVacancies(doc);
        //HtmlVacPage temp = new HtmlVacPage(doc, htmlLoaded, link);

        Elements elems = doc.select("div.search-result-item");

        parsedElems.setSize(elems.size());
        Iterator<Element> iterator = elems.iterator();
        Integer qty = 0;
        //value.add(temp);
        while (iterator.hasNext()) {
            Element elem = iterator.next();
            String vacancyPageUrl = elem.select("div.search-result-item__head").first().getElementsByAttribute("href").first().attr("href");
            String contentPage = get_html_by_link(vacancyPageUrl);

            HtmlVacPage p = new HtmlVacPage(Jsoup.parse(contentPage), contentPage, vacancyPageUrl);

            /*try {
                parse_html_page(contentPage, vacancyPageUrl);
                qty++;
                parsedElems.setQty(qty);
            } catch (Exception e) {
                logHistory(e.getMessage(), vacancyPageUrl, SYSTEM_TYPE, VACANCY_TYPE);
                logger.debug(e.getMessage());
            }*/
            value.add(p);
        }
        return value;
    }

    public void start_vacancy_load(String html, TaskLink task) throws IOException {
        //забираем по одной ссылке со страницы и каждую грузим.
        boolean needWork = true;
        Integer count = 0;

        HistoryLoad historyLoad = new HistoryLoad();
        historyLoad.setStartTime(new Date());
        while (needWork) {
            try {
                long start = (new Date()).getTime();
                ParsedVacancies p = parse_page(html);
                long diff = (new Date()).getTime() - start;
                logger.debug("parse time qty: " + p.getQty() + " time: " + diff);

                count += p.getQty();

                Document doc = p.getDoc();
                String next_link = get_next_link_from_page(doc);
                html = "";
                if (next_link.length() > 0) {
                    count++;
                    html = get_html_by_link(PREFIX + next_link);
                } else {
                    needWork = false;
                }
            } catch (Exception ex) {
                logger.debug(ex.getMessage());
            }
        }
        logger.debug("End load root page: " + task.getLink());
        historyLoad.setEndTime(new Date());
        historyLoad.setTaskLink(task);
        historyLoad.setRecord_type(VACANCY_TYPE);
        historyLoad.setQty_entity(count);
        historyLoad.setHtml(html.getBytes());
        historyLoad.setProcessed(true);
        historyLoadRepository.create(historyLoad);

    }

    /*  public void start_vacancy_load1(String html, TaskLink task) throws IOException {
          //забираем по одной ссылке со страницы и каждую грузим.
          boolean needWork = true;
          Integer count = 0;

          if(task.getQty_entity() < 2000) {
              List<HtmlVacPage> htmlPages = new LinkedList<HtmlVacPage>();
              HistoryLoad historyLoad = new HistoryLoad();
              historyLoad.setStartTime(new Date());
              String next_link = task.getLink();
              while (needWork) {
                  try {

                      List<HtmlVacPage> htmlVacPages = parse_page_test(html, next_link);
                      htmlPages.addAll(htmlVacPages);

                      Document doc = htmlVacPages.get(htmlVacPages.size()-1).getDoc();
                      next_link = get_next_link_from_page(doc);
                      html = "";
                      if (next_link.length() > 0) {
                          html = get_html_by_link(PREFIX + next_link);
                      } else {
                          needWork = false;
                      }
                  } catch (Exception ex) {
                      logger.debug(ex.getMessage());
                  }
              }

              for(HtmlVacPage p : htmlPages){
                  parse_html_page_test(p.getDoc(), p.getUrl());
              }



              logger.debug("End load root page: " + task.getLink());
              historyLoad.setEndTime(new Date());
              historyLoad.setTaskLink(task);
              historyLoad.setRecord_type(VACANCY_TYPE);
              historyLoad.setQty_entity(count);
              historyLoad.setHtml(html.getBytes());
              historyLoad.setProcessed(true);
              historyLoadRepository.create(historyLoad);
          }
      }
  */
    private void logHistory(String msg, String url, int systemtype, int type) {
        try {
            historyRepository.create(new History(msg.getBytes(), url, systemtype, type));
        } catch (Exception e) {

        }
    }

    private void parse_html_page(String htmlContent, String vacancyPageUrl) {
        // разбор целиком вакансии
        Document doc = Jsoup.parse(htmlContent);
        String company_name = doc.select("div.companyname").first().getElementsByAttribute("href").first().text();
        String Salary = doc.select("td.l-content-colum-1").first().select("div.l-paddings").text();
        String city = doc.select("td.l-content-colum-2").select("div.l-paddings").text();
        String Experience = doc.select("td.l-content-colum-3").select("div.l-paddings").text();

        String full_description = doc.select("div.b-vacancy-desc-wrapper").text();
        String typeWork = doc.select("div.b-vacancy-employmentmode").first().select("div.l-content-paddings").text(); //полная занятост, полный день.
        String vacancy_name = doc.select("h1.title").text();  // Менеджер проектов SMM

        String company_addr = "";
        if (doc.select("div.VacancyView_location").size() > 0) {
            doc.select("div.VacancyView_location").first().select("span.h_color_gray").first().text();
        }

//        String vacancy_internal_number = doc.select("div.VacancyView_number").first().text();
//        String[] splt = vacancy_internal_number.split(" ");
//        Long vacancy_internale_id = Long.parseLong(splt[2]);
        ////////////////////////////////////////////////////////
        Vacancy vacancy = new Vacancy();
        vacancy.setVacancy_name(vacancy_name);
        vacancy.setSalary(Salary);
        vacancy.setExperience(Experience);

//        vacancy.setNeed_make(need_make.getBytes());
//        vacancy.setNeeds(needs.getBytes());
//        vacancy.setPredlagaem(predlagaem.getBytes());

        vacancy.setTypeWork(typeWork);
        vacancy.setFullDescription(full_description.getBytes());
        vacancy.setCity(city);

        vacancy.setCompany_name(company_name);
        vacancy.setCompany_addr(company_addr);
        vacancy.setUrl(vacancyPageUrl);
        vacancy.setSystemId(SYSTEM_TYPE);
        //vacancy.setVacancy_internale_id(getLongInternalVacancyIdFromUrl(vacancyPageUrl));

        //if (!vacancyService.findByLink(vacancy.getUrl(), SYSTEM_TYPE)) {
        try {
            vacancyService.create(vacancy);
            logger.debug("Save vacancy: " + vacancy.getUrl());
        } catch (Exception e) {
            logger.debug("Save vacancy exception: " + e.getMessage());
        }
        //}
    }

    /*
        private void parse_html_page_test(Document doc, String vacancyPageUrl) {
            // разбор целиком вакансии
            //Document doc = Jsoup.parse(htmlContent);
            String company_name = doc.select("div.companyname").first().getElementsByAttribute("href").first().text();
            String Salary = doc.select("td.l-content-colum-1").first().select("div.l-paddings").text();
            String city = doc.select("td.l-content-colum-2").select("div.l-paddings").text();
            String Experience = doc.select("td.l-content-colum-3").select("div.l-paddings").text();

            String full_description = doc.select("div.b-vacancy-desc-wrapper").text();
            String typeWork = doc.select("div.b-vacancy-employmentmode").first().select("div.l-content-paddings").text(); //полная занятост, полный день.
            String vacancy_name = doc.select("h1.title").text();  // Менеджер проектов SMM

            String company_addr = "";
            if (doc.select("div.VacancyView_location").size() > 0) {
                doc.select("div.VacancyView_location").first().select("span.h_color_gray").first().text();
            }

    //        String vacancy_internal_number = doc.select("div.VacancyView_number").first().text();
    //        String[] splt = vacancy_internal_number.split(" ");
    //        Long vacancy_internale_id = Long.parseLong(splt[2]);
            ////////////////////////////////////////////////////////
            Vacancy vacancy = new Vacancy();
            vacancy.setVacancy_name(vacancy_name);
            vacancy.setSalary(Salary);
            vacancy.setExperience(Experience);

    //        vacancy.setNeed_make(need_make.getBytes());
    //        vacancy.setNeeds(needs.getBytes());
    //        vacancy.setPredlagaem(predlagaem.getBytes());

            vacancy.setTypeWork(typeWork);
            vacancy.setFullDescription(full_description.getBytes());
            vacancy.setCity(city);

            vacancy.setCompany_name(company_name);
            vacancy.setCompany_addr(company_addr);
            vacancy.setUrl(vacancyPageUrl);
            vacancy.setSystemId(SYSTEM_TYPE);
            //vacancy.setVacancy_internale_id(getLongInternalVacancyIdFromUrl(vacancyPageUrl));

            if (!vacancyService.findByLink(vacancy.getUrl(), SYSTEM_TYPE)) {
                vacancyService.create(vacancy);
                logger.debug("Save vacancy: " + vacancy.getUrl());
            }
        }
    */
    private Long getLongInternalVacancyIdFromUrl(String vacancyPageUrl) {
        String longId = vacancyPageUrl.replaceAll("[^0-9]+", "");
        return Long.parseLong(longId);
    }

    private String get_next_link_from_page(Document doc) {
        return doc.select("div.b-pager__next").first().getElementsByAttribute("href").first().attr("href");
    }

    @Override
    public void startResume() {

    }

    private void BuildVocabulary() throws IOException {
        //получение списка отраслей для фильтрации

        String content = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.hh.ru/industries");
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

            Gson gson = new Gson();
            JsonElement obj = gson.fromJson(content, JsonElement.class);


        }
    }
}
