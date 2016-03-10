package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="AwesomeThings")
public class AwesomeThing {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id",nullable=false)
	int id;
	@Column(name="Description",nullable=true)
	String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
