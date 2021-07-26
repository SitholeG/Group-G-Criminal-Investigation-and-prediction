package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Case;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class CaseResultsDto {

    private long id;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String caseResults;
    private Date date;
    private CaseDto caseDto;
    private UserDto userDto;

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

    public CaseDto getCaseDto() {
        return caseDto;
    }

    public void setCaseDto(CaseDto caseDto) {
        this.caseDto = caseDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "CaseResultsDto{" +
                "id=" + id +
                ", caseResults='" + caseResults + '\'' +
                ", date=" + date +
                ", caseDto=" + caseDto +
                ", userDto=" + userDto +
                '}';
    }
}

