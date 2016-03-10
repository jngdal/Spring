package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByusername(String username);
	@Modifying
	@Query("from User")
	List<User>findAll();
}
