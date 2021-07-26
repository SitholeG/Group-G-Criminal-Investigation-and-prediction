package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.controllers;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.CaseDto;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Case;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services.CaseService;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
	@Autowired
	CaseService caseService;
	@Autowired
	UserService userService;

	@GetMapping("/")
	public String showHome(Model theModel, Authentication authentication) {
		Collection<CaseDto> theCases = new ArrayList<>();
		Collection<CaseDto> openCases = new ArrayList<>();
		authentication.getAuthorities();
		boolean hasAdminRole = false;
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			hasAdminRole = authority.getAuthority().equals("ROLE_ADMIN");
			if (hasAdminRole) {
				break;
			}
		}
		if(hasAdminRole){
			theCases = caseService.getCases();


		}else {

			ModelMapper mapper = new ModelMapper();
			Collection<Case> cases =  userService.findByUserName(authentication.getName()).getCases();
			theCases = mapper.map(cases,new TypeToken<List<CaseDto>>(){}.getType());
		}

		openCases = theCases.stream().filter(cases -> cases.getStatus().equals("Active")).collect(Collectors.toList());

		// add to the spring model
		theModel.addAttribute("cases", openCases);

		return "view-cases";
	}
	
	// add request mapping for /leaders

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}

	
}










