package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "professional_detail_v")
@Table
public class ProfessionalDetailR implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "name")
    private String name;

    @ManyToOne(targetEntity = ProfessionalR.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professional_r")
    private ProfessionalR professionalR;

    public ProfessionalDetailR() {
        super();
    }

    public ProfessionalDetailR(ProfessionalDetail pd) {
        super();
        this.name = pd.getProfessionalDetailName();
    }

    public ProfessionalDetailR(ProfessionalDetail pd, ProfessionalR pv) {
        super();
        this.name = pd.getProfessionalDetailName();
        this.professionalR = pv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProfessionalR getProfessionalV() {
        return professionalR;
    }

    public void setProfessionalV(ProfessionalV professionalV) {
        this.professionalR = professionalR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
