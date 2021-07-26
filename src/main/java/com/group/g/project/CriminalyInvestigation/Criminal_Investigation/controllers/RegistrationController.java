package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.controllers;

import java.util.logging.Logger;

import javax.validation.Valid;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.User;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services.UserService;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("userDto", new UserDto());
		
		return "registration-form";
	}

	@GetMapping("/showAdminRegistrationForm")
	public String showAdminMyLoginPage(Model theModel) {

		theModel.addAttribute("userDto", new UserDto());

		return "admin-registration-form";
	}

	@GetMapping("/showUpdateUserForm")
	public String showUpdateUserForm(Model theModel, @RequestParam("username") String username) {
		ModelMapper mapper = new ModelMapper();
		User user = userService.findByUserName(username);
		UserDto userDto = mapper.map(user,new TypeToken<UserDto>(){}.getType());
		theModel.addAttribute("userDto", userDto);

		return "Update-user-form";
	}

	@PostMapping("/processAdminRegistrationForm")
	public String processAdminRegistrationForm(
			@Valid @ModelAttribute("userDto") UserDto userDto,
			BindingResult theBindingResult,
			Model theModel) {

		String userName = userDto.getUserName();
		logger.info("Processing registration form for: " + userName);

		// form validation
		if (theBindingResult.hasErrors()){
			return "admin-registration-form";
		}

		// check the database if user already exists
		User existing = userService.findByUserName(userName);
		if (existing != null){
			theModel.addAttribute("userDto", new UserDto());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
			return "admin-registration-form";
		}

		// create user account
		userService.saveAdmin(userDto);

		logger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") UserDto theCrmUser,
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("crmUser", new UserDto());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        
        // create user account        						
        userService.save(theCrmUser);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}

	@PostMapping("/processUpdateUserForm")
	public String processUpdateUserForm(
			@Valid @ModelAttribute("crmUser") UserDto theUser,
			BindingResult theBindingResult,
			Model theModel) {

		String userName = theUser.getUserName();
		logger.info("Processing update form for: " + userName);

		// form validation
		if (theBindingResult.hasErrors()){
			return "Update-user-form";
		}

		userService.upDate(theUser);

		logger.info("Successfully created user: " + userName);

		return "registration-confirmation";
	}
}
