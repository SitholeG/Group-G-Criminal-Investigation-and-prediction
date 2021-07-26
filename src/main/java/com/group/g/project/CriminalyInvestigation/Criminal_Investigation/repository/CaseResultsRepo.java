package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.repository;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.ActionLogs;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.CaseResults;
import org.springframework.data.repository.CrudRepository;

public interface CaseResultsRepo extends CrudRepository<CaseResults,Long> {
}
