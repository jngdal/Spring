package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.AwesomeThing;

public interface AwesomeThingRepository extends JpaRepository<AwesomeThing, Integer> {

}
