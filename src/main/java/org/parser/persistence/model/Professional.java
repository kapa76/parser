package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Professional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "professional_name")
    private String professionalName;
    @ManyToOne(targetEntity = Site.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_site")
    private Site site;
    @OneToMany(mappedBy = "professional", fetch = FetchType.EAGER)
    private Set<ProfessionalDetail> professionalDetail;

    public Professional() {
        super();
    }

    public Professional(final Site site, final String professionalName) {
        super();

        this.professionalName = professionalName;
        this.site = site;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public Set<ProfessionalDetail> getProfessionalDetail() {
        return professionalDetail;
    }

    public void setProfessionalDetail(Set<ProfessionalDetail> professionalDetail) {
        this.professionalDetail = professionalDetail;
    }
}
