package org.parser.persistence.kernel.factory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.parser.common.SiteEnum;
import org.parser.common.SuperJobResumePage;
import org.parser.common.SuperJobVacancyPage;
import org.parser.persistence.kernel.ServiceSJ;
import org.parser.persistence.model.*;
import org.parser.persistence.model.Currency;
import org.parser.persistence.repository.hibernate.*;
import org.parser.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ServiceSJImpl implements ServiceSJ {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceSJImpl.class);

    private final String SJ_URL = "http://www.superjob.ru/vacancy/search/?period=0&c%5B%5D=1&catalogues=&pay1=&pay0=0&experience=0&type_of_work=0&place_of_work=0&age=&pol=0&education=0&lng=0&lnlev=0&lang0=0&agency=0&moveable=0&active=0&detail_search=1&sbmit=1&extended=0";
    private final String SJ_LOAD_PROPERTIES = "https://api.superjob.ru/2.0/references/";
    private final String SJ_INIT_URL = "http://www.superjob.ru/vacancy/search_form.html";


    @Autowired
    public CityRepository cityService;


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


    private Site siteDefault = null;

    @Override
    public void init() {

        loadReferencesSJ(SJ_LOAD_PROPERTIES, SiteEnum.superjob);

    }

    @Override
    public void startVacancy() throws IOException {
        siteDefault = siteService.findOne("superjob");

        //load first page для получения кол-ва страниц
        int current = 0;
        int lastPage;

        Integer period = 0;//за все время
        Integer count = 20; // - на странице
        Integer page = 0; // номер страницы
        //если more = true -> есть что качать.
        boolean more = true;

        String url = "https://api.superjob.ru/2.0/" + "r00e72d6106234c3e2bd3d644c726c4aaeafd6483e52ae3cfc217329676e9de33710117a5" + "/vacancies?";

        while (more) {
            //?page = 0 & period = 0 & count

            List<NameValuePair> params = new LinkedList<NameValuePair>();
            params.add(new BasicNameValuePair("page", page.toString()));
            params.add(new BasicNameValuePair("count", count.toString()));
            params.add(new BasicNameValuePair("period", "0"));
            String paramString = URLEncodedUtils.format(params, "utf-8");
            url = url + paramString;

            //url сохранить в history на тему делали не делали ? ....

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            int returnCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + returnCode);  //200

            if (returnCode == 200) {
                String result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                SuperJobVacancyPage sPage = new SuperJobVacancyPage(result);

                saveVacancy(sPage.getVacancyJsonObject());

                //more = false;
                more = sPage.getMore();
                page++;
                if (page % 10 == 0)
                    vacancyService.setCommit(true);
            }
        }
    }

    private void saveVacancy(List<JsonObject> vacancyObj) {
        for (JsonObject t : vacancyObj) {
            Vacancy v = new Vacancy();

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
            if (t.get("catalogues").isJsonArray()) {
                if (t.get("catalogues").getAsJsonArray().size() > 0) {
                    String pValue = t.get("catalogues").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsString().replaceAll("\"", "");
                    int size = t.get("catalogues").getAsJsonArray().get(0).getAsJsonObject().get("positions").getAsJsonArray().size();

                    Professional p = professionalService.findOne(pValue);
                    for (int i = 0; i < size; i++) {
                        String pdValue = t.get("catalogues").getAsJsonArray().get(0).getAsJsonObject().get("positions").getAsJsonArray().get(i).getAsJsonObject().get("title").getAsString().replaceAll("\"", "");
                        ProfessionalDetail pd = professionalDetailService.findOne(pdValue);
                    }
                }
            }
            if (t.get("agency").isJsonObject()) {
                String val = t.get("agency").getAsJsonObject().get("title").getAsString();
                v.setAgency(agencyService.findOne(val));
            }
            if (t.get("town").isJsonObject()) {
                String val = t.get("town").getAsJsonObject().get("title").getAsString();
                v.setCity(cityService.findOne(val));
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
                vacancyService.create(v);
                logger.debug("Save vacancy: " + v.getId_client());
            } catch (DataIntegrityViolationException exception) {
                //save to history error

                String mesg = exception.getMessage();

            }
        }
    }

    private void saveResume(List<JsonObject> resumeObj) {

    }

    @Override
    public void startResume() throws IOException {
        siteDefault = siteService.findOne("superjob");

        //load first page для получения кол-ва страниц
        int current = 0;
        int lastPage;

        Integer period = 0;//за все время
        Integer count = 20; // - на странице
        Integer page = 0; // номер страницы
        //если more = true -> есть что качать.
        boolean more = true;

        String url = "https://api.superjob.ru/2.0/" + "r00e72d6106234c3e2bd3d644c726c4aaeafd6483e52ae3cfc217329676e9de33710117a5" + "/resumes?";

        while (more) {
            //?page = 0 & period = 0 & count

            List<NameValuePair> params = new LinkedList<NameValuePair>();
            params.add(new BasicNameValuePair("page", page.toString()));
            params.add(new BasicNameValuePair("count", count.toString()));
            params.add(new BasicNameValuePair("period", "0"));
            String paramString = URLEncodedUtils.format(params, "utf-8");
            url = url + paramString;

            //url сохранить в history на тему делали не делали ? ....

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            int returnCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + returnCode);  //200

            if (returnCode == 200) {
                String result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                SuperJobResumePage sPage = new SuperJobResumePage(result);

                saveResume(sPage.getResumeJsonObject());
                more = sPage.getMore();
                page++;
            }
        }
    }

    private void loadReferencesSJ(String initUrl, SiteEnum val) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(initUrl);
            HttpResponse response = client.execute(request);

            int returnCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + returnCode);  //200

            if (returnCode == 200) {
                String result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
                JsonElement element = new Gson().fromJson(result, JsonElement.class);

                PlaceWorkServiceInit(element, "place_of_work");
                TypeOfWorkServiceInit(element, "type_of_work");
                EducationServiceInit(element, "education");
                ExperienceServiceInit(element, "experience");
                LanguageServiceInit(element, "language");
                LangLevelServiceInit(element, "lang_level");
                LanguageResumeServiceInit(element, "language_resume");
                LangLevelResumeServiceInit(element, "lang_level_resume");
                MaritalStatusServiceInit(element, "maritalstatus");
                MaritalStatusResumeServiceInit(element, "maritalstatus_resume");
                MaritalStatusResumeGenderServiceInit(element, "maritalstatus_resume_gender");
                ChildrenServiceInit(element, "children");
                ChildrenResumeServiceInit(element, "children_resume");
                AgencyServiceInit(element, "agency");
                GenderServiceInit(element, "gender");
                GenderResumeServiceInit(element, "gender_resume");
                CurrencyServiceInit(element, "currency");
                EducationFormResumeServiceInit(element, "education_form_resume");
                EducationTypeResumeServiceInit(element, "education_type_resume");
                WorkTypeServiceInit(element, "work_type");
                CitizenshipServiceInit(element, "citizenship");
                MoveableServiceInit(element, "moveable");
                BusinessTripServiceInit(element, "business_trip");
                PublishedResumeServiceInit(element, "published_resume");
                SocialLinksResumeServiceInit(element, "social_links_resume");

            }
        } catch (Exception e) {
            String mesg = e.getMessage();
        }
    }


    /*private void CheckCreateClass(JsonElement element, String value, Class className) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = className;

        Object obj = cl.getDeclaredConstructor(className).newInstance(value);
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
                List<String> tval = new ArrayList<String>();
                while (iter.hasNext()) {
                    Map.Entry<String, JsonElement> t = iter.next();
                    String name = t.getValue().toString();

                    PlaceWork p = placeWorkService.findOne(name);
                    if (p == null) {
                        placeWorkService.create(obj);
                    }
                }

    } */


    public void SocialLinksResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            SocialLinksResume p = socialLinksResumeService.findOne(name);
            if (p == null) {
                socialLinksResumeService.create(new SocialLinksResume(name, siteDefault));
            }
        }
    }

    public void PublishedResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            PublishedResume p = publishedResumeService.findOne(name);
            if (p == null) {
                publishedResumeService.create(new PublishedResume(name, siteDefault));
            }
        }
    }

    public void BusinessTripServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            BusinessTrip p = businessTripService.findOne(name);
            if (p == null) {
                businessTripService.create(new BusinessTrip(name, siteDefault));
            }
        }
    }

    public void MoveableServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Moveable p = moveableService.findOne(name);
            if (p == null) {
                moveableService.create(new Moveable(name, siteDefault));
            }
        }
    }

    public void CitizenshipServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Citizenship p = citizenshipService.findOne(name);
            if (p == null) {
                citizenshipService.create(new Citizenship(name, siteDefault));
            }
        }
    }

    public void WorkTypeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            WorkType p = workTypeService.findOne(name);
            if (p == null) {
                workTypeService.create(new WorkType(name, siteDefault));
            }
        }
    }

    public void EducationTypeResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            EducationTypeResume p = educationTypeResumeService.findOne(name);
            if (p == null) {
                educationTypeResumeService.create(new EducationTypeResume(name, siteDefault));
            }
        }
    }

    public void EducationFormResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            EducationFormResume p = educationFormResumeService.findOne(name);
            if (p == null) {
                educationFormResumeService.create(new EducationFormResume(name, siteDefault));
            }
        }
    }

    public void CurrencyServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Currency p = currencyService.findOne(name);
            if (p == null) {
                currencyService.create(new Currency(name, siteDefault));
            }
        }
    }

    public void GenderServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Gender p = genderService.findOne(name);
            if (p == null) {
                genderService.create(new Gender(name, siteDefault));
            }
        }
    }

    public void AgencyServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Agency p = agencyService.findOne(name);
            if (p == null) {
                agencyService.create(new Agency(name, siteDefault));
            }
        }
    }

    public void MaritalStatusResumeGenderServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            MaritalStatusResumeGender p = maritalStatusResumeGenderService.findOne(name);
            if (p == null) {
                maritalStatusResumeGenderService.create(new MaritalStatusResumeGender(name, siteDefault));
            }
        }
    }

    public void ChildrenServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Children p = childrenService.findOne(name);
            if (p == null) {
                childrenService.create(new Children(name, siteDefault));
            }
        }
    }

    public void ChildrenResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            ChildrenResume p = childrenResumeService.findOne(name);
            if (p == null) {
                childrenResumeService.create(new ChildrenResume(name, siteDefault));
            }
        }
    }

    public void MaritalStatusResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            MaritalStatusResume p = maritalStatusResumeService.findOne(name);
            if (p == null) {
                maritalStatusResumeService.create(new MaritalStatusResume(name, siteDefault));
            }
        }
    }

    public void GenderResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            GenderResume p = genderResumeService.findOne(name);
            if (p == null) {
                genderResumeService.create(new GenderResume(value, siteDefault));
            }
        }
    }

    public void MaritalStatusServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            MaritalStatus p = maritalStatusService.findOne(name);
            if (p == null) {
                maritalStatusService.create(new MaritalStatus(name, siteDefault));
            }
        }
    }

    public void LangLevelResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            LangLevelResume p = langLevelResumeService.findOne(name);
            if (p == null) {
                langLevelResumeService.create(new LangLevelResume(name, siteDefault));
            }
        }
    }

    public void LanguageResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            LanguageResume p = languageResumeService.findOne(name);
            if (p == null) {
                languageResumeService.create(new LanguageResume(name, siteDefault));
            }
        }
    }

    public void LangLevelServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            LangLevel p = langLevelService.findOne(name);
            if (p == null) {
                langLevelService.create(new LangLevel(name, siteDefault));
            }
        }
    }

    public void LanguageServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Language p = languageService.findOne(name);
            if (p == null) {
                languageService.create(new Language(name, siteDefault));
            }
        }
    }

    public void ExperienceServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Experience p = experienceService.findOne(name);
            if (p == null) {
                experienceService.create(new Experience(name, siteDefault));
            }
        }
    }

    public void EducationServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            Education p = educationService.findOne(name);
            if (p == null) {
                educationService.create(new Education(name, siteDefault));
            }
        }
    }

    public void PlaceWorkServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            PlaceWork p = placeWorkService.findOne(name);
            if (p == null) {
                placeWorkService.create(new PlaceWork(name, siteDefault));
            }
        }
    }


    public void TypeOfWorkServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();
            name = name.replaceAll("\"", "");
            TypeOfWork p = typeOfWorkService.findOne(name);
            if (p == null) {
                typeOfWorkService.create(new TypeOfWork(name, siteDefault));
            }
        }
    }

}
