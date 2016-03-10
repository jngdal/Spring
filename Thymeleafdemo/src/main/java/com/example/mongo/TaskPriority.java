package com.example.mongo;

public enum TaskPriority {
	High("HIGH"),Low("LOW"),Medium("MEDIUM");
	String priority;
	TaskPriority(String priority){
		this.priority=priority;
	}
	@Override
	public String toString(){
		return priority;
	}
}
