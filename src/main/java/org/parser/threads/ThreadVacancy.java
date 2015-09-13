package org.parser.threads;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parser.common.ParsedVacancies;
import org.parser.persistence.model.History;
import org.parser.persistence.model.HistoryLoad;
import org.parser.persistence.model.TaskLink;
import org.parser.persistence.model.Vacancy;
import org.parser.persistence.repository.hibernate.HistoryLoadRepository;
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
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Callable;

@Service
@Transactional
public class ThreadVacancy implements Callable<String> {

    private String html;
    private TaskLink taskLink;

    private static final Logger logger =
            LoggerFactory.getLogger(ThreadVacancy.class);


    private int SYSTEM_TYPE = 0;
    private int VACANCY_TYPE = 0;
    private String PREFIX = "http://hh.ru";

    private TaskLinkProcessedRepositoy taskLinkProcessedRepositoy;

    private VacancyRepository vacancyService;

    public HistoryRepository historyRepository;

    public HistoryLoadRepository historyLoadRepository;

    public void setTaskLinkProcessedRepositoy(TaskLinkProcessedRepositoy taskLinkProcessedRepositoy){
        this.taskLinkProcessedRepositoy = taskLinkProcessedRepositoy;
    }

    public void setHistoryRepository(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    public void setHistoryLoadRepository(HistoryLoadRepository historyLoadRepository){
        this.historyLoadRepository = historyLoadRepository;
    }

    public void setVacancyService(VacancyRepository vacancyService){
        this.vacancyService = vacancyService;
    }

    public ThreadVacancy(){

    }

    public ThreadVacancy(String html, TaskLink task) {
        this.html = html;
        this.taskLink = task;
    }

    @Override
    public String call() throws Exception {
        String val = "";
        //корень поиска - начальная страница со всеми списками
        try {
            start_vacancy_load(html, taskLink);
            taskLink.setProcessed(true);
            taskLinkProcessedRepositoy.update(taskLink);
        } catch (Exception e) {
            val = e.getMessage();
        }
        return val;
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

    private String get_next_link_from_page(Document doc) {
        return doc.select("div.b-pager__next").first().getElementsByAttribute("href").first().attr("href");
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

    private void logHistory(String msg, String url, int systemtype, int type) {
        try {
            historyRepository.create(new History(msg.getBytes(), url, systemtype, type));
        } catch (Exception e) {

        }
    }


}
