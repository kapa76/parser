package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="task_link")
@Table
public class TaskLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "link")
    private String link;

    @Column(name = "processed")
    private boolean processed;

    @Column(name = "html")
    private byte[] html;

    @Column(name = "qty_entity")
    private Integer qty_entity;

    public TaskLink() {
        super();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public byte[] getHtml() {
        return html;
    }

    public void setHtml(byte[] html) {
        this.html = html;
    }

    public Integer getQty_entity() {
        return qty_entity;
    }

    public void setQty_entity(Integer qty_entity) {
        this.qty_entity = qty_entity;
    }
}
