package org.parser.persistence.kernel.factory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parser.common.ParsedVacancies;
import org.parser.persistence.kernel.ServiceHH;
import org.parser.persistence.model.*;
import org.parser.persistence.repository.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;


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

    private HttpClient client;

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
        if (client == null) {
            client = HttpClientBuilder.create().build();
        }
        //если нет не обработанных вакансий то начинаем поиск заново.
        List<TaskLink> taskLinkListNotProcessed = new ArrayList<>();
        taskLinkListNotProcessed = taskLinkProcessedRepositoy.getNotProcessedLink(0);
        if (taskLinkListNotProcessed != null && taskLinkListNotProcessed.size() > 0) {
            for (TaskLink task : taskLinkListNotProcessed) {

                //корень поиска - начальная страница со всеми списками
                start_vacancy_load(new String(task.getHtml()), task);

                task.setProcessed(true);
                taskLinkProcessedRepositoy.update(task);
            }

        } else {//если все обработано начинаем новый поиск
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
        //HttpClient client = HttpClientBuilder.create().build();
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
                logHistory(e.getMessage(), vacancyPageUrl, 1, 0);
                logger.debug(e.getMessage());
            }
        }
        return parsedElems;
    }

    public void start_vacancy_load(String html, TaskLink task) throws IOException {
        //забираем по одной ссылке со страницы и каждую грузим.
        boolean needWork = true;
        Integer count = 0;

        HistoryLoad historyLoad = new HistoryLoad();
        historyLoad.setStartTime(new Date());
        while (needWork) {
            try {
                ParsedVacancies p = parse_page(html);

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
        historyLoad.setEndTime(new Date());
        historyLoad.setTaskLink(task);
        historyLoad.setRecord_type(0);
        historyLoad.setQty_entity(count);
        historyLoad.setHtml(html.getBytes());
        historyLoad.setProcessed(true);
        historyLoadRepository.create(historyLoad);

    }

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
        vacancy.setSystemId(1);
        vacancy.setVacancy_internale_id(getLongInternalVacancyIdFromUrl(vacancyPageUrl));

        if (!vacancyService.findByLink(vacancy.getUrl(), 1)) {
            vacancyService.create(vacancy);
        }
    }

    private Long getLongInternalVacancyIdFromUrl(String vacancyPageUrl) {
        String longId = vacancyPageUrl.replaceAll("[^0-9]+", "");
        return Long.parseLong(longId);
    }

    private String get_next_link_from_page(Document doc) {
        String next_url = doc.select("div.b-pager__next").first().getElementsByAttribute("href").first().attr("href");
        return next_url;
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
