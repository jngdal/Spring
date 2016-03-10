package com.example.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.repositories.UserRepository;
import com.example.repositories.UserRoleRepository;
import com.example.domain.User;
import com.example.domain.UserRole;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	UserRepository UserRepository;
	@Autowired
	UserRoleRepository UserRoleRepository;

	@RequestMapping
	public String adminPage(ModelMap map) {
		map.addAttribute("message", "This is welcome page!");
		return "admin/admin";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singupPage(User user) {
		
		return "admin/signup";
	}

	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String postsingupPage(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/signup";
		}
		user.setEnabled(true);
		Set<UserRole> userroles = new HashSet<UserRole>(0);
		UserRole userrole = new UserRole();
		userrole.setUser(user);
		userrole.setRole("ROLE_ADMIN");
		userroles.add(userrole);
		userrole = new UserRole();
		userrole.setUser(user);
		userrole.setRole("ROLE_USER");		
		userroles.add(userrole);
		user.setUserRole(userroles);
		UserRepository.save(user);
		for (UserRole userRole1 : userroles) {
			UserRoleRepository.save(userRole1);
		}

		return "redirect:/login";
	}

}
