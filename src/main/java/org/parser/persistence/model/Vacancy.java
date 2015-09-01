package org.parser.persistence.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "id_client")
    private Long id_client;
    @Column(name = "date_published")
    private Long datePublished;
    @Column(name = "payment")
    private Double payment;
    @Column(name = "work")
    private String work;
    @Column(name = "candidat")
    private String candidat;
    @ManyToOne(targetEntity = Currency.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_currency")
    private Currency currency;
    @Column(name = "compensation")
    private String compensation;
    /**/
    @Column(name = "address")
    private String address;
    @Column(name = "date_pub_to")
    private long date_pub_to;

//    @Column(name = "profession")
//    private String profession;
    @Column(name = "payment_from")
    private Double payment_from;
    @Column(name = "internal_id")
    private long internal_id;
    @ManyToOne(targetEntity = Moveable.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_moveable")
    private Moveable moveable;
    @Column(name = "agreement")
    private String agreement;
    @Column(name = "anonymous")
    private String anonymous;
    @Column(name = "is_archive")
    private boolean is_archive;
    @Column(name = "is_storage")
    private boolean is_storage;
    @ManyToOne(targetEntity = TypeOfWork.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type_of_work")
    private TypeOfWork type_of_work;
    @ManyToOne(targetEntity = PlaceWork.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_place_of_work")
    private PlaceWork placeOfWork;
    @ManyToOne(targetEntity = Experience.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_experience")
    private Experience experience;
    @ManyToOne(targetEntity = Education.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_education")
    private Education education;
    @ManyToOne(targetEntity = MaritalStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marital_status")
    private MaritalStatus maritalStatus;
    @ManyToOne(targetEntity = Children.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_children")
    private Children children;
    @ManyToOne(targetEntity = Language.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_languages")
    private Language languages;
    @ManyToOne(targetEntity = Agency.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_agency")
    private Agency agency;
    @Column(name = "company_name")
    private String companyName;

    /*driving_licence: [1]
    0:  "B"
    - */
    /*catalogues: [1]
    0:  {
    id: 86
    title: "Транспорт, логистика, ВЭД"
    positions: [1]
    0:  {
    id: 88
    title: "Автоперевозки"
    }-
    -
    }-
    - */
    @Column(name = "profession")
    private String profession;
    @Column(name = "company_descr")
    private String companyDescr;   //firm_activity
    @Column(name = "age_from")
    private Integer ageFrom;
    @Column(name = "age_to")
    private Integer ageTo;

    //message_received: false
    //already_sent_on_vacancy: false
    //client_logo: "http://public.superjob.ru/images/clients_logos.ru/2618757_06a595332beb1fba6032729847600979.png"
    @ManyToOne(targetEntity = Gender.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gender")
    private Gender gender;
    @Column(name = "company_url")
    private String companyUrl;
    @ManyToOne(targetEntity = Professional.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_professional")
    private Professional professional;
    @ManyToOne(targetEntity = ProfessionalDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prof_detail")
    private ProfessionalDetail professionalDetail;
    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;
    @Column(name = "last_update")
    private Date lastUpdate;
    @Column(name = "page")
    private int page;
    @Column(name = "condition_search")
    private int conditionSearch;

    public Vacancy() {
        super();
    }

    public Vacancy(final String name) {
        super();
        this.profession = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public Long getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Long datePublished) {
        this.datePublished = datePublished;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCandidat() {
        return candidat;
    }

    public void setCandidat(String candidat) {
        this.candidat = candidat;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDate_pub_to() {
        return date_pub_to;
    }

    public void setDate_pub_to(long date_pub_to) {
        this.date_pub_to = date_pub_to;
    }

    public Double getPayment_from() {
        return payment_from;
    }

    public void setPayment_from(Double payment_from) {
        this.payment_from = payment_from;
    }

    public long getInternal_id() {
        return internal_id;
    }

    public void setInternal_id(long internal_id) {
        this.internal_id = internal_id;
    }

    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public boolean is_archive() {
        return is_archive;
    }

    public void setIs_archive(boolean is_archive) {
        this.is_archive = is_archive;
    }

    public boolean is_storage() {
        return is_storage;
    }

    public void setIs_storage(boolean is_storage) {
        this.is_storage = is_storage;
    }

    public TypeOfWork getType_of_work() {
        return type_of_work;
    }

    public void setType_of_work(TypeOfWork type_of_work) {
        this.type_of_work = type_of_work;
    }

    public PlaceWork getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(PlaceWork placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public Language getLanguages() {
        return languages;
    }

    public void setLanguages(Language languages) {
        this.languages = languages;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompanyDescr() {
        return companyDescr;
    }

    public void setCompanyDescr(String companyDescr) {
        this.companyDescr = companyDescr;
    }

    public Integer getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(Integer ageFrom) {
        this.ageFrom = ageFrom;
    }

    public Integer getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(Integer ageTo) {
        this.ageTo = ageTo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public ProfessionalDetail getProfessionalDetail() {
        return professionalDetail;
    }

    public void setProfessionalDetail(ProfessionalDetail professionalDetail) {
        this.professionalDetail = professionalDetail;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getConditionSearch() {
        return conditionSearch;
    }

    public void setConditionSearch(int conditionSearch) {
        this.conditionSearch = conditionSearch;
    }
}
