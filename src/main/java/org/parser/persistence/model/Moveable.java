package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "moveable")
public class Moveable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = Site.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    private Site site;

    public Moveable() {
        super();
    }

    public Moveable(final String name) {
        super();
        this.name = name;
    }

    public Moveable(String value, Site siteDefault) {
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