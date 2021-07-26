package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.repository;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Evidence;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Suspect;
import org.springframework.data.repository.CrudRepository;

public interface EvidenceRepo extends CrudRepository<Evidence,Integer> {
}
