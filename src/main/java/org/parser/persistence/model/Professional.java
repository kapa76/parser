package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(name="professional")
@Table
public class Professional implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "professional_name")
    private String professionalName;

    @ManyToOne(targetEntity = Site.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    private Site site;

    @OneToMany(mappedBy = "professional", fetch = FetchType.LAZY)
    private List<ProfessionalDetail> professionalDetail;

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

    public List<ProfessionalDetail> getProfessionalDetail() {
        return professionalDetail;
    }

    public void setProfessionalDetail(List<ProfessionalDetail> professionalDetail) {
        this.professionalDetail = professionalDetail;
    }
}
