package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.controllers;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.*;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Role;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services.CaseService;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;


@Controller
@RequestMapping("/case")
public class CaseController {
	
    @Autowired
    private UserService userService;

    @Autowired
	CaseService caseService;

    private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

		dataBinder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true, 10));
	}

	@GetMapping("/showAddCaseForm")
	public String showMyLoginPage(Model theModel) {
		ModelMapper mapper = new ModelMapper();
		Collection<User> users = userService.getOfficerUsers();
		Collection<String> oficername = new ArrayList<>();
		//Collection<CrmUser> crmUser = new ArrayList<>();
		//Collection<CrmUser> crmUser = mapper.map(users,new TypeToken<List<CrmUser>>(){}.getType());
		//mapper.map()
		for(User x: users) {
			for(Role i: x.getRoles())
			if (i.getName().equals("ROLE_OFFICER")){
				oficername.add(x.getUserName());
			}

		}
//		List<User> users = new ArrayList<>();
//		userRepo.findAll().forEach(users::add);

		CaseDto aCase = new CaseDto();

		//aCase.setUsers(crmUser);
		aCase.setOfficerName(oficername);
		theModel.addAttribute("crmcase", aCase);

		return "addCase-form";
	}

	@PostMapping("/processAddCaseForm")
	public String processAddCaseForm(
				@Valid @ModelAttribute("caseDto") CaseDto theCaseDto,
				BindingResult theBindingResult,
				Model theModel,Principal principal) {

		String caseName = theCaseDto.getCaseName();
		logger.info("Processing Add case form for: " + caseName);

		// form validation
		 if (theBindingResult.hasErrors()){
			 ModelMapper mapper = new ModelMapper();
			 Collection<User> users = userService.getOfficerUsers();
			 Collection<String> oficername = new ArrayList<>();

			 for(User x: users)
			 {

					oficername.add(x.getUserName());

			 }
			 CaseDto aCase = new CaseDto();
			 aCase.setOfficerName(oficername);
			 theModel.addAttribute("caseDto", aCase);
			 return "addCase-form";
		 }

		caseService.save(theCaseDto,principal.getName());

        logger.info("Successfully created user: " + caseName);

        return "AddCase-confirmation";
	}

	@GetMapping("/getCases")
	public String getCase(Model theModel) {

		// get cases from db
		Collection<CaseDto> theCases = caseService.getCases();
		Collection<CaseDto> openCases = new ArrayList<>();
		for(CaseDto caseDto: theCases){
			if(caseDto.getStatus().equals("Active")){
				theCases.add(caseDto);
			}
		}

		// add to the spring model
		theModel.addAttribute("cases", openCases);

		return "view-cases";
	}

	@GetMapping("/getCase")
	public String getCase(@RequestParam("caseId") long theId,Model theModel) {

		// get cases from db
		CaseDto theCase = caseService.getCase(theId);

		// add to the spring model
		theModel.addAttribute("case", theCase);

		return "view-case";
	}
	@GetMapping("/showAddEvidenceForm")
	public String showAddEvidencePage(@RequestParam("suspectId") int theId,Model theModel) {
		theModel.addAttribute("evidence", new EvidenceDto());
		theModel.addAttribute("suspectId",theId);
		theModel.addAttribute("case", caseService.getSuspect(theId).getCaseDto());
		return "Add-evidence-form";
	}

	@GetMapping("/showAddSuspectForm")
	public String showAddSuspectFormPage(@RequestParam("caseId") int theId,Model theModel) {
		CaseDto theCase = caseService.getCase(theId);
		SuspectDto suspectDto = new SuspectDto();
		suspectDto.setCaseDto(theCase);
		theModel.addAttribute("suspect", suspectDto);
		theModel.addAttribute("caseId",theId);
		return "Add-suspect-form";
	}
	@PostMapping("/processAddSuspectForm")
	public String processAddSuspectForm(@RequestParam("caseId") int theId,
										@Valid @ModelAttribute("suspect") SuspectDto suspectDto,
										BindingResult theBindingResult,
										Model theModel, Authentication authentication, Principal principal) {
		CaseDto theCase = caseService.getCase(theId);
		suspectDto.setCaseDto(theCase);
		String name = suspectDto.getSuspectName();
		principal.getName();
		logger.info("Processing Add suspect form for: " + name);

		// form validation
		if (theBindingResult.hasErrors()){
//			caseDto theCase = caseService.getCase(suspectDto.getCaseDto().getId());
//			SuspectDto suspectDto = new SuspectDto();
//			suspectDto.setCaseDto(theCase);
			theModel.addAttribute("suspect", suspectDto);
			return "Add-suspect-form";
		}

		caseService.addSuspect(suspectDto,principal.getName());
		theModel.addAttribute("caseId", theId);
		return "Add-suspect-confirmation";
	}

	@PostMapping("/processAddEvidenceForm")
	public String processRegistrationForm(@RequestParam("suspectId") long theId,@RequestParam("username") String username,
			@Valid @ModelAttribute("evidence") EvidenceDto evidenceDto,
			BindingResult theBindingResult,
			Model theModel) {
		ModelMapper mapper = new ModelMapper();
		SuspectDto suspectDto = caseService.getSuspect(theId);
		CaseDto caseDto = suspectDto.getCaseDto();
		String evidence = evidenceDto.getEvidence();
		evidenceDto.setDate(new Date());
		evidenceDto.setSuspect(suspectDto);
		evidenceDto.setCaseDto(caseDto);
		//evidenceDto.setUserDto(mapper.map(user, new TypeToken<UserDto>(){}.getType()));
		logger.info("Processing Add evidence form for: " + evidence);


		if (theBindingResult.hasErrors()){
			return "Add-Evidence-form";
		}

		// Add evidence
		caseService.addEvidence(evidenceDto,username);

		logger.info("Successfully Added evidence " + evidence);

		return "Add-Evidence-confirmation";
	}

	@GetMapping("/showPredictionPage")
	public String showPredictionPage(@RequestParam("caseId") int theId,Model theModel) {
		SuspectDto suspectDto = caseService.getPredictedSuspect(theId);
		theModel.addAttribute("suspect", suspectDto);
		return "prediction-page";
	}

	@GetMapping("/getCaseHistory")
	public String getCaseHistory(@RequestParam("caseId") int theId,Model theModel) {
		Collection<ActionLogsDto> actionLogsDto = caseService.getCaseLogs(theId);

		theModel.addAttribute("caseHistory", actionLogsDto);
		return "case-history";
	}
	@GetMapping("/showConcludeForm")
	public String showConcludeForm(@RequestParam("caseId") int theId,Model theModel) {
		theModel.addAttribute("caseResults" , new CaseResultsDto());
		theModel.addAttribute("caseId", theId);
		return "case-conclude-form";
	}
//	processConcludeCaseForm

	@PostMapping("/processConcludeCaseForm")
	public String processConcludeCaseForm(@RequestParam("caseId") long caseId,@RequestParam("username") String username,
										  @Valid @ModelAttribute("caseResults") CaseResultsDto caseResultsDto,
										  BindingResult theBindingResult,
										  Model theModel) {
		caseResultsDto.setDate(new Date());
		caseService.concludeCase(caseResultsDto,caseId,username);

		return "case-conclude-success";
	}

	@GetMapping("/viewEvidence")
	public String viewEvidence(@RequestParam("suspectId") long theId, Model theModel) {
		Collection<EvidenceDto> evidenceDto = caseService.getSuspect(theId).getEvidence();
		theModel.addAttribute("evidence", evidenceDto);
		theModel.addAttribute("suspect",caseService.getSuspect(theId));
		theModel.addAttribute("case", caseService.getSuspect(theId).getCaseDto());
		return "view-evidence";
	}
}
