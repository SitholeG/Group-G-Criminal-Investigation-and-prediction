package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.*;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.repository.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;


@Repository
public class CaseDaoImpl implements CaseDao {

	@Autowired
	CaseRepo caseRepo;
	@Autowired
	SuspectRepo suspectRepo;
	@Autowired
	EvidenceRepo evidenceRepo;
	@Autowired
	ActionLogsRepo actionLogsRepo;
	@Autowired
	UserDao userDao;
	@Autowired
	CaseResultsRepo caseResultsRepo;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Optional<Case> getCase(long id) {
		return caseRepo.findById(id);

	}

	@Override
	public void save(Case aCase, String name) {
		Case aCase1 =  caseRepo.save(aCase);
		ActionLogs actionLogs = new ActionLogs("Case added",new Date(),
				aCase1,userDao.findByUserName(name));
		actionLogsRepo.save(actionLogs);
	}

	@Override
	public Collection<Case> getCases() {
		Collection<Case> cases = new ArrayList<>();
		caseRepo.findAll().forEach(cases::add);
		return cases;
	}

	@Override
	public Collection<Case> getCases(String userName) {
		return null;
	}

	@Override
	public void addSuspect(Suspect suspect, String name) {
		suspectRepo.save(suspect);
		ActionLogs actionLogs = new ActionLogs("New suspect added",new Date(),
				suspect.getaCase(),userDao.findByUserName(name));

		actionLogsRepo.save(actionLogs);
	}

	@Override
	public Optional<Suspect> getSuspect(long theId) {
		return suspectRepo.findById(theId);
	}

	@Override
	public void addEvidence(Evidence evidence) {
		evidenceRepo.save(evidence);
		ActionLogs actionLogs = new ActionLogs("New Evidence added",new Date(),
				evidence.getaCase(),evidence.getUser());
		actionLogsRepo.save(actionLogs);
	}

	@Override
	public Collection<ActionLogs> getCaseLogs(int id) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		Query<ActionLogs> theQuery = currentSession.createQuery("from ActionLogs where case_id=:caseId", ActionLogs.class);
		theQuery.setParameter("caseId", id);
		Collection<ActionLogs> theActionLogs = null;
		try {
			theActionLogs = theQuery.getResultList();
		} catch (Exception e) {
			theActionLogs = null;
		}

		return theActionLogs;
	}

	@Override
	public void concludeCase(CaseResults caseResults) {
		Case aCase = caseResults.getaCase();
		aCase.setStatus("Close");
		caseRepo.save(aCase);
		caseResultsRepo.save(caseResults);
	}

}
