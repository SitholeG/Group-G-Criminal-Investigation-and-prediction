package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "evidence")
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "evidence")
    private String evidence;
    @Column(name = "type")
    private String type;
    @Column(name = "rank")
    private int rank;
    @Column(name = "note")
    private String note;
    @Column(name = "date")
    private Date date;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="case_id")
    private Case aCase;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="suspect_id")
    private Suspect suspect;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Suspect getSuspect() {
        return suspect;
    }

    public void setSuspect(Suspect suspect) {
        this.suspect = suspect;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "id=" + id +
                ", evidence='" + evidence + '\'' +
                ", type='" + type + '\'' +
                ", rank='" + rank + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                ", aCase=" + aCase +
                ", suspect=" + suspect +
                ", user=" + user +
                '}';
    }
}
