package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "history")
@Table
public class History implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "systemtype")          // какой сайт ?
    private int systemtype;

    @Column(name = "type")
    private int type;             //вакансия или резме

    @Column(name = "stacktrace")
    private byte[] stacktrace;

    @Column(name = "date_event")
    private Date dateEvent;

    public History() {
        super();
    }

    public History(byte[] bytes, String url, int i, int type) {
        super();
        this.url = url;
        this.systemtype = i;
        this.type = type;
        stacktrace = bytes;
        this.dateEvent = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(int systemtype) {
        this.systemtype = systemtype;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(byte[] stacktrace) {
        this.stacktrace = stacktrace;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }
}