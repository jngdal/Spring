package com.example.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "PersistentLogins")
public class PersistentLogin {
	@Id
	@Column(name = "series", nullable = false,length=64)
	private String series ;
	 
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getLast_used() {
		return last_used;
	}
	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private User user;
	@Column(name = "token", nullable = false,length=64)
	private String token ;
	@Column(name = "last_used", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used ;
	
}
