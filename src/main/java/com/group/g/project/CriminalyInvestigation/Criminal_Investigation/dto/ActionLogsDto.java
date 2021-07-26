package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto;

import java.util.Date;


public class ActionLogsDto {
    private long id;
    private String action;
    private Date date;
    private CaseDto caseDto;
    private UserDto userDto;

    public ActionLogsDto() {
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
        return "ActionLogsDto{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", date=" + date +
                ", caseDto=" + caseDto +
                ", userDto=" + userDto +
                '}';
    }
}
