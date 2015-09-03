package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;


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

    @Column(name = "msg")
    private String msg;

    @Column(name = "systemtype")
    private Integer systemtype;

    @Column(name = "type")
    private Integer type;

    public History() {
        super();
    }

    public History(String msg, String url, int systemtype, int type) {
        super();
        this.msg = msg;
        this.url = url;
        this.systemtype = systemtype;
        this.type = type;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(Integer systemtype) {
        this.systemtype = systemtype;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

