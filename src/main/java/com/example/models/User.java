package com.etisalat.models;

import com.etisalat.utilities.Encryption;

public class User {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() throws Exception {
		Encryption encryption=new Encryption();
		return encryption.encrypt(password);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
