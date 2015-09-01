package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "professional_v")
@Table
public class ProfessionalV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "professionalV", fetch = FetchType.LAZY)
    private Set<ProfessionalDetailV> professionalDetailV;

    @ManyToOne(targetEntity = Vacancy.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacancy")
    private Vacancy vacancylV;

    public ProfessionalV() {
        super();
    }

    public ProfessionalV(final String name) {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProfessionalDetailV> getProfessionalDetailV() {
        return professionalDetailV;
    }

    public void setProfessionalDetailV(Set<ProfessionalDetailV> professionalDetailV) {
        this.professionalDetailV = professionalDetailV;
    }

    public Vacancy getVacancylV() {
        return vacancylV;
    }

    public void setVacancylV(Vacancy vacancylV) {
        this.vacancylV = vacancylV;
    }
}