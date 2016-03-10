package com.example.angular.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.angular.domain.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Repository
public class MovieRepository {
	public List<Movies> allMovies() {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		 final String uri = "http://movieapp-sitepointdemos.rhcloud.com/api/movies/";
		 String result = restTemplate.getForObject(uri, String.class);
		 JSONArray all =  new JSONArray(result);
		
		 List<Movies> allmovies= new ArrayList<Movies>(); 
			
			for (int i = 0; i < all.length(); i++) {
				 JSONObject jsonObj = all.getJSONObject(i);
				try {
					
					Movies add=mapper.readValue(jsonObj.toString(), Movies.class);
					
					allmovies.add(add);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return allmovies;
	}

}
