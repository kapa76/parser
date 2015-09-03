package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "vacancy")
@Table
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "vacancy_name")
    private String vacancy_name;

    @Column(name = "salary")
    private String Salary;

    @Column(name = "experience")
    private String Experience;

    @Column(name = "need_make")
    private byte[] need_make;

    @Column(name = "needs")
    private byte[] needs;

    @Column(name = "predlagaem")
    private byte[] predlagaem;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "company_addr")
    private String company_addr;

    @Column(name = "vacancy_internale_id")
    private Long vacancy_internale_id;

    @Column(name = "url")
    private String url;

    @Column(name = "system_id")
    private Integer systemId;

    @Column(name = "city")
    private String city;

    @Column(name = "full_description")
    private byte[] fullDescription;

    @Column(name = "typeWork")
    private String typeWork;

    public Vacancy() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getVacancy_name() {
        return vacancy_name;
    }

    public void setVacancy_name(String vacancy_name) {
        this.vacancy_name = vacancy_name;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public byte[] getNeed_make() {
        return need_make;
    }

    public void setNeed_make(byte[] need_make) {
        this.need_make = need_make;
    }

    public byte[] getNeeds() {
        return needs;
    }

    public void setNeeds(byte[] needs) {
        this.needs = needs;
    }

    public byte[] getPredlagaem() {
        return predlagaem;
    }

    public void setPredlagaem(byte[] predlagaem) {
        this.predlagaem = predlagaem;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_addr() {
        return company_addr;
    }

    public void setCompany_addr(String company_addr) {
        this.company_addr = company_addr;
    }

    public Long getVacancy_internale_id() {
        return vacancy_internale_id;
    }

    public void setVacancy_internale_id(Long vacancy_internale_id) {
        this.vacancy_internale_id = vacancy_internale_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte[] getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(byte[] fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getTypeWork() {
        return typeWork;
    }

    public void setTypeWork(String typeWork) {
        this.typeWork = typeWork;
    }
}