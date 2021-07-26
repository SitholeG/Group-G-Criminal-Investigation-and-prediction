package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

public class SuspectDto {
    private Long id;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String suspectName;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String mobile;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String suspectAddress;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String suspectRelationship;
    @Min(1)
    @Max(10)
    private int rank;
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String note;
    private CaseDto caseDto;
    private Collection<EvidenceDto> evidence;

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

    public CaseDto getCaseDto() {
        return caseDto;
    }

    public void setCaseDto(CaseDto caseDto) {
        this.caseDto = caseDto;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Collection<EvidenceDto> getEvidence() {
        return evidence;
    }

    public void setEvidence(Collection<EvidenceDto> evidence) {
        this.evidence = evidence;
    }

    @Override
    public String toString() {
        return "SuspectDto{" +
                "id=" + id +
                ", suspectName='" + suspectName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", suspectAddress='" + suspectAddress + '\'' +
                ", suspectRelationship='" + suspectRelationship + '\'' +
                ", rank=" + rank +
                ", caseDto=" + caseDto +
                ", evidence=" + evidence +
                '}';
    }
}
