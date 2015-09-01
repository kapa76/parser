package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "place_detail")
@Table
public class PlaceDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "option_descr")
    private String optionDescr;
    @Column(name = "detail")
    private String detail;
    @Column(name = "date_from")
    private Date dateFrom;
    @Column(name = "date_to")
    private Date dateTo;
    @Column(name = "company")
    private String company;
    @ManyToOne(targetEntity = Place.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_place")
    private Place place;
    @ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city")
    private City city;

    public PlaceDetail() {
        super();
    }

    public PlaceDetail(final String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getOptionDescr() {
        return optionDescr;
    }

    public void setOptionDescr(String optionDescr) {
        this.optionDescr = optionDescr;
    }
}
