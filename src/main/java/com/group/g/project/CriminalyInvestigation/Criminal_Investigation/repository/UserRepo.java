package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.repository;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepo extends CrudRepository<User,Long> {
}
