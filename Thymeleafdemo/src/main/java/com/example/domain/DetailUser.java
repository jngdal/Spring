package com.example.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "detailusers")
@Table(name = "detailusers")
@NamedStoredProcedureQueries(value = {
		@NamedStoredProcedureQuery(
				name = "DetailUser.findAll", procedureName = "selecttable", 
				parameters = { @StoredProcedureParameter(name = "tablename", type = String.class, mode = ParameterMode.IN) }, 
				resultClasses = { DetailUser.class }),
		})
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler"})
public class DetailUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private int id;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "birthday")
	private Date birthday;
	@Column(name = "email")
	private String email;
	@Column(name = "cellphone")
	private String cellphone;
	@Column(name = "status")
	private boolean status;
	@JsonManagedReference("detailuser")
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "detailuser")
	private User user;
	
	
	public User getUser() {
		return user;
	}	
	
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "FirstName:" + this.firstname + " LastName: " + this.lastname
				+ " BirthDay: " + this.birthday.toString() + " Email: "
				+ this.email + " CellPhone: " + this.cellphone+"Status"+this.status;
	}

}
