package com.example.testlogin;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationForm {
	@Email
    @NotEmpty
    @Size(max = 100)
    private String email;
 
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordVerification() {
		return passwordVerification;
	}

	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
	}

	public SocialMediaService getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
	}

	@NotEmpty
    @Size(max = 100)
    private String firstName;
 
    @NotEmpty
    @Size(max = 100)
    private String lastName;
 
    private String password;
 
    private String passwordVerification;
 
    private SocialMediaService signInProvider;
 
    //Constructor is omitted for the of clarity.
     
    public boolean isNormalRegistration() {
        return signInProvider == null;
    }
 
    public boolean isSocialSignIn() {
        return signInProvider != null;
    }
}
