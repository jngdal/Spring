package com.example.mongo;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
@RepositoryRestController
public class TaskController {

	@Autowired
	MongoTemplate mongotemplate;
	@RequestMapping(value="/tasks",method=RequestMethod.POST)
	public @ResponseBody void getProducers(HttpServletResponse response,@RequestBody Task task) {
     AutoIncrement<Task> auto= new AutoIncrement<Task>(task, mongotemplate);
     try {
		auto.autoIncrement();
		response.addHeader("Location", ControllerLinkBuilder.linkTo(TaskController.class).slash(task.getId()).withSelfRel().getHref());
	System.out.println(response.getHeader("Location"));
     } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
}
