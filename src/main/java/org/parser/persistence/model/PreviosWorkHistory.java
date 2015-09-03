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

    @Column(name = "from_to")
    private String from_to;

    @Column(name = "place_descr")
    private byte[] place_descr;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "description")
    private byte[] description;


    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume")
    private Resume resume;

    public PreviosWorkHistory() {
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom_to() {
        return from_to;
    }

    public void setFrom_to(String from_to) {
        this.from_to = from_to;
    }

    public byte[] getPlace_descr() {
        return place_descr;
    }

    public void setPlace_descr(byte[] place_descr) {
        this.place_descr = place_descr;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public byte[] getDescription() {
        return description;
    }

    public void setDescription(byte[] description) {
        this.description = description;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}
