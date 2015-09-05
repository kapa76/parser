package org.parser.persistence.kernel.factory;

import com.google.gson.Gson;
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
import org.parser.persistence.kernel.ServiceHH;
import org.parser.persistence.model.TaskLink;
import org.parser.persistence.model.Vacancy;
import org.parser.persistence.repository.hibernate.HistoryRepository;
import org.parser.persistence.repository.hibernate.TaskLinkProcessedRepositoy;
import org.parser.persistence.repository.hibernate.VacancyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@Repository
@Transactional
public class ServiceHHImpl implements ServiceHH {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceHHImpl.class);

    private final String START_PAGE = "http://hh.ru/search/vacancy?text=&salary=&currency_code=RUR&experience=doesNotMatter&order_by=relevance&search_period=30&items_on_page=20&no_magic=true";

    @Autowired
    private VacancyRepository vacancyService;

    @Autowired
    public HistoryRepository historyRepository;

    @Autowired
    private TaskLinkProcessedRepositoy taskLinkProcessedRepositoy;

    private String get_html_by_link(String url) throws IOException {
        String content = "";
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200

        if (returnCode == 200) {
            content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        }
        return content;
    }

    /*private String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        //String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            //stringBuilder.append( ls );
        }

        return stringBuilder.toString();
    }  */

    @Override
    public void startVacancy() throws IOException {
        //если нет не обработанных вакансий то начинаем поиск заново.
        List<TaskLink> taskLinkListNotProcessed = new ArrayList<>();
        taskLinkListNotProcessed = taskLinkProcessedRepositoy.getNotProcessedLink(0);
        if (taskLinkListNotProcessed.size() == 0) {
            //если все обработано начинаем новый поиск


        } else {
            //есть заполненные слова для поиска профессии и отрасли
            buildListForSearch();

            startFindBySearchWords();
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(START_PAGE);
            HttpResponse response = client.execute(request);

            int returnCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + returnCode);  //200
            if (returnCode == 200) {
                String htmlPage = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
//            start_vacancy_load(readFile("c:\\repository\\parser\\input_data\\hh\\vacancy\\full.html"));
                start_vacancy_load(htmlPage);
            }
        }
    }

    public void start_vacancy_load(String htmlLoaded) throws IOException {
        //забираем по одной ссылке со страницы и каждую грузим.

        Document doc = Jsoup.parse(htmlLoaded);
        Elements elems = doc.select("div.search-result-item");
        int countOnPage = elems.size();
        Iterator<Element> iterator = elems.iterator();
        while (iterator.hasNext()) {
            Element elem = iterator.next();
            String vacancyPageUrl = elem.select("div.search-result-item__head").first().getElementsByAttribute("href").first().attr("href");
            String contentPage = get_html_by_link(vacancyPageUrl);
//            String contentPage = readFile("c:\\repository\\parser\\input_data\\hh\\vacancy\\vacancy.html");
            try {
                parse_html_page(contentPage, vacancyPageUrl);
            } catch (Exception e) {
                logHistory(e.getMessage(), vacancyPageUrl, 1, 0);
            }
        }

//        String next_link = get_next_link_from_page(doc);
//        if (next_link.length() > 0) {
//            String html = get_html_by_link(next_link);
//            start_vacancy_load(html);
//        }
    }


    private void logHistory(String msg, String url, int systemtype, int type) {
        historyRepository.create(new History(msg, url, systemtype, type));
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


        vacancy.setCompany_name(company_name);
        vacancy.setCompany_addr(company_addr);
        vacancy.setUrl(vacancyPageUrl);
        vacancy.setSystemId(1);
//        vacancy.setVacancy_internale_id(vacancy_internale_id);

        if (!vacancyService.findByLink(vacancy.getUrl(), 1)) {
            vacancyService.create(vacancy);
        }
    }

    private String get_next_link_from_page(Document doc) {
        String next_url = doc.select("div.b-pager__next").first().select("span.b-pager__next-arrow").first().getElementsByAttribute("href").first().attr("href");
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
