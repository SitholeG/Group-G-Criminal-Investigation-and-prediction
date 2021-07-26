package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "case_results")
public class CaseResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "case_results")
    private String caseResults;
    @Column(name = "date")
    private Date date;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="case_id")
    private Case aCase;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaseResults() {
        return caseResults;
    }

    public void setCaseResults(String caseResults) {
        this.caseResults = caseResults;
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
        return "CaseResults{" +
                "id=" + id +
                ", caseResults='" + caseResults + '\'' +
                ", date=" + date +
                ", aCase=" + aCase +
                ", user=" + user +
                '}';
    }
}
