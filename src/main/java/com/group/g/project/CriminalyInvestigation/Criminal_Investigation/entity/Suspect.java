package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "suspect")
public class Suspect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "suspect_name")
    private String suspectName;
    @Column(name = "Mobile_number")
    private String mobile;
    @Column(name = "address")
    private String suspectAddress;
    @Column(name = "relationship")
    private String suspectRelationship;
    @Column(name = "rank")
    private int rank;
    @Column(name = "note")
    private String note;
    @ManyToOne(cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="case_id")
    private  Case aCase;
    @OneToMany(mappedBy="suspect",
            cascade= {CascadeType.MERGE, CascadeType.REFRESH})
    private Collection<Evidence> evidence;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuspectName() {
        return suspectName;
    }

    public void setSuspectName(String suspectName) {
        this.suspectName = suspectName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSuspectAddress() {
        return suspectAddress;
    }

    public void setSuspectAddress(String suspectAddress) {
        this.suspectAddress = suspectAddress;
    }

    public String getSuspectRelationship() {
        return suspectRelationship;
    }

    public void setSuspectRelationship(String suspectRelationship) {
        this.suspectRelationship = suspectRelationship;
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

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public Collection<Evidence> getEvidence() {
        return evidence;
    }

    public void setEvidence(Collection<Evidence> evidence) {
        this.evidence = evidence;
    }

    @Override
    public String toString() {
        return "Suspect{" +
                "id=" + id +
                ", suspectName='" + suspectName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", suspectAddress='" + suspectAddress + '\'' +
                ", suspectRelationship='" + suspectRelationship + '\'' +
                ", rank=" + rank +
                ", note='" + note + '\'' +
                ", aCase=" + aCase +
                ", evidence=" + evidence +
                '}';
    }
}
