package com.example.aop;

import java.util.Date;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.domain.DetailUser;
import com.example.domain.User;
import com.example.repositories.UserRepository;

@Aspect
@Component
public class NotifyAllAop {

	public static final String WEBSOCKET_TOPIC = "/topic/notify";
	@Autowired SimpMessagingTemplate SimpMessagingTemplate;
	@Autowired UserRepository UserRepository;
	@Pointcut(value="@annotation(com.example.aop.NotifyAll)")
    public void myPoincut(){
		
    }
	@Pointcut("execution(void com.example.angular.controller.ManagerUserController.update(com.example.domain.DetailUser))&&args(detailUser)")
    public void aPoincut(DetailUser detailUser){
		
    }
	@AfterReturning(pointcut="myPoincut()")
	 public void myadvice(){
		try {
			Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
	        User user= UserRepository.findByusername(authentication.getName());
	    
	        	user.getDetailuser().setStatus(true);  
	        	
		  UserRepository.save(user);	
	             
	        
	       SimpMessagingTemplate.convertAndSend(WEBSOCKET_TOPIC, new Date());
		} catch (Exception e) {
			// TODO: handle exception
		}	
        
    }
	
	
	@AfterReturning(pointcut="aPoincut(detailUser)",argNames="detailUser")
	 public void aadvice(DetailUser detailUser){	
       System.out.println("Executing myAdvice!!"+detailUser.toString());
   }
	
}
