package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "[case]")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "[case_name]")
    private String caseName;
    @Column(name = "status")
    private String status;
    @Column(name = "[note]")
    private String note;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cases_users",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @OneToMany(mappedBy="aCase",
            cascade= {CascadeType.MERGE,
                     CascadeType.REFRESH})
    private Collection<Suspect> suspects;
    @OneToMany(mappedBy="aCase",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private Collection<Evidence> evidence;

    public Case(){

    }

    public Case(int id, String caseName, String note, Collection<User> users) {
        this.id = id;
        this.caseName = caseName;
        this.note = note;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Suspect> getSuspects() {
        return suspects;
    }

    public void setSuspects(Collection<Suspect> suspects) {
        this.suspects = suspects;
    }

    public Collection<Evidence> getEvidence() {
        return evidence;
    }

    public void setEvidence(Collection<Evidence> evidence) {
        this.evidence = evidence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", caseName='" + caseName + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", users=" + users +
                ", suspects=" + suspects +
                ", evidence=" + evidence +
                '}';
    }
}
