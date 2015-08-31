package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="education_form_resume")
public class EducationFormResume implements Serializable {

    private static final long serialVersionUID = 1L;

    public EducationFormResume() {
        super();
    }

    public EducationFormResume(final String name) {
        super();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Site.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_site")
    private Site site;

    public EducationFormResume(String value, Site siteDefault) {
        super();
        this.name = value;
        this.site = siteDefault;
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

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}