package com.example.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

	@Bean(initMethod="init")
	public InitMongoService initMongoService(){
		return new InitMongoService();
	}
}
