package com.ujjawal.project.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ujjawal.project.dto.LoginRequestData;
import com.ujjawal.project.entity.ToDoItems;
import com.ujjawal.project.entity.UserCredentials;
import com.ujjawal.project.repo.ToDoItemsRepository;
import com.ujjawal.project.repo.UserRepository;
import com.ujjawal.project.service.MyUserDetailsService;

@Controller
public class MainController {
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ToDoItemsRepository todoitemrepo;
	
	@Autowired
    AuthenticationManager authenticationManager;
	@Autowired
	MyUserDetailsService userDetailsService;
	
	@RequestMapping("/")
	public String Home(ModelMap modelMap)
	{
		String useremail=SecurityContextHolder.getContext().getAuthentication().getName();
		List<ToDoItems> items=todoitemrepo.findByUserName(useremail);
		modelMap.addAttribute("userName", userDetailsService.getUserNamefromPrincipal(useremail));
		modelMap.addAttribute("items", items);
		return "home";
	}
	
	@RequestMapping("/login")
	public String loginPage()
	{
		return "loginpage";
	}
	
	@PostMapping("/login")
	public String loginUser(LoginRequestData request)
	{
		try {
			UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
			Authentication auth=authenticationManager.authenticate(token);
			SecurityContext sc=SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
		} catch (AuthenticationException e) {
			
			
		}
		
		
		return "redirect:/";
	}
	
	@RequestMapping("/register")
	public String showRegister() {
	

		return "registerpage";
	}
	
	@PostMapping("/register")
	public String registerUser(UserCredentials userreceived) {
		String pass=userreceived.getPassword();
		userreceived.setPassword(passwordEncoder.encode(pass));
	    userRepository.save(userreceived);

		return "redirect:/login";
	}
	
}
