package com.example.mongo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/mongo")
public class MongoController {

	@RequestMapping(method=RequestMethod.GET)
	public String mongoview(){
		return "mongo/datarest";
	}
}
