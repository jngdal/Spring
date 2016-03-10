package com.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.example.domain" })
@EnableJpaRepositories(basePackages = { "com.example.repositories" },repositoryImplementationPostfix="ExtensionImpl")
@EnableTransactionManagement
public class Repositoryconfig {

}
