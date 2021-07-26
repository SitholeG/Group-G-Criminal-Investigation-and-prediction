package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Action_logs")
public class ActionLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "action")
    private String action;
    @Column(name = "date")
    private Date date;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="case_id")
    private Case aCase;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public ActionLogs() {
    }

    public ActionLogs(String action, Date date, Case aCase, User user) {
        this.action = action;
        this.date = date;
        this.aCase = aCase;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ActionLogs{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", aCase=" + aCase +
                ", user=" + user +
                '}';
    }
}
