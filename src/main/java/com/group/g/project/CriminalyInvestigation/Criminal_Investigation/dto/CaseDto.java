package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Evidence;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Suspect;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

public class CaseDto {
    private long id;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String caseName;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String note;
    @NotNull(message = "is required")
    private String status;
    private  Collection<String> officerName;
    private Collection<Suspect> suspects;
    private Collection<Evidence> evidence;

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

    public Collection<String> getOfficerName() {
        return officerName;
    }

    public void setOfficerName(Collection<String> officerName) {
        this.officerName = officerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "caseDto{" +
                "id=" + id +
                ", caseName='" + caseName + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", officerName=" + officerName +
                ", suspects=" + suspects +
                ", evidence=" + evidence +
                '}';
    }
}
