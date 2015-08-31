package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "properties_v")
public class PropertiesV implements Serializable {

    private static final long serialVersionUID = 1L;

    public PropertiesV() {
        super();
    }

    public PropertiesV(final String value) {
        super();
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "value")
    private String value;

    @ManyToOne(targetEntity = Language.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_language")
    private Language language;

    @ManyToOne(targetEntity = Vacancy.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_vacancy")
    private Vacancy vacancy;


}
