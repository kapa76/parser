package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity (name="place")
@Table
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    public Place() {
        super();
    }

    public Place(final String name) {
        super();
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private Set<PlaceDetail> placeDetail;

    @ManyToOne(targetEntity = Resume.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_resume")
    private Resume resume;


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


    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Set<PlaceDetail> getPlaceDetail() {
        return placeDetail;
    }

    public void setPlaceDetail(Set<PlaceDetail> placeDetail) {
        this.placeDetail = placeDetail;
    }
}
