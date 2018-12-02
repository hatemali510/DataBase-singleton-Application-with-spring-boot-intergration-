package com.etisalat.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etisalat.integration.OwnerIntegrator;
import com.etisalat.models.Owner;
import com.etisalat.models.User;
@Component
public class OwnerService {
	@Autowired
	OwnerIntegrator ownerIntegrator;
	public List<Owner> getAllOwners() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return ownerIntegrator.getAllOwners();
	}
	
	public boolean insertOwner(Owner owner) throws Exception {
		return ownerIntegrator.insertOwner(owner);
	}
	
	public boolean DeleteOwner(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return ownerIntegrator.DeleteOwner(id);
	}
	
	public Owner UpdateOwner(int id,String[] values) throws Exception {
		return ownerIntegrator.UpdateOwner(id, values);
	}
	
	public Owner getOwnerById(int id) throws Exception {
		return ownerIntegrator.getOwnerById(id);
	}

	public Owner Login(User user) throws Exception {
		
		return ownerIntegrator.Login(user);
		
	}
}
