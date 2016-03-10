package com.example.repositories;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.domain.Foo;
import com.example.domain.FooEnum;

@Repository
public class FooRepository {

	public Set<Foo> findAll() {
		Set<Foo> temp = new HashSet<Foo>();
		Foo a = new Foo();
		a.setId(1);
		a.setName(FooEnum.USER.getType());
		temp.add(a);
		a = new Foo();
		a.setId(2);
		a.setName(FooEnum.ADMIN.getType());
		temp.add(a);
		a = new Foo();
		a.setId(3);
		a.setName(FooEnum.DBA.getType());
		temp.add(a);
		return temp;
	}

	public Foo findId(int id) {
		Set<Foo> temp = findAll();
		
		for (Foo foo : temp) {
			if (foo.getId() == id){
				
				return foo;}
		}
		return null;

	}

}
