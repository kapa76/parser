package org.parser.persistence.model;


import javax.persistence.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 1L;

    public Vacancy() {
        super();
    }

    public Vacancy(final String name) {
        super();
        this.profession = name;
    }

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

//    @Column(name = "profession")
//    private String profession;

    /**/
    @Column(name = "address")
    private String address;

    @Column(name = "date_pub_to")
    private long date_pub_to;

    @Column(name = "payment_from")
    private long payment_from;

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

    @ManyToOne(targetEntity = Agency.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_agency")
    private Agency agency;


    @Column(name = "company_name")
    private String companyName;

    @Column(name = "profession")
    private String profession;

    @Column(name = "company_descr")
    private String companyDescr;   //firm_activity

    //message_received: false
    //already_sent_on_vacancy: false
    //client_logo: "http://public.superjob.ru/images/clients_logos.ru/2618757_06a595332beb1fba6032729847600979.png"

    @Column(name = "age_from")
    private Integer ageFrom;

    @Column(name = "age_to")
    private Integer ageTo;

    @ManyToOne(targetEntity = Gender.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gender")
    private Gender gender;


    @Column(name = "company_url")
    private String companyUrl;

    /**/


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

    public ProfessionalDetail getProfessionalDetail() {
        return professionalDetail;
    }

    public void setProfessionalDetail(ProfessionalDetail professionalDetail) {
        this.professionalDetail = professionalDetail;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
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
