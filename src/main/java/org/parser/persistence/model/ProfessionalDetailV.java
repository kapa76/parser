package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "professional_detail_v")
@Table
public class ProfessionalDetailV implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = ProfessionalDetail.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professional")
    private ProfessionalDetail professionalDetail;

    @ManyToOne(targetEntity = ProfessionalV.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacancy")
    private ProfessionalV professionalV;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProfessionalDetail getProfessionalDetail() {
        return professionalDetail;
    }

    public void setProfessionalDetail(ProfessionalDetail professionalDetail) {
        this.professionalDetail = professionalDetail;
    }

    public ProfessionalV getProfessionalV() {
        return professionalV;
    }

    public void setProfessionalV(ProfessionalV professionalV) {
        this.professionalV = professionalV;
    }
}