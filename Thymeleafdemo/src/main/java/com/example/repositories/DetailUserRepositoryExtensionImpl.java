package com.example.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.DetailUser;

@Component
public class DetailUserRepositoryExtensionImpl implements
DetailUserRepositoryExtension {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<DetailUser> allDetailUser(String name) {
		/*StoredProcedureQuery query = entityManager
				.createNamedStoredProcedureQuery("DetailUser.findAll");*/
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("selecttable", DetailUser.class);
		query.registerStoredProcedureParameter("tablename", String.class, ParameterMode.IN);
		System.out.println(name);
		query.setParameter("tablename", name);
		return query.getResultList();
	}

}
