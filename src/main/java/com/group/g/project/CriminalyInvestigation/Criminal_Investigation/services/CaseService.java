package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.*;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CaseService {

    public CaseDto getCase(long id);
    public void save(CaseDto aCase, String name);
    public Collection<CaseDto> getCases();

    Collection<CaseDto> getCases(String userName);

    void addSuspect(SuspectDto suspectDto, String name);

    SuspectDto getSuspect(long theId);

    void addEvidence(EvidenceDto evidenceDto, String username);

    SuspectDto getPredictedSuspect(int theId);

    Collection<ActionLogsDto> getCaseLogs(int id);

    void concludeCase(CaseResultsDto caseResultsDto, long caseId, String username);
}
