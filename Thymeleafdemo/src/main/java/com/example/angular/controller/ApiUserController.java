package com.example.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DetailUser;
import com.example.domain.User;
import com.example.repositories.DetailUserRepository;
import com.example.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

	@Autowired
	DetailUserRepository detailUserRepository;
	@Autowired
	UserRepository userRepository;


	@RequestMapping(method = RequestMethod.GET)
	public List<DetailUser> getAllUser() {

		return detailUserRepository.allDetailUser("detailusers");

	}
	@RequestMapping(value="/all",method = RequestMethod.GET)
	public List<User> getUser() {

		System.out.println(userRepository.findAll().size());
		return userRepository.findAll();

	}
}
