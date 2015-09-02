package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "base_education")
@Table
public class BaseEducation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "institute")
    private String institute;

    @Column(name = "town")
    private String town;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "profession")
    private String profession;

    @ManyToOne(targetEntity = EducationTypeResume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_educationtype")
    private EducationTypeResume educationTypeResume;

    @ManyToOne(targetEntity = EducationFormResume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_educationform")
    private EducationFormResume educationFormResume;

    @Column(name = "yearend")
    private Integer yearend;

    @ManyToOne(targetEntity = Site.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    private Site site;

    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume")
    private Resume resume;

    public BaseEducation() {
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public EducationTypeResume getEducationTypeResume() {
        return educationTypeResume;
    }

    public void setEducationTypeResume(EducationTypeResume educationTypeResume) {
        this.educationTypeResume = educationTypeResume;
    }

    public EducationFormResume getEducationFormResume() {
        return educationFormResume;
    }

    public void setEducationFormResume(EducationFormResume educationFormResume) {
        this.educationFormResume = educationFormResume;
    }

    public Integer getYearend() {
        return yearend;
    }

    public void setYearend(Integer yearend) {
        this.yearend = yearend;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}