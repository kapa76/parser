package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "education_history")
public class EducationHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "town")
    private String town;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "profession")
    private String profession;
    @ManyToOne(targetEntity = EducationTypeResume.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_education_type")
    private EducationTypeResume educationType;
    @ManyToOne(targetEntity = EducationFormResume.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_education_form")
    private EducationFormResume educationForm;
    @Column(name = "yearend")
    private Integer yearend;
    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resume")
    private Resume resume;

    public EducationHistory() {
        super();
    }

    public EducationHistory(final String name) {
        super();
        this.name = name;
    }
}