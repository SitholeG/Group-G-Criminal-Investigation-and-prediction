package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao.CaseDao;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dao.UserDao;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.*;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CaseServiceImpl implements CaseService{
    @Autowired
    private CaseDao caseDao;

    @Autowired
    private UserDao userDao;

    @Override
    public CaseDto getCase(long id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Case> aCase = caseDao.getCase(id);
        Case aCase1 = aCase.get();
        CaseDto caseDto1 = mapper.map(aCase1,new TypeToken<CaseDto>(){}.getType());
        CaseDto caseDto = new CaseDto();
        caseDto.setId(aCase.get().getId());
        caseDto.setCaseName(aCase.get().getCaseName());
        caseDto.setNote(aCase.get().getNote());
        Collection<String> officerName = new ArrayList<>();
        for (User name : aCase.get().getUsers()){
            officerName.add(name.getUserName());
        }
        caseDto.setOfficerName(officerName);
        return caseDto1;
    }

    @Override
    public void save(CaseDto aCase, String name) {
        Case aCase1 = new Case();
        aCase1.setCaseName(aCase.getCaseName());
        aCase1.setNote(aCase.getNote());
        aCase1.setStatus(aCase.getStatus());
        Collection<User> users = new ArrayList<>();
        for(String username: aCase.getOfficerName()){
            users.add(userDao.findByUserName(username));
        }
        aCase1.setUsers(users);
        caseDao.save(aCase1,name);
    }

    @Override
    public Collection<CaseDto> getCases() {
        ModelMapper mapper = new ModelMapper();
        Collection<Case> cases =  caseDao.getCases();
        return mapper.map(cases,new TypeToken<Collection<CaseDto>>(){}.getType());
    }

    @Override
    public Collection<CaseDto> getCases(String userName) {
        ModelMapper mapper = new ModelMapper();
        Collection<Case> cases =  caseDao.getCases(userName);
        return mapper.map(cases,new TypeToken<List<CaseDto>>(){}.getType());
    }

    @Override
    public void addSuspect(SuspectDto suspectDto, String name) {
        ModelMapper mapper = new ModelMapper();
        CaseDto caseDto = suspectDto.getCaseDto();
        Case aCase = mapper.map(caseDto,new TypeToken<Case>(){}.getType());
        Suspect suspect = mapper.map(suspectDto,new TypeToken<Suspect>(){}.getType());
        suspect.setaCase(aCase);
        caseDao.addSuspect(suspect, name);
    }

    @Override
    public SuspectDto getSuspect(long theId) {
        Suspect suspect = caseDao.getSuspect(theId).get();
        Case aCase = suspect.getaCase();
        Collection<Evidence> evidence = suspect.getEvidence();

        ModelMapper mapper = new ModelMapper();
        CaseDto caseDto = mapper.map(aCase,new TypeToken<CaseDto>(){}.getType());
        Collection<EvidenceDto> evidenceDtos = mapper.map(evidence,
                new TypeToken<Collection<EvidenceDto>>(){}.getType());
        SuspectDto suspectDto = mapper.map(suspect,new TypeToken<SuspectDto>(){}.getType());

        suspectDto.setCaseDto(caseDto);
        suspectDto.setEvidence(evidenceDtos);
        return suspectDto;
    }

    @Override
    public void addEvidence(EvidenceDto evidenceDto,String username) {
        CaseDto caseDto = evidenceDto.getSuspect().getCaseDto();
        SuspectDto suspectDto = evidenceDto.getSuspect();
        UserDto userDto = evidenceDto.getUserDto();
        ModelMapper mapper = new ModelMapper();
        Case aCase = mapper.map(caseDto,new TypeToken<Case>(){}.getType());
        Suspect suspect = mapper.map(suspectDto,new TypeToken<Suspect>(){}.getType());
        User user = userDao.findByUserName(username);
        Evidence evidence = mapper.map(evidenceDto,new TypeToken<Evidence>(){}.getType());
        evidence.setaCase(aCase);
        evidence.setSuspect(suspect);
        evidence.setUser(user);
        caseDao.addEvidence(evidence);
    }

    @Override
    public SuspectDto getPredictedSuspect(int theId) {
        ModelMapper mapper = new ModelMapper();
        int currentEvidenceRank = 0;
        int sum = 0;
        Suspect suspect = new Suspect();
        Collection<Suspect> suspects = caseDao.getCase(theId).get().getSuspects();
        for(Suspect x: suspects){
            for (Evidence e: x.getEvidence()){
                sum += e.getRank();
            }
            if(sum>currentEvidenceRank){
                currentEvidenceRank = sum;
                suspect = x;
                sum = 0;
            }
        }
        Collection<EvidenceDto> evidenceDto = mapper.map(suspect.getEvidence(), new TypeToken<Collection<EvidenceDto>>(){}.getType());
        CaseDto caseDto = mapper.map(caseDao.getCase(theId).get(),new TypeToken<CaseDto>(){}.getType());
        SuspectDto suspectDto = mapper.map(suspect,new TypeToken<SuspectDto>(){}.getType());
        suspectDto.setCaseDto(caseDto);
        suspectDto.setEvidence(evidenceDto);
        return  suspectDto;
    }

    @Override
    public Collection<ActionLogsDto> getCaseLogs(int id) {
        ModelMapper mapper = new ModelMapper();
        Collection<ActionLogs> actionLogs = caseDao.getCaseLogs(id);
        Collection<ActionLogsDto> actionLogsDtos = new ArrayList<>();
        for(ActionLogs logs: actionLogs){
            ActionLogsDto actionLogsDto = mapper.map(logs,new TypeToken<ActionLogsDto>(){}.getType());
            User user = logs.getUser();
            Case aCase = logs.getaCase();
            UserDto userDto = mapper.map(user,new TypeToken<UserDto>(){}.getType());
            CaseDto caseDto = mapper.map(aCase,new TypeToken<CaseDto>(){}.getType());
            actionLogsDto.setUserDto(userDto);
            actionLogsDto.setCaseDto(caseDto);
            actionLogsDtos.add(actionLogsDto);
        }
        return actionLogsDtos;
    }

    @Override
    public void concludeCase(CaseResultsDto caseResultsDto, long caseId, String username) {
        ModelMapper mapper = new ModelMapper();
        User user = userDao.findByUserName(username);
        Case aCase = caseDao.getCase(caseId).get();
        CaseResults caseResults = mapper.map(caseResultsDto,new TypeToken<CaseResults>(){}.getType());
        caseResults.setaCase(aCase);
        caseResults.setUser(user);

        caseDao.concludeCase(caseResults);
    }
}
