package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao;


import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;

import java.util.Collection;

public interface UserDao {

    public User findByUserName(String userName);

    public Collection<User> getAllUsers();

    public Collection<User> getOfficerUsers();
    
    public void save(User user);

    void upDateUser(User user);
}
