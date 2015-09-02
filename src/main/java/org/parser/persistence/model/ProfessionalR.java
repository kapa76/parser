package org.parser.persistence.model;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(name = "professional_r")
@Table
public class ProfessionalR implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "professionalR", fetch = FetchType.LAZY )
    private List<ProfessionalDetailR> professionalDetailR;

    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resume", nullable = true)
    private Resume resume;

    @Column(name = "name")
    private String name;

    public ProfessionalR() {
        super();
    }

    public ProfessionalR(Professional p) {
        super();
        this.name = p.getProfessionalName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProfessionalDetailR> getProfessionalDetailR() {
        return professionalDetailR;
    }

    public void setProfessionalDetailV(List<ProfessionalDetailR> professionalDetailV) {
        this.professionalDetailR = professionalDetailV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
