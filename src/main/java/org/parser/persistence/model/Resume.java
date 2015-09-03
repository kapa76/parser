package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "resume")
@Table
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "resume_name")
    private String resume_name;

    @Column(name = "salary")
    private String Salary;

    @Column(name = "experience")
    private String Experience;

    @Column(name = "need_make")
    private byte[] need_make;

    @Column(name = "needs")
    private byte[] needs;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "company_addr")
    private String company_addr;

    @Column(name = "resume_internale_id")
    private String resume_internale_id;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY)
    private List<PreviosWorkHistory> previos;

    public Resume() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getResume_name() {
        return resume_name;
    }

    public void setResume_name(String resume_name) {
        this.resume_name = resume_name;
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

    public String getResume_internale_id() {
        return resume_internale_id;
    }

    public void setResume_internale_id(String resume_internale_id) {
        this.resume_internale_id = resume_internale_id;
    }

    public List<PreviosWorkHistory> getPrevios() {
        return previos;
    }

    public void setPrevios(List<PreviosWorkHistory> previos) {
        this.previos = previos;
    }
}