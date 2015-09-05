package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "word_list")
@Table
public class WordList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_search_word")
    private Long id_search_word;

    public WordList() {
        super();
    }

    public WordList(final String name, final Long words) {
        super();
        this.name = name;
        this.id_search_word = words;
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

    public Long getWords() {
        return id_search_word;
    }

    public void setWords(Long words) {
        this.id_search_word = words;
    }
}