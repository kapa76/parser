package org.parser.persistence.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ProfessionalDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    public ProfessionalDetail() {
        super();
    }

    public ProfessionalDetail(final Professional professional, final String professionalDetailName) {
        super();

        this.professionalDetailName = professionalDetailName;
        this.professional = professional;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "detail_name")
    private String professionalDetailName;

    @ManyToOne(targetEntity = Professional.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_professional")
    private Professional professional;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public String getProfessionalDetailName() {
        return professionalDetailName;
    }

    public void setProfessionalDetailName(String professionalDetailName) {
        this.professionalDetailName = professionalDetailName;
    }
}
