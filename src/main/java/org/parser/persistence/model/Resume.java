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

    @Column(name = "last_profession")
    private String last_profession;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_internal() {
        return id_internal;
    }

    public void setId_internal(long id_internal) {
        this.id_internal = id_internal;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Integer getBirthmonth() {
        return birthmonth;
    }

    public void setBirthmonth(Integer birthmonth) {
        this.birthmonth = birthmonth;
    }

    public Integer getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Integer birthyear) {
        this.birthyear = birthyear;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public PublishedResume getPublishedResume() {
        return publishedResume;
    }

    public void setPublishedResume(PublishedResume publishedResume) {
        this.publishedResume = publishedResume;
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

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public BusinessTrip getBusinessTrip() {
        return businessTrip;
    }

    public void setBusinessTrip(BusinessTrip businessTrip) {
        this.businessTrip = businessTrip;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public List<ProfessionalR> getProfessional() {
        return professional;
    }

    public void setProfessional(List<ProfessionalR> professional) {
        this.professional = professional;
    }

    public City getTown() {
        return town;
    }

    public void setTown(City town) {
        this.town = town;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getExperience_text() {
        return experience_text;
    }

    public void setExperience_text(String experience_text) {
        this.experience_text = experience_text;
    }

    public Integer getExperience_month_count() {
        return experience_month_count;
    }

    public void setExperience_month_count(Integer experience_month_count) {
        this.experience_month_count = experience_month_count;
    }

    public List<PreviosWorkHistory> getPreviosWorkHistories() {
        return previosWorkHistories;
    }

    public void setPreviosWorkHistories(List<PreviosWorkHistory> previosWorkHistories) {
        this.previosWorkHistories = previosWorkHistories;
    }

    public List<BaseEducation> getBaseEducationsHistory() {
        return baseEducationsHistory;
    }

    public void setBaseEducationsHistory(List<BaseEducation> baseEducationsHistory) {
        this.baseEducationsHistory = baseEducationsHistory;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public byte[] getAchievements() {
        return achievements;
    }

    public void setAchievements(byte[] achievements) {
        this.achievements = achievements;
    }

    public byte[] getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(byte[] additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setIsArchive(boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLast_profession() {
        return last_profession;
    }

    public void setLast_profession(String last_profession) {
        this.last_profession = last_profession;
    }
}