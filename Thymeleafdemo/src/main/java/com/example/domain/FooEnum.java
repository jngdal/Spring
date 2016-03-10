package com.example.domain;

public enum FooEnum {

	ADMIN("Admin"),
	DBA("DBA"),
	USER("User");
	private String type;
	private FooEnum(String type){
		this.type=type;
	}
	
	public String getType(){
		return this.type;
	}
	
}
