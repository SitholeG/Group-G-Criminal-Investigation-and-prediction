package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao;


import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.*;

import java.util.Collection;
import java.util.Optional;

public interface CaseDao {

    public Optional<Case> getCase(long id);

    public void save(Case aCase, String name);

    public Collection<Case> getCases();

    Collection<Case> getCases(String userName);

    void addSuspect(Suspect suspect, String name);

    Optional<Suspect> getSuspect(long theId);

    void addEvidence(Evidence evidence);

    Collection<ActionLogs> getCaseLogs(int id);

    void concludeCase(CaseResults caseResults);
}
