package com.example.mongo;

public enum TaskStatus {
	Active("ACTIVE"),
	Completed("COMPLETED");
	private String status;
	private TaskStatus(String status){
		this.status=status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return status;
	}
	
}
