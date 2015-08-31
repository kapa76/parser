package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "previos_work")
public class PreviosWorkHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    public PreviosWorkHistory() {
        super();
    }

    public PreviosWorkHistory(final String name) {
        super();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    @Column(name = "profession")
    private String profession;

    @Column(name = "work")
    private String work;

    @ManyToOne(targetEntity = WorkType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_worktype")
    private WorkType type;

    @Column(name = "monthbeg")
    private Integer monthbeg;

    @Column(name = "monthend")
    private Integer monthend;

    @Column(name = "yearbeg")
    private Integer yearbeg;

    @Column(name = "yearend")
    private Integer yearend;

    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resume")
    private Resume resume;


}
