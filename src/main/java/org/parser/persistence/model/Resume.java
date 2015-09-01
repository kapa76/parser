package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity(name = "resume")
@Table
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @ManyToOne(targetEntity = Experience.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_experience")
    private Experience experience;
    @Column(name = "last_update")
    private Date lastUpdate;
    @Column(name = "page")
    private int page;
    @Column(name = "condition_search")
    private int conditionSearch;
    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;
    @ManyToOne(targetEntity = Professional.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_professional")
    private Professional professional;
    @ManyToOne(targetEntity = ProfessionalDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prof_detail")
    private ProfessionalDetail professionalDetail;
    @ManyToOne(targetEntity = Place.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_place")
    private Place place;
    @Column(name = "id_internal")
    private long id_internal;
    @Column(name = "last_profession")
    private String last_profession;
    @Column(name = "payment_salary")
    private Double paymentSalary;
    @ManyToOne(targetEntity = Currency.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_currency")
    private Currency currency;
    @Column(name = "birthday")
    private Integer birthday;
    @Column(name = "birthmonth")
    private Integer birthmonth;
    @Column(name = "birthyear")
    private Integer birthyear;
    @Column(name = "age")
    private Integer age;
    @Column(name = "address")
    private String address;
    //   metro: [0]
    @ManyToOne(targetEntity = Citizenship.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_citizenship")
    private Citizenship citizenship;
    /*
    published: {
    id: 1
    title: "Открытый доступ"
    }-
      */
    /*draft: false
    photo: "http://public.superjob.ru/images/resume_fotos/728/36/31672836.medium_7b2f0ccd9c5e49ad48c693d59ac5768c.jpg"
    photo_sizes: {
    small: "http://public.superjob.ru/images/resume_fotos/728/36/31672836.small_7b2f0ccd9c5e49ad48c693d59ac5768c.jpg"
    medium: "http://public.superjob.ru/images/resume_fotos/728/36/31672836.medium_7b2f0ccd9c5e49ad48c693d59ac5768c.jpg"
    large: "http://public.superjob.ru/images/resume_fotos/728/36/31672836.large_7b2f0ccd9c5e49ad48c693d59ac5768c.jpg"
    }-  */
    @ManyToOne(targetEntity = Moveable.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_moveable")
    private Moveable moveable;
    @Column(name = "agreement")
    private String agreement;
    @ManyToOne(targetEntity = TypeOfWork.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_type_of_work")
    private TypeOfWork type_of_work;
    @ManyToOne(targetEntity = PlaceWork.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_place_of_work")
    private PlaceWork placeOfWork;
    @ManyToOne(targetEntity = Children.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_children")
    private Children children;
    /*business_trip: {
    id: 2
    title: "Готов"
    }-*/
    @ManyToOne(targetEntity = MaritalStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marital_status")
    private MaritalStatus maritalStatus;
    @Column(name = "experience_text")
    private String experienceText;
    @Column(name = "experience_month_count")
    private String experienceMonthCount;

    /*@OneToMany(mappedBy = "professional", fetch = FetchType.EAGER)
      private Set<Properties> languages;

    languages: [1]
    0:  [2]
    0:  {
    id: 1
    title: "Английский"
    level: 3
    }-
    1:  {
    id: 3
    title: "Базовый"
    }-
    -
    -    */

    /*driving_licence: [2]
    0:  "B"
    1:  "C"
    - */

    /*@ManyToOne(targetEntity = Professional.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_professional")
    private Professional professional;

    @ManyToOne(targetEntity = ProfessionalDetail.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prof_detail")
    private ProfessionalDetail professionalDetail;*/

    /*region: {
    id: 3
    title: "Центральный округ"
    }-*/
    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER)
    private Set<PreviosWorkHistory> previosWorkHistory;
    @OneToMany(mappedBy = "resume", fetch = FetchType.EAGER)
    private Set<EducationHistory> educationHistories;
    @Column(name = "resume_url")
    private String resumeUrl;
    @ManyToOne(targetEntity = Gender.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gender")
    private Gender gender;
    @Column(name = "achievements")
    private String achievements;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "date_published")
    private Date datePublished;
    @Column(name = "date_last_modified")
    private Date dateLastModified;
    @Column(name = "profession")
    private String profession;
    @Column(name = "is_archive")
    private boolean isArchive;
    @Column(name = "id_user")
    private Long idUser;

    public Resume() {
        super();
    }

    public Resume(final String name) {
        super();
        this.name = name;
    }

}