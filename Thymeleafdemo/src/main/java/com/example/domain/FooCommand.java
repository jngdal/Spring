package com.example.domain;

import java.util.Set;

public class FooCommand {
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private Set<Foo> foos;

	public Set<Foo> getFoos() {
		return foos;
	}

	public void setFoos(Set<Foo> foos) {
		this.foos = foos;
	}
}
