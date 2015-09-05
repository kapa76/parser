package org.parser.persistence.kernel.factory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parser.persistence.kernel.ServiceSJ;
import org.parser.persistence.model.*;
import org.parser.persistence.repository.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ServiceSJImpl implements ServiceSJ {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceSJImpl.class);

    private final String START_PAGE =
            "http://www.superjob.ru/vacancy/search/?period=0&catalogues=&pay1=&pay0=0&experience=0&type_of_work=0&place_of_work=0&age=&pol=0&education=0&lng=0&lnlev=0&lang0=0&agency=0&moveable=0&active=0&detail_search=1&sbmit=1&extended=1&c%5B%5D=1";

    private final String START_RESUME_PAGE =
            "http://www.superjob.ru/resume/search_resume.html?sbmit=1&detail_search=1&keywords%5B0%5D%5Bkeys%5D=&keywords%5B0%5D%5Bskwc%5D=and&keywords%5B0%5D%5Bsrws%5D=7&exclude_words=&period=0&place_of_work=0&c%5B%5D=1&paymentfrom=&paymentto=&type_of_work=0&citizenship%5B0%5D=0&old1=&old2=&maritalstatus=0&pol=0&children=0&education=0&eduform=0&id_institute=0&institution=&languages%5B0%5D%5Blanguage_id%5D=0&languages%5B0%5D%5Blanguage_level%5D=0&business_trip=0";
    @Autowired
    public CityRepository cityService;

    @Autowired
    public HistoryLoadRepository historyLoadRepository;

    @Autowired
    public HistoryRepository historyRepository;

    @Autowired
    public AgencyRepository agencyService;

    @Autowired
    private EducationRepository educationService;

    @Autowired
    private EducationFormResumeRepository educationFormResumeService;

    @Autowired
    private EducationHistoryRepository educationHistoryService;

    @Autowired
    private EducationTypeResumeRepository educationTypeResumeService;

    @Autowired
    private PreviosWorkHistoryRepository previosWorkHistoryService;

    @Autowired
    private QueuerRepository queuerService;

    @Autowired
    private QueuevRepository queuevService;

    @Autowired
    private ResumeRepository resumeService;

    @Autowired
    private VacancyRepository vacancyService;

    @Autowired
    private TaskLinkProcessedRepositoy taskLinkProcessedRepositoy;

    @Override
    public void startVacancy() throws IOException {


        //если нет не обработанных вакансий то начинаем поиск заново.
        List<TaskLink> taskLinkListNotProcessed = new ArrayList<>();
        taskLinkListNotProcessed = taskLinkProcessedRepositoy.getNotProcessedLink(0);
        if (taskLinkListNotProcessed.size() == 0) {
            //если все обработано начинаем новый поиск


        } else {
            //есть заполненные слова для поиска профессии и отрасли
//            buildListForSearch();

//            startFindBySearchWords();

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(START_PAGE);
            HttpResponse response = client.execute(request);

            int returnCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + returnCode);  //200
            if (returnCode == 200) {
                String htmlPage = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

//            start_vacancy_load(readFile("c:\\repository\\parser\\input_data\\superjob\\vacancy\\full_vacancy.html"));
                start_vacancy_load(htmlPage);
            }
        }
    }

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

    public void start_vacancy_load(String htmlLoaded) throws IOException {
        //забираем по одной ссылке со страницы и каждую грузим.


        Document doc = Jsoup.parse(htmlLoaded);
        Elements elems = doc.select("div.VacancyListElement_content");
        int countOnPage = elems.size();
        Iterator<Element> iterator = elems.iterator();
        while (iterator.hasNext()) {
            Element elem = iterator.next();
            String vacancyPageUrl = elem.select("h2.VacancyListElement_position").first().getElementsByAttribute("href").first().attr("href");

//            String contentPage = readFile("c:\\repository\\parser\\input_data\\superjob\\vacancy\\once_vacacy.html");
            String contentPage = get_html_by_link(vacancyPageUrl);

            try {
                parse_html_page(contentPage, vacancyPageUrl);
            } catch (Exception e) {
                logHistory(e.getMessage(), vacancyPageUrl, 0, 0);
            }
        }

       /* String next_link = get_next_link_from_page(doc);
        if (next_link.length() > 0) {
            String html = get_html_by_link(next_link);
            start_vacancy_load(html);
        } */
    }

    private void logHistory(String msg, String url, int systemtype, int type) {
        historyRepository.create(new History(msg.getBytes(), url, 1, type));
    }

    private String get_next_link_from_page(Document doc) {
        String next_url = "";
        try {
            next_url = doc.select("div.navbar").first().getElementsByAttribute("href").first().attr("href");
        } catch (Exception e) {
            //

            String msg = "";
        }
        return next_url;
    }

    private void parse_html_page(String htmlContent, String url) {
        // разбор целиком вакансии
        Document doc = Jsoup.parse(htmlContent);
        String vacancy_name = doc.select("h1.VacancyView_title").text();

        String Salary = doc.select("div.VacancyView_salary").select("span.h_color_green").text();
        String Experience = doc.select("div.VacancyView_salary").select("b.h_color_gray_dk").text();

        Elements ee = doc.select("div.VacancyDetails_section");
        Object obj[] = ee.toArray();
        String need_make = ((Element) obj[0]).text();

        String needs = "";
        if (obj.length > 1)
            needs = ((Element) obj[1]).text();

        String predlagaem = "";
        if (obj.length > 2)
            predlagaem = ((Element) obj[2]).text();

        String company_name = "";
        try {
            company_name = doc.select("div.VacancyView_location").first().getElementsByAttribute("href").first().text();
        } catch (Exception e) {

        }
        String company_addr = doc.select("div.VacancyView_location").first().select("span.h_color_gray").first().text();

        String vacancy_internal_number = doc.select("div.VacancyView_number").first().text();
        String[] splt = vacancy_internal_number.split(" ");
        Long vacancy_internale_id = Long.parseLong(splt[2]);

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancy_name(vacancy_name);
        vacancy.setSalary(Salary);
        vacancy.setExperience(Experience);

        vacancy.setNeed_make(need_make.getBytes());
        vacancy.setNeeds(needs.getBytes());
        vacancy.setPredlagaem(predlagaem.getBytes());

        vacancy.setCompany_name(company_name);
        vacancy.setCompany_addr(company_addr);

        vacancy.setVacancy_internale_id(vacancy_internale_id);
        vacancy.setUrl(url);
        vacancy.setSystemId(0);

        if (!vacancyService.findByInternal(vacancy.getVacancy_internale_id())) {
            vacancyService.create(vacancy);
        }
    }


    @Override
    public void startResume() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(START_RESUME_PAGE);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200
        if (returnCode == 200) {
            String htmlPage = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
//            start_resume_load(readFile("c:\\repository\\parser\\input_data\\superjob\\resume\\full_resume.html"));
            start_resume_load(htmlPage);
        }
    }

    private void start_resume_load(String htmlPage) throws IOException {
        Document doc = Jsoup.parse(htmlPage);

        Elements elems = doc.select("div.ResumeSearchResult_list");
        int countOnPage = elems.size();
        Iterator<Element> iterator = elems.iterator();
        while (iterator.hasNext()) {
            Element elem = iterator.next();
            String vacancyPageUrl = elem.select("h2.ResumeListElement_post").first().getElementsByAttribute("href").first().attr("href");
//            String contentPage = readFile("c:\\repository\\parser\\input_data\\superjob\\resume\\once_resume.html");
            String contentPage = get_html_by_link(vacancyPageUrl);
            parse_resume_html_page(contentPage, vacancyPageUrl);
        }

        String next_link = get_next_link_from_page(doc);
        if (next_link.length() > 0) {
            String html = get_html_by_link(next_link);
            start_vacancy_load(html);
        }
    }

    private void parse_resume_html_page(String contentPage, String vacancyPageUrl) {
        // разбор целиком вакансии
        Document doc = Jsoup.parse(contentPage);
        String resume_internal_number = doc.select("div.resume_num_info").first().text();
        String[] splt = resume_internal_number.split(" ");
        Long resume_internale_id = Long.parseLong(splt[1]);

        String resume_name = doc.select("h1.sj_typo_h1").text();
        Elements ee = doc.select("td.ResumeMainHR_payment_td");
        Object obj[] = ee.toArray();

        String payment = ((Element) obj[0]).text();
        String work_type = "";
        if (obj.length > 1)
            work_type = ((Element) obj[1]).text();
        String place = "";
        if (obj.length > 2)
            place = ((Element) obj[2]).text();

        String bdays = doc.select("div.ResumeMainHR_content_row").first().text();

        ////опыт работы
        Elements historys = doc.select("div.ResumeDetailsHR_row");
        Iterator<Element> iterator = historys.iterator();
        List<PreviosWorkHistory> ll = new ArrayList<>();
        while (iterator.hasNext()) {
            Element elem = iterator.next();
            PreviosWorkHistory history = new PreviosWorkHistory();


            String fromTo = elem.select("div.ResumeDetailsHR_leftblock").first().text();
            String place_descr = elem.select("div.work_profesion").first().select("h2").first().text();

            String companyName = elem.select("div.ResumeDetailsHR_line_rightblock").first().select("p").first().text();
            String description = elem.select("div.work_profesion").select("h2").text();

            history.setCompanyName(companyName);
            history.setDescription(description.getBytes());
            history.setFrom_to(fromTo);
            history.setPlace_descr(place_descr.getBytes());

            previosWorkHistoryService.create(history);
            ll.add(history);
        }
        ///////////////
        String hasNowledge = doc.select("div.ResumeDetailsHR_14font").first().text();
        String education = doc.select("div.ResumeDetailsHR_line_rightblock").first().text();
        Resume resume = new Resume();

        resume.setPrevios(ll);


        resumeService.create(resume);
//        if (!vacancyService.findByInternal(vacancy.getVacancy_internale_id())) {
//            vacancyService.create(vacancy);
//        }
    }
       /*
    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        //String         ls = System.getProperty("line.separator");

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            //stringBuilder.append( ls );
        }

        return stringBuilder.toString();
    }  */

}
