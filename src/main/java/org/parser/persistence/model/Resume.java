package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "predlagaem")
    private byte[] predlagaem;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "company_addr")
    private String company_addr;

    @Column(name = "vacancy_internale_id")
    private String vacancy_internale_id;

    public Resume() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}