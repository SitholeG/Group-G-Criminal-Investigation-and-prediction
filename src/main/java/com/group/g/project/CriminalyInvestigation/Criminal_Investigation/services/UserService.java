package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services;


import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(UserDto crmUser);

	public Collection<User> getAllUsers();

	public Collection<User> getOfficerUsers();

    void upDate(UserDto theUser);

    void saveAdmin(UserDto userDto);
}
