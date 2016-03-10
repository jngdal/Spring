package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.*;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

}
