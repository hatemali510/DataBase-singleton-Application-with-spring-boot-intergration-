package com.etisalat.models;

import java.sql.ResultSet;



public class Owner {
	
	
	private int id;
	private String name;
	private String username;
	private String mobile_number;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	
	public String Tostring() {
		return "name : "+this.name +" mobile : "+this.mobile_number;
	}
	public Owner ConvertToObject(ResultSet result) throws Exception {
		Owner owner=new Owner();
		while(result.next()) {
			owner.setId(result.getInt("id"));
			owner.setName(result.getString("name"));
			owner.setMobile_number(result.getString("mobile_number"));
			owner.setUsername(result.getString("username"));
			owner.setPassword("Not Allowed");
			break;
		}
		return owner;
	}
	
	public boolean isvalid() {
		if( this.name!=null && this.mobile_number!=null && this.username!=null) {
			return true;
		}else {
			return false;
		}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
