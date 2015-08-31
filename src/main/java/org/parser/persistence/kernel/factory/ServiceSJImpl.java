package org.parser.persistence.kernel.factory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.parser.common.SiteEnum;
import org.parser.common.SuperJobVacancyPage;
import org.parser.persistence.kernel.ServiceSJ;
import org.parser.persistence.model.*;
import org.parser.persistence.model.Currency;
import org.parser.persistence.repository.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ServiceSJImpl implements ServiceSJ {

    private final String SJ_URL = "http://www.superjob.ru/vacancy/search/?period=0&c%5B%5D=1&catalogues=&pay1=&pay0=0&experience=0&type_of_work=0&place_of_work=0&age=&pol=0&education=0&lng=0&lnlev=0&lang0=0&agency=0&moveable=0&active=0&detail_search=1&sbmit=1&extended=0";
    private final String SJ_LOAD_PROPERTIES = "https://api.superjob.ru/2.0/references/";
    private final String SJ_INIT_URL = "http://www.superjob.ru/vacancy/search_form.html";


    @Autowired
    public CityRepository cityService;

    /*
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
               */

    private Site siteDefault = null;

    @Override
    public void init() {
        //cityService.create(new City("asdasd"));
        //new CityRepository()
        loadReferencesSJ(SJ_LOAD_PROPERTIES, SiteEnum.superjob);

    }

    @Override
    public void startVacancy() throws IOException {
        //siteDefault = siteService.findOne("superjob");

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

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            int returnCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + returnCode);  //200

            if (returnCode == 200) {
                String result = IOUtils.toString(response.getEntity().getContent(), "UTF-8");

                SuperJobVacancyPage sPage = new SuperJobVacancyPage(result);


                //place_of_work
                JsonElement element = new Gson().fromJson(result, JsonElement.class);
                //CheckCreateClass(element, "place_of_work", PlaceWork.class, );

            }

            page++;

        }


    }

    @Override
    public void startResume() {

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
                //place_of_work
                JsonElement element = new Gson().fromJson(result, JsonElement.class);
                //CheckCreateClass(element, "place_of_work", PlaceWork.class, );
                /*
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
                */
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

                    PlaceWork p = placeWorkService.findOne(value);
                    if (p == null) {
                        placeWorkService.create(obj);
                    }
                }

    } */

                /*
    public void SocialLinksResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            SocialLinksResume p = socialLinksResumeService.findOne(value);
            if (p == null) {
                socialLinksResumeService.create(new SocialLinksResume(value, siteDefault));
            }
        }
    }

    public void PublishedResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            PublishedResume p = publishedResumeService.findOne(value);
            if (p == null) {
                publishedResumeService.create(new PublishedResume(value, siteDefault));
            }
        }
    }

    public void BusinessTripServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            BusinessTrip p = businessTripService.findOne(value);
            if (p == null) {
                businessTripService.create(new BusinessTrip(value, siteDefault));
            }
        }
    }

    public void MoveableServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Moveable p = moveableService.findOne(value);
            if (p == null) {
                moveableService.create(new Moveable(value, siteDefault));
            }
        }
    }

    public void CitizenshipServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Citizenship p = citizenshipService.findOne(value);
            if (p == null) {
                citizenshipService.create(new Citizenship(value, siteDefault));
            }
        }
    }

    public void WorkTypeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            WorkType p = workTypeService.findOne(value);
            if (p == null) {
                workTypeService.create(new WorkType(value, siteDefault));
            }
        }
    }

    public void EducationTypeResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            EducationTypeResume p = educationTypeResumeService.findOne(value);
            if (p == null) {
                educationTypeResumeService.create(new EducationTypeResume(value, siteDefault));
            }
        }
    }

    public void EducationFormResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            EducationFormResume p = educationFormResumeService.findOne(value);
            if (p == null) {
                educationFormResumeService.create(new EducationFormResume(value, siteDefault));
            }
        }
    }

    public void CurrencyServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Currency p = currencyService.findOne(value);
            if (p == null) {
                currencyService.create(new Currency(value, siteDefault));
            }
        }
    }

    public void GenderServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Gender p = genderService.findOne(value);
            if (p == null) {
                genderService.create(new Gender(value, siteDefault));
            }
        }
    }

    public void AgencyServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Agency p = agencyService.findOne(value);
            if (p == null) {
                agencyService.create(new Agency(value, siteDefault));
            }
        }
    }

    public void MaritalStatusResumeGenderServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            MaritalStatusResumeGender p = maritalStatusResumeGenderService.findOne(value);
            if (p == null) {
                maritalStatusResumeGenderService.create(new MaritalStatusResumeGender(value, siteDefault));
            }
        }
    }

    public void ChildrenServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Children p = childrenService.findOne(value);
            if (p == null) {
                childrenService.create(new Children(value, siteDefault));
            }
        }
    }

    public void ChildrenResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            ChildrenResume p = childrenResumeService.findOne(value);
            if (p == null) {
                childrenResumeService.create(new ChildrenResume(value, siteDefault));
            }
        }
    }

    public void MaritalStatusResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            MaritalStatusResume p = maritalStatusResumeService.findOne(value);
            if (p == null) {
                maritalStatusResumeService.create(new MaritalStatusResume(value, siteDefault));
            }
        }
    }

    public void GenderResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            GenderResume p = genderResumeService.findOne(value);
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

            MaritalStatus p = maritalStatusService.findOne(value);
            if (p == null) {
                maritalStatusService.create(new MaritalStatus(value, siteDefault));
            }
        }
    }

    public void LangLevelResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            LangLevelResume p = langLevelResumeService.findOne(value);
            if (p == null) {
                langLevelResumeService.create(new LangLevelResume(value, siteDefault));
            }
        }
    }

    public void LanguageResumeServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            LanguageResume p = languageResumeService.findOne(value);
            if (p == null) {
                languageResumeService.create(new LanguageResume(value, siteDefault));
            }
        }
    }

    public void LangLevelServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            LangLevel p = langLevelService.findOne(value);
            if (p == null) {
                langLevelService.create(new LangLevel(value, siteDefault));
            }
        }
    }

    public void LanguageServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Language p = languageService.findOne(value);
            if (p == null) {
                languageService.create(new Language(value, siteDefault));
            }
        }
    }

    public void ExperienceServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Experience p = experienceService.findOne(value);
            if (p == null) {
                experienceService.create(new Experience(value, siteDefault));
            }
        }
    }

    public void EducationServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            Education p = educationService.findOne(value);
            if (p == null) {
                educationService.create(new Education(value, siteDefault));
            }
        }
    }

    public void PlaceWorkServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            PlaceWork p = placeWorkService.findOne(value);
            if (p == null) {
                placeWorkService.create(new PlaceWork(value, siteDefault));
            }
        }
    }


    public void TypeOfWorkServiceInit(JsonElement element, String value) {
        Iterator<Map.Entry<String, JsonElement>> iter = element.getAsJsonObject().getAsJsonObject(value).entrySet().iterator();
        List<String> tval = new ArrayList<String>();
        while (iter.hasNext()) {
            Map.Entry<String, JsonElement> t = iter.next();
            String name = t.getValue().toString();

            TypeOfWork p = typeOfWorkService.findOne(value);
            if (p == null) {
                typeOfWorkService.create(new TypeOfWork(value, siteDefault));
            }
        }
    }      */

}
