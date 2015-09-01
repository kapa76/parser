package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "city")
@Table
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name", unique = true)
    private String name;

    public City() {
        super();
    }

    public City(final String name) {
        super();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
