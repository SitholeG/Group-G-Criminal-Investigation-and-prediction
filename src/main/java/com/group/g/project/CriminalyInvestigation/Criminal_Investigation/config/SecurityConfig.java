package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.config;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
// case/getCase
		http.authorizeRequests()
			.antMatchers("/").hasAnyRole ("ADMIN","OFFICER")
			.antMatchers("/case/getCase/**").hasAnyRole ("ADMIN","OFFICER")
			.antMatchers("/case/getCaseHistory").hasAnyRole ("ADMIN","OFFICER")
			.antMatchers("/case/showAddCaseForm/**").hasRole("ADMIN")
			.antMatchers("/register/showRegistrationForm/**").hasRole("ADMIN")
			.antMatchers("/case/showAddSuspectForm/**").hasRole("OFFICER")
			.antMatchers("/case/showAddEvidenceForm/**").hasRole("OFFICER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
	//beans
	//bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}






