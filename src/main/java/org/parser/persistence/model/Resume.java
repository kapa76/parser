package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity(name = "resume")
@Table
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "id_internal")
    private long id_internal;

    @Column(name = "payment_salary")
    private Double payment;

    @ManyToOne(targetEntity = Currency.class, fetch = FetchType.LAZY)
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


    @ManyToOne(targetEntity = Citizenship.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_citizenship")
    private Citizenship citizenship;

    @ManyToOne(targetEntity = PublishedResume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_published_resume")
    private PublishedResume publishedResume;


    @ManyToOne(targetEntity = Moveable.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moveable")
    private Moveable moveable;

    @Column(name = "agreement")
    private String agreement;

    @ManyToOne(targetEntity = TypeOfWork.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_of_work")
    private TypeOfWork type_of_work;

    @ManyToOne(targetEntity = PlaceWork.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_place_of_work")
    private PlaceWork placeOfWork;

    @ManyToOne(targetEntity = Education.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_education")
    private Education education;

    @ManyToOne(targetEntity = BusinessTrip.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_businesstrip")
    private BusinessTrip businessTrip;

    @ManyToOne(targetEntity = MaritalStatus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marital_status")
    private MaritalStatus maritalStatus;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY)
    private List<ProfessionalR> professional;

    @ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City town;

    @Column(name = "region")
    private String region;

    @Column(name = "experience_text")
    private String experience_text;

    @Column(name = "experience_month_count")
    private Integer experience_month_count;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY)
    private List<PreviosWorkHistory> previosWorkHistories;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY)
    private List<BaseEducation> baseEducationsHistory;

    @ManyToOne(targetEntity = Gender.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gendery")
    private Gender gender;

    @Column(name = "achievements")
    private byte[] achievements;

    @Column(name = "additional_info")
    private byte[] additionalInfo;

    @Column(name = "date_published")
    private Date published;

    @Column(name = "date_last_modified")
    private Date dateLastModified;

    @Column(name = "is_archive")
    private boolean isArchive;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "profession")
    private String profession;

    public Resume() {
        super();
    }

    public Resume(final String name) {
        super();
        this.profession = name;
    }

}