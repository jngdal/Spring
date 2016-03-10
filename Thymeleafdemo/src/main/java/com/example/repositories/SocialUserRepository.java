package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.SocialUsers;

public interface SocialUserRepository extends JpaRepository<SocialUsers, Integer> {
	public SocialUsers findByEmail(String email);
}
