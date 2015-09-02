package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "professional_detail_v")
@Table
public class ProfessionalDetailV implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "name")
    private String name;

    @ManyToOne(targetEntity = ProfessionalV.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professional_v")
    private ProfessionalV professionalV;

    public ProfessionalDetailV() {
        super();
    }

    public ProfessionalDetailV(ProfessionalDetail pd) {
        super();
        this.name = pd.getProfessionalDetailName();
    }

    public ProfessionalDetailV(ProfessionalDetail pd, ProfessionalV pv) {
        super();
        this.name = pd.getProfessionalDetailName();
        this.professionalV = pv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProfessionalV getProfessionalV() {
        return professionalV;
    }

    public void setProfessionalV(ProfessionalV professionalV) {
        this.professionalV = professionalV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
