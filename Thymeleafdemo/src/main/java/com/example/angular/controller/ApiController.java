package com.example.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.angular.domain.Movies;

@Controller

public class ApiController {

	@Autowired
	com.example.angular.repositories.MovieRepository MovieRepository;
	@RequestMapping(value="/api",method = RequestMethod.GET)
	public String index() {
		return "angular/index";
	}
	
	@RequestMapping(value="/movies",method = RequestMethod.GET)
	public String moviespartial() {
		return "angular/movies";
	}
	@RequestMapping(value="/movieview",method = RequestMethod.GET)
	public String moviesviewpartial() {
		return "angular/movie-view";
	}
	@RequestMapping(value="/movieadd",method = RequestMethod.GET)
	public String moviesaddpartial() {
		return "angular/movie-add";
	}
	@RequestMapping(value="/movieedit",method = RequestMethod.GET)
	public String movieseditpartial() {
		return "angular/movie-edit";
	}
	@RequestMapping(value="/angular/_form.html",method = RequestMethod.GET)
	public String form() {
		return "angular/_form";
	}
	
		
	
	@RequestMapping(value="/api/movies",method=RequestMethod.GET)
	public @ResponseBody List<Movies> allmovies(){
		
		return MovieRepository.allMovies();
	}
	
	@RequestMapping(value="/api/movies",method=RequestMethod.POST)
	public @ResponseBody void addmovie(@RequestBody Movies movie){
		
		System.out.println(movie.getTitle()+movie.get_id());
	}
	
	
	@RequestMapping(value="/api/movies/{id}",method=RequestMethod.GET)
	public @ResponseBody Movies onemovies(@PathVariable int id){
		
		MovieRepository.allMovies();
		for (Movies movie : MovieRepository.allMovies()) {
			
			if(movie.get_id()==id) return movie;
		}
		return null;
	}
	
	@RequestMapping(value="/api/movies/{id}",method=RequestMethod.DELETE)
	public @ResponseBody void deletemovies(@PathVariable int id){
		
		System.out.println("remove"+id);
	}
	
	@RequestMapping(value="/api/movies/{id}",method=RequestMethod.PUT)
	public @ResponseBody void updatemovies(@RequestBody Movies movie){
		
		System.out.println("update"+ movie.getReleaseYear()+movie.get_id());
	}
	
	
	
	

}
