package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao;

import javax.persistence.EntityManager;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.repository.UserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private EntityManager entityManager;

	@Override
	public User  findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return  users;
	}

	@Override
	public Collection<User> getOfficerUsers() {
		//return userRepo.findAll();
		return null;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public void upDateUser(User user) {
		userRepo.save(user);
	}

}
