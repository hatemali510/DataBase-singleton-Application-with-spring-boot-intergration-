package com.etisalat.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Host {
	
	private int id ;
	private String name;
	private String ip;
	private String port;
	private int owner_id;
	
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public static  ArrayList<Host> ConvertToHosts(ResultSet result) throws SQLException {
		ArrayList<Host> hosts=new ArrayList<>();
		while(result.next()) {
			Host host=new Host();
			host.setId(result.getInt("id"));
			host.setIp(result.getString("ip"));
			host.setName(result.getString("name"));
			host.setPort(result.getString("port"));
			host.setOwner_id(result.getInt("owner_id"));
			hosts.add(host);
		}
		return hosts;
	}
	
	public String Tostring() {
		return "name : "+this.name +" ip : "+this.ip + " port : "+this.port;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public static Host ConvertToHost(ResultSet result) throws SQLException {
		Host host=new Host();
		while(result.next()) {
			host.setId(result.getInt("id"));
			host.setIp(result.getString("ip"));
			host.setName(result.getString("name"));
			host.setPort(result.getString("port"));
			host.setOwner_id(result.getInt("owner_id"));
		}
		return host;
	}
	
	
}
