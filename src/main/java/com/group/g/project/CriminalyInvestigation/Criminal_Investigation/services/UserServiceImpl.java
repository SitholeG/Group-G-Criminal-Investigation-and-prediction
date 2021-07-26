package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao.RoleDao;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao.UserDao;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Role;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setArea(userDto.getArea());
		user.setMobile(userDto.getMobile());

		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_OFFICER")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Collection<User> getOfficerUsers() {
		return userDao.getAllUsers();
	}

	@Override
	@Transactional
	public void upDate(UserDto theUser) {

		User user = new User();
		// assign user details to the user object
		user.setId(theUser.getId());
		user.setUserName(theUser.getUserName());
		user.setPassword(passwordEncoder.encode(theUser.getPassword()));
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		user.setEmail(theUser.getEmail());
		user.setArea(theUser.getArea());
		user.setMobile(theUser.getMobile());
		User user1 = userDao.findByUserName(user.getUserName());
		user.setRoles(user1.getRoles());
		user.setCases(user1.getCases());
		//user.setEvidence(user1.getEvidence());


		// save user in the database
		userDao.upDateUser(user);

	}

	@Override
	@Transactional
	public void saveAdmin(UserDto userDto) {
		User user = new User();
		// assign user details to the user object
		user.setUserName(userDto.getUserName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setArea(userDto.getArea());
		user.setMobile(userDto.getMobile());

		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_ADMIN")));

		// save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
