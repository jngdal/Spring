package com.example.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findByusername",
query = "select u from User u where u.username = ?1")

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message="notnull")
	@Column(name = "UserName", nullable = false)
	private String username;
	@NotNull
   	@Column(name = "Password", nullable = false)
	private String password;
	@Column(name = "Enabled", nullable = false)
	private boolean enabled;
	@OneToMany( mappedBy = "user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<UserRole> userRole;	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user",orphanRemoval=true)
	private List<PersistentLogin> userlist;
	
	@GenericGenerator(name="generator",strategy="foreign",parameters=@Parameter(name="property",value="detailuser"))
	@GeneratedValue(generator = "generator")	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	@JsonBackReference("detailuser")
	private DetailUser detailuser;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="friends",joinColumns=@JoinColumn(name="username"),inverseJoinColumns=@JoinColumn(name="friendname"),foreignKey=@ForeignKey(name="username"),inverseForeignKey=@ForeignKey(name="friendname"))
	private List<User> friends;
	
	public User(){}
	public User(String username,String password,boolean enable){
		this.username=username;
		this.enabled=enable;
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password= passwordEncoder.encode(password);
	}
	
	public String getUsername() {
		return username;
	}

	public List<PersistentLogin> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<PersistentLogin> userlist) {
		this.userlist = userlist;
	}
	
	public DetailUser getDetailuser() {
		return detailuser;
	}
	
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	public void setDetailuser(DetailUser detailuser) {
		this.detailuser = detailuser;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password= passwordEncoder.encode(password);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@JsonManagedReference
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
}
