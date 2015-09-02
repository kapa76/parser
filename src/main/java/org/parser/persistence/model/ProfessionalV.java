package org.parser.persistence.model;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(name = "professional_v")
@Table
public class ProfessionalV implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "professionalV", fetch = FetchType.LAZY )
    private List<ProfessionalDetailV> professionalDetailV;

    @ManyToOne(targetEntity = Vacancy.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacancy", nullable = true)
    private Vacancy vacancylV;

    @Column(name = "name")
    private String name;

    public ProfessionalV() {
        super();
    }

    public ProfessionalV(Professional p) {
        super();
        this.name = p.getProfessionalName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProfessionalDetailV> getProfessionalDetailV() {
        return professionalDetailV;
    }

    public void setProfessionalDetailV(List<ProfessionalDetailV> professionalDetailV) {
        this.professionalDetailV = professionalDetailV;
    }

    public Vacancy getVacancylV() {
        return vacancylV;
    }

    public void setVacancylV(Vacancy vacancylV) {
        this.vacancylV = vacancylV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
