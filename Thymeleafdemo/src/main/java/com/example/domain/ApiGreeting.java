package com.example.domain;

public class ApiGreeting {
	private final long id;
    private final String content;

    public ApiGreeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
