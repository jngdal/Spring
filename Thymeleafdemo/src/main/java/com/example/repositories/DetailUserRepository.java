package com.example.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.domain.DetailUser;
/*import org.springframework.data.repository.query.Param;*/

public interface DetailUserRepository extends JpaRepository<DetailUser, Integer>,DetailUserRepositoryExtension {
	
    
	@Procedure(value="inserttable")
	Integer   insertDetailUser(String lastname,String firstname,Date birthday,String email,String cellphone,Boolean sta);
	@Procedure
	Integer plus1inout(Integer arg);	
	
}
