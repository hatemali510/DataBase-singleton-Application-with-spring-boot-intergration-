package com.etisalat.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.etisalat.integration.HostIntegrator;
import com.etisalat.models.Host;
import com.etisalat.models.Owner;
@Component
public class HostService {
	
	@Autowired
	HostIntegrator hostIntegrator;
	
	
	public List<Host> getAllHosts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return hostIntegrator.getAllHosts();
	}
	
	public boolean insertHost(Host host) throws Exception {
		return hostIntegrator.insertHost(host);
	}
	
	public boolean AssignOwner(int OwnerId,int host) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return hostIntegrator.AssignOwner(OwnerId, host);
	}
	
	public boolean DeleteHost(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return hostIntegrator.DeleteHost(id);
	}
	
	public Host UpdateHost(int id,String[] values) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return hostIntegrator.UpdateHost(id, values);
	}
	
	public Owner getHostOwner(int Hostid) throws Exception {
		return hostIntegrator.getHostOwner(Hostid);
	}
	
	public Host getHostById(int HostId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return hostIntegrator.getHostById(HostId);
	}

}
