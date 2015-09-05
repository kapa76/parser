package org.parser.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="history_load")
@Table
public class HistoryLoad implements Serializable {

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
    private byte[] html;  //целиковая страница общая, со всем списком

    @Column(name = "qty_entity")
    private Integer qty_entity;

    @Column(name = "record_type")
    private Integer record_type; //вакансия или резюме ?

    @Column(name = "starttime")
    private Date startTime; //начали страницу

    @Column(name = "endtime")
    private Date endTime; //закончили страницу

    @ManyToOne(targetEntity = TaskLink.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_task_link")
    private TaskLink taskLink;

    public HistoryLoad() {
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

    public Integer getRecord_type() {
        return record_type;
    }

    public void setRecord_type(Integer record_type) {
        this.record_type = record_type;
    }

    public TaskLink getTaskLink() {
        return taskLink;
    }

    public void setTaskLink(TaskLink taskLink) {
        this.taskLink = taskLink;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
