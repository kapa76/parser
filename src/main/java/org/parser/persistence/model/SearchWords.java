package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "search_words")
@Table
public class SearchWords implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "word_name")
    private String word_name;

    @Column(name = "system")
    private Integer system;

    public SearchWords() {
        super();
    }

    public SearchWords(String name, int system) {
        super();
        this.word_name = name;
        this.system = system;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }
}
