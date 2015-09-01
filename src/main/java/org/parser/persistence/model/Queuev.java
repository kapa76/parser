package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "queuev")
@Table
public class Queuev implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_from")
    private Date dateFrom;
    @Column(name = "date_to")
    private Date dateTo;
    @Column(name = "status")
    private String status;
    @ManyToOne(targetEntity = Vacancy.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vacancy")
    private Vacancy vacancy;

    public Queuev() {
        super();
    }

    public Queuev(final String name) {
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}