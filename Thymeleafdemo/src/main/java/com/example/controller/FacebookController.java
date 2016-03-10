package com.example.controller;
import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/fb")

public class FacebookController {
	
	private Facebook facebook;

    @Inject
    public FacebookController(Facebook facebook) {
        this.facebook = facebook;
    }

   

    @RequestMapping(method=RequestMethod.GET)
    public String helloFacebook(Model model) {
    	try {
    		if (!facebook.isAuthorized()) {    			
    			return "connect/facebookConnect";
            }
    		
            model.addAttribute("facebookProfile",facebook.userOperations().getUserProfile());           
            return "feeds";
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return "connect/facebookConnect";
		}	
    }

}