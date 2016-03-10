package com.example.angular.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DetailUser;
import com.example.domain.User;
import com.example.repositories.UserRepository;

@RequestMapping("user/friends")
@RestController
public class ManagerUserFriends {

	@Autowired
	UserRepository UserRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<DetailUser> getAllFriend() {
		try {
			
			
			List<User> users=UserRepository.findByusername(SecurityContextHolder.getContext().getAuthentication().getName()).getFriends();
			List<DetailUser> detailUsers = new ArrayList<DetailUser>();			
			users.forEach((User user)->{				
				detailUsers.add(user.getDetailuser());
			});
		
			return detailUsers;
		} catch (Exception e) {			
			System.out.println("null");
			return null;
		}
	}
}
