package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "previos_work")
@Table
public class PreviosWorkHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_city")
    private City city;

    @Column(name = "profession")
    private String profession;

    @Column(name = "work")
    private String work;

    @ManyToOne(targetEntity = WorkType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worktype")
    private WorkType type;

    @Column(name = "monthbeg")
    private Integer monthbeg;

    @Column(name = "monthend")
    private Integer monthend;

    @Column(name = "yearbeg")
    private Integer yearbeg;

    @Column(name = "yearend")
    private Integer yearend;

    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume")
    private Resume resume;

    public PreviosWorkHistory() {
        super();
    }

    public PreviosWorkHistory(final String name) {
        super();
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public WorkType getType() {
        return type;
    }

    public void setType(WorkType type) {
        this.type = type;
    }

    public Integer getMonthbeg() {
        return monthbeg;
    }

    public void setMonthbeg(Integer monthbeg) {
        this.monthbeg = monthbeg;
    }

    public Integer getMonthend() {
        return monthend;
    }

    public void setMonthend(Integer monthend) {
        this.monthend = monthend;
    }

    public Integer getYearbeg() {
        return yearbeg;
    }

    public void setYearbeg(Integer yearbeg) {
        this.yearbeg = yearbeg;
    }

    public Integer getYearend() {
        return yearend;
    }

    public void setYearend(Integer yearend) {
        this.yearend = yearend;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
