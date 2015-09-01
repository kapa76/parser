package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "social_links_resume")
public class SocialLinksResume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = Site.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_site")
    private Site site;

    public SocialLinksResume() {
        super();
    }

    public SocialLinksResume(final String name) {
        super();
        this.name = name;
    }

    public SocialLinksResume(String value, Site site) {
        super();
        this.name = value;
        this.site = site;
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