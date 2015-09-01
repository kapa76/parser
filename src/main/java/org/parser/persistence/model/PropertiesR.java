package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "properties_r")
public class PropertiesR implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "value")
    private String value;
    @ManyToOne(targetEntity = Language.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_language")
    private Language language;
    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resume")
    private Resume resume;

    public PropertiesR() {
        super();
    }

    public PropertiesR(final String value) {
        super();
        this.value = value;
    }


}
