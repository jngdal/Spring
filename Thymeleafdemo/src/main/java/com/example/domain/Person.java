package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "Persons")
public class Person {

	@NotBlank(message="NotBlank.name")
	@Id	
	@Column(name = "name", nullable = false)
    @Size(min=2, max=30,message="Size.name")
    private String name;

	@NotNull(message="NotNull.age")
    @Min(value=18,message="Min.age")
    @Column(name = "age", nullable = false)
    private Integer age;
	
	@Transient
	@NotBlank(message="NotBlank.a")
	private String a;
    public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }

}