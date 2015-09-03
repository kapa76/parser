package org.parser.persistence.kernel.factory;

import com.google.gson.JsonObject;
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
import org.parser.persistence.model.History;
import org.parser.persistence.model.Resume;
import org.parser.persistence.model.Vacancy;
import org.parser.persistence.repository.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
    public HistoryRepository historyRepository;

    @Autowired
    public AgencyRepository agencyService;

    @Autowired
    private BusinessTripRepository businessTripService;

    @Autowired
    private ChildrenRepository childrenService;

    @Autowired
    private ChildrenResumeRepository childrenResumeService;

    @Autowired
    private CitizenshipRepository citizenshipService;

    @Autowired
    private CurrencyRepository currencyService;

    @Autowired
    private EducationRepository educationService;

    @Autowired
    private EducationFormResumeRepository educationFormResumeService;

    @Autowired
    private EducationHistoryRepository educationHistoryService;

    @Autowired
    private EducationTypeResumeRepository educationTypeResumeService;

    @Autowired
    private ExperienceRepository experienceService;

    @Autowired
    private GenderRepository genderService;

    @Autowired
    private GenderResumeRepository genderResumeService;

    @Autowired
    private LangLevelRepository langLevelService;

    @Autowired
    private LangLevelResumeRepository langLevelResumeService;

    @Autowired
    private LanguageRepository languageService;

    @Autowired
    private LanguageResumeRepository languageResumeService;

    @Autowired
    private MaritalStatusRepository maritalStatusService;

    @Autowired
    private MaritalStatusResumeRepository maritalStatusResumeService;

    @Autowired
    private MaritalStatusResumeGenderRepository maritalStatusResumeGenderService;

    @Autowired
    private MoveableRepository moveableService;

    @Autowired
    private PlaceRepository placeService;

    @Autowired
    private PlaceDetailRepository placeDetailService;

    @Autowired
    private PlaceWorkRepository placeWorkService;

    @Autowired
    private PreviosWorkHistoryRepository previosWorkHistoryService;

    @Autowired
    private ProfessionalRepository professionalService;

    @Autowired
    private ProfessionalDetailRepository professionalDetailService;

    @Autowired
    private PropertiesRRepository propertiesRService;

    @Autowired
    private PropertiesVRepository propertiesVService;

    @Autowired
    private PublishedResumeRepository publishedResumeService;

    @Autowired
    private QueuerRepository queuerService;

    @Autowired
    private QueuevRepository queuevService;

    @Autowired
    private ResumeRepository resumeService;

    @Autowired
    private SiteRepository siteService;

    @Autowired
    private SocialLinksResumeRepository socialLinksResumeService;

    @Autowired
    private TypeOfWorkRepository typeOfWorkService;

    @Autowired
    private VacancyRepository vacancyService;

    @Autowired
    private WorkTypeRepository workTypeService;

    @Autowired
    private ProfessionalVRepository professionalVRepository;

    @Autowired
    private ProfessionalDetailVRepository professionalDetailVRepository;

    @Override
    public void startVacancy() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(START_PAGE);
        HttpResponse response = client.execute(request);

        int returnCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + returnCode);  //200
        if (returnCode == 200) {
            String htmlPage = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            start_vacancy_load(htmlPage);
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
            String contentPage = get_html_by_link(vacancyPageUrl);

            try {
                parse_html_page(contentPage, vacancyPageUrl);
            } catch (Exception e) {
                logHistory(e.getMessage(), vacancyPageUrl, 0, 0);
            }
        }

        String next_link = get_next_link_from_page(doc);
        if (next_link.length() > 0) {
            String html = get_html_by_link(next_link);
            start_vacancy_load(html);
        }
    }

    private void logHistory(String msg, String url, int systemtype, int type) {
        historyRepository.create(new History(msg, url, systemtype, type));
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


    private void saveResume(List<JsonObject> resumeObj) {
        for (JsonObject t : resumeObj) {
            Resume v = new Resume();
          /*
            //////
            v.setId_internal(t.get("id").getAsLong());
            v.setLast_profession(t.get("last_profession").getAsString().replaceAll("\"", ""));
            v.setPayment(t.get("payment").getAsDouble());

            String val = t.get("currency").getAsString().replaceAll("\"", "");
            v.setCurrency(currencyService.findOne(val));


            v.setBirthday();
            v.setBirthmonth();
            v.setBirthyear();
            v.setAge();
            v.setAddress();

            v.setCitizenship();
            v.setPublishedResume();
            v.setPublishedResume();
            v.setMoveable();


            v.setAgreement();


            v.setType_of_work();

            v.setPlaceOfWork();

            v.setEducation();

            v.setBusinessTrip();

            v.setMaritalStatus();

            v.setProfessional();

            v.setTown();
            v.setRegion();

            v.setExperience_text();
            v.setExperience_month_count();

            v.setPreviosWorkHistories();

            v.setBaseEducationsHistory();

            v.setGender();

            v.setAchievements();

            v.setAdditionalInfo();

            v.setPublished();

            v.setDateLastModified();

            v.setIsArchive();

            v.setIdUser();

            v.setProfession();


            /////
            v.setId_client(t.get("id_client").getAsLong());
            v.setPayment(t.get("payment_to").getAsDouble());
            v.setDatePublished(t.get("date_published").getAsLong());
            v.setWork(CommonUtils.isNullNull(t.get("work")));
            v.setCandidat(CommonUtils.isNullNull(t.get("candidat")));
            v.setCurrency(currencyService.findOne(CommonUtils.isNullNull(t.get("currency"))));
            v.setCompensation(CommonUtils.isNullNull(t.get("compensation")));
            v.setProfession(t.get("profession").getAsString());
            v.setAddress(CommonUtils.isNullNull(t.get("address")));
            v.setDate_pub_to(t.get("date_pub_to").getAsLong());
            v.setPayment_from(t.get("payment_from").getAsDouble());
            v.setInternal_id(t.get("id").getAsLong());
            if (t.get("moveable").isJsonObject()) {
                String val = t.get("place_of_work").getAsJsonObject().get("title").getAsString();
                v.setMoveable(moveableService.findOne(val));
            }
            v.setAgreement(CommonUtils.isNullNull(t.get("agreement")));
            v.setAnonymous(CommonUtils.isNullNull(t.get("anonymous")));
            v.setIs_archive((t.get("is_archive").getAsBoolean()));
            v.setIs_storage((t.get("is_storage").getAsBoolean()));

            if (t.get("type_of_work").isJsonObject()) {
                String val = t.get("type_of_work").getAsJsonObject().get("title").getAsString();
                v.setType_of_work(typeOfWorkService.findOne(val));
            }
            if (t.get("place_of_work").isJsonObject()) {
                String val = t.get("place_of_work").getAsJsonObject().get("title").getAsString();
                v.setPlaceOfWork(placeWorkService.findOne(val));
            }
            if (t.get("education").isJsonObject()) {
                String val = t.get("education").getAsJsonObject().get("title").getAsString();
                v.setEducation(educationService.findOne(val));
            }
            if (t.get("experience").isJsonObject()) {
                String val = t.get("experience").getAsJsonObject().get("title").getAsString();
                v.setExperience(experienceService.findOne(val));
            }
            if (t.get("maritalstatus").isJsonObject()) {
                String val = t.get("maritalstatus").getAsJsonObject().get("title").getAsString();
                v.setMaritalStatus(maritalStatusService.findOne(val));
            }
            if (t.get("children").isJsonObject()) {
                String val = t.get("children").getAsJsonObject().get("title").getAsString();
                v.setChildren(childrenService.findOne(val));
            }
            if (t.get("languages").isJsonObject()) {
                String val = t.get("languages").getAsJsonObject().get("title").getAsString();
                v.setLanguages(languageService.findOne(val));
            }
            ProfessionalV pv = null;
            if (t.get("catalogues").isJsonArray()) {
                if (t.get("catalogues").getAsJsonArray().size() > 0) {
                    String pValue = t.get("catalogues").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsString().replaceAll("\"", "");
                    int size = t.get("catalogues").getAsJsonArray().get(0).getAsJsonObject().get("positions").getAsJsonArray().size();

                    Professional p = professionalService.findOne(pValue);

                    pv = new ProfessionalV(p);

                    Set<ProfessionalDetailV> setPdv = new HashSet<>();
                    for (int i = 0; i < size; i++) {
                        String pdValue = t.get("catalogues").getAsJsonArray().get(0).getAsJsonObject().get("positions").getAsJsonArray().get(i).getAsJsonObject().get("title").getAsString().replaceAll("\"", "");

                        ProfessionalDetail pd = professionalDetailService.findOne(pdValue);
                        ProfessionalDetailV pdv = new ProfessionalDetailV(pd, pv);
                        professionalDetailVRepository.create(pdv);
                        setPdv.add(pdv);


                    }
                    pv.setProfessionalDetailV(setPdv);
                    professionalVRepository.create(pv);

                    //professionalVRepository.update(pv);
                }
            }
            if (t.get("agency").isJsonObject()) {
                String val = t.get("agency").getAsJsonObject().get("title").getAsString();
                v.setAgency(agencyService.findOne(val));
            }
            if (t.get("town").isJsonObject()) {
                String val = t.get("town").getAsJsonObject().get("title").getAsString();
                City city = cityService.findOne(val);
                if (city == null) {
                    cityService.create(city);
                }
                v.setCity(city);
            }
            v.setAgeFrom(t.get("age_from").getAsInt());
            v.setAgeTo(t.get("age_to").getAsInt());

            if (t.get("gender").isJsonObject()) {
                String val = t.get("gender").getAsJsonObject().get("title").getAsString();
                v.setGender(genderService.findOne(val));
            }
            v.setCompanyDescr(CommonUtils.isNullNull(t.get("firm_activity")));
            v.setCompanyUrl(CommonUtils.isNullNull(t.get("link")));
            v.setCompanyName(CommonUtils.isNullNull(t.get("firm_name")));
            try {
                if (!resumeService.exist(v.getId_client(), v.getInternal_id())) {
                    resumeService.create(v);
                    pv.setVacancylV(v);
                    professionalVRepository.update(pv);

                    logger.debug("Save vacancy: " + v.getId_client());
                }
            } catch (DataIntegrityViolationException exception) {
                //save to history error

                String mesg = exception.getMessage();

            }*/
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
            //String vacancyPageUrl = elem.select("h2.VacancyListElement_position").first().getElementsByAttribute("href").first().attr("href");
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
        String vacancy_internal_number = doc.select("div.resume_num_info").first().text();
        String[] splt = vacancy_internal_number.split(" ");
        Long resume_internale_id = Long.parseLong(splt[1]);

        String resume_name = doc.select("h1.sj_typo_h1").text();
        Elements ee = doc.select("td.ResumeMainHR_payment_td");
        Object obj[] = ee.toArray();

        String payment = ((Element) obj[0]).text();
        String work_type = ((Element) obj[1]).text();
        String place = ((Element) obj[2]).text();
        //((Element) obj[1]).text()
        String bdays = doc.select("div.ResumeMainHR_content_row").first().text();


        ////опыт работы
        Elements historys = doc.select("div.ResumeDetailsHR_row");
        Iterator<Element> iterator = historys.iterator();
        while (iterator.hasNext()) {
            Element elem = iterator.next();

            String fromTo = elem.select("div.ResumeDetailsHR_leftblock").first().text();

            String place1 = elem.select("div.work_profesion").first().select("h2").first().text();

            String companyName = elem.select("div.ResumeDetailsHR_line_rightblock").first().select("p").first().text();
            String description = elem.select("div.work_profesion").select("h2").text();
        }
        ///////////////
        String hasNowledge = doc.select("div.ResumeDetailsHR_14font").first().text();
        String education = doc.select("div.ResumeDetailsHR_line_rightblock").first().text();


//        if (!vacancyService.findByInternal(vacancy.getVacancy_internale_id())) {
//            vacancyService.create(vacancy);
//        }
    }


}
