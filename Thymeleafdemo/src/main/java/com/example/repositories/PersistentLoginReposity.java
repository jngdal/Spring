package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.PersistentLogin;
import com.example.domain.User;

public interface PersistentLoginReposity extends JpaRepository<PersistentLogin, String> {
	
	List<PersistentLogin> findByuser(User user);
}
