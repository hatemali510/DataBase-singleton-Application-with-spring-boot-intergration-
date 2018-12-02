package com.etisalat.integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.etisalat.models.DB;
import com.etisalat.models.Owner;
import com.etisalat.models.User;
import com.etisalat.utilities.Encryption;
@Component

public class OwnerIntegrator {
	private  String OwnerTableName="owners";
	String[] params= {"username","password","name","mobile_number"};
	
	public List<Owner> getAllOwners() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<Owner> Owners=new ArrayList<>();
		DB db=DB.get_DB_Instance();
		ResultSet result=db.getAllFromTable(OwnerTableName);
		while(result.next()) {
			Owner owner=new Owner();
			owner.setId(result.getInt("id"));
			owner.setName(result.getString("name"));
			owner.setUsername(result.getString("username"));
			owner.setMobile_number(result.getString("mobile_number"));
			Owners.add(owner);
		}
		return Owners;
	}
	
	
	public boolean insertOwner(Owner owner) throws Exception {
		String name =owner.getName();  String mobile =owner.getMobile_number();  
		Encryption encrypt=new Encryption();
		String username=owner.getUsername(); String password=encrypt.encrypt(owner.getPassword());
		String [] values= {username,password,name,mobile}; 
		DB db=DB.get_DB_Instance();
		int insertRes=db.insertRecord(OwnerTableName, params, values);
		if(insertRes!=-1) {
			return true;
		}else {
			return false;
		}
	}
	
	public Owner getOwnerById(int id) throws Exception {
		Owner owner=new Owner();
		DB db=DB.get_DB_Instance();
		ResultSet result=db.getRecordFromTable(OwnerTableName, id);
		owner=owner.ConvertToObject(result);
		if(owner.isvalid()) {
		    return owner;
		}else {
			return null;
		}
	}
	
	public boolean DeleteOwner(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB db=DB.get_DB_Instance();
		if(db.DeleteRecord(OwnerTableName, Integer.toString(id))){
			return true;
		}else {
			return false;
		}
	}
	
	
	public Owner UpdateOwner(int id,String[] values) throws Exception {
		DB db=DB.get_DB_Instance();
		ArrayList<String> updatedParams=new ArrayList<>(); ArrayList<String> updatedValues=new ArrayList<>();
		for(int i=0;i<values.length;i++) {
			if(values[i]!=null) {
				if(i==0) {
					updatedParams.add("name");
					updatedValues.add(values[i]);
				}
				if(i==1) {
					updatedParams.add("mobile_number");
					updatedValues.add(values[i]);
				}
			}
		}
		if(db.updateRecordsOnTable(OwnerTableName, updatedParams, updatedValues, Integer.toString(id))) {
			return getOwnerById(id);
		}else {
			return null;
		}
		
	}


	public Owner Login(User user) throws Exception {
		
		if( OwnerAuth(user.getUsername(),user.getPassword())){
			return getOwnerByUsername(user.getUsername());
		}
		else {
			return null;
		}
	}
	
	private Owner getOwnerByUsername(String username) throws Exception {
		DB db=DB.get_DB_Instance();
		ArrayList<String> args=new ArrayList<>(); args.add("username");
		ArrayList<String> values=new ArrayList<>(); values.add(username);
		String fields=db.PrepeareWhereQuery(args, values);
		String Query="select * from "+OwnerTableName+fields;
		ResultSet resultSet=db.executeDbQuery(Query);
		Owner owner=new Owner();
		owner=owner.ConvertToObject(resultSet);
		if(owner.isvalid()) {
			return owner;
		}else {
			return null;
		}
	}


	public boolean OwnerAuth(String username,String Password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB db=DB.get_DB_Instance();
		ArrayList<String> args=new ArrayList<>(); args.add("username"); args.add("password");
		ArrayList<String> values=new ArrayList<>(); values.add(username); values.add(Password);
		String fields=db.PrepeareWhereQuery(args, values);
		String Query="select * from "+OwnerTableName+fields;
		ResultSet resultSet=db.executeDbQuery(Query);
		while(resultSet.next()) {
			return true;
		}
		return false;
	}

}
