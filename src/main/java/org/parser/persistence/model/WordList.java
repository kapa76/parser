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

    @ManyToOne(targetEntity = SearchWords.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_search_word")
    private SearchWords words;

    public WordList() {
        super();
    }

    public WordList(final String name, final SearchWords words) {
        super();
        this.name = name;
        this.words = words;
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

    public SearchWords getWords() {
        return words;
    }

    public void setWords(SearchWords words) {
        this.words = words;
    }
}