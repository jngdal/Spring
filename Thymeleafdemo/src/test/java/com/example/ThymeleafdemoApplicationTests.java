package com.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.domain.DetailUser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ThymeleafdemoApplication.class)
@WebAppConfiguration
public class ThymeleafdemoApplicationTests {

	@Test
	public void contextLoads() {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("DetailUser.findAll");
        query.setParameter("tablename", "sadsa");
        
        @SuppressWarnings("unchecked")
		List<DetailUser> students = query.getResultList();
        
        System.out.println("Executing Stored Procedure Query ................");
        for(DetailUser student : students){
            System.out.println("student"+student.toString());
        }
	}
	@PersistenceContext
    private EntityManager em;
    
   
    
}
