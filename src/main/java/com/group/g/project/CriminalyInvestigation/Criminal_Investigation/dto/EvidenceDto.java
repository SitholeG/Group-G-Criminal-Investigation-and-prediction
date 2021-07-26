package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Case;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class EvidenceDto {
    private long id;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String evidence;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String type;
    @NotNull(message = "is required")
    @Min(1)
    @Max(10)
    private String rank;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String note;
    private Date date;
    private CaseDto caseDto;
    private SuspectDto suspectDto;
    private UserDto userDto;

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
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

    public SuspectDto getSuspect() {
        return suspectDto;
    }

    public void setSuspect(SuspectDto suspect) {
        this.suspectDto = suspect;
    }

    public CaseDto getCaseDto() {
        return caseDto;
    }

    public void setCaseDto(CaseDto caseDto) {
        this.caseDto = caseDto;
    }

    public SuspectDto getSuspectDto() {
        return suspectDto;
    }

    public void setSuspectDto(SuspectDto suspectDto) {
        this.suspectDto = suspectDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "EvidenceDto{" +
                "id=" + id +
                ", evidence='" + evidence + '\'' +
                ", type='" + type + '\'' +
                ", rank='" + rank + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                ", caseDto=" + caseDto +
                ", suspectDto=" + suspectDto +
                ", userDto=" + userDto +
                '}';
    }
}
