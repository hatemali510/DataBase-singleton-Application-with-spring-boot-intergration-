package com.etisalat.integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.etisalat.models.DB;
import com.etisalat.models.Host;
import com.etisalat.models.Owner;
@Component

public class HostIntegrator {
	
	private  String table_name="hosts";
	private static String[] params= {"name","ip","port"};
	
	public List<Host> getAllHosts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<Host> hosts=new ArrayList<>();
		DB db=DB.get_DB_Instance();
		ResultSet result=db.getAllFromTable(table_name);
		hosts=Host.ConvertToHosts(result);
		return hosts;
	}
	
	public boolean insertHost(Host host) throws Exception {
		String name =host.getName();  String ip =host.getIp();  String port=host.getPort();  //int owner_id=host.getOwner_id();
		String [] values= {name,ip,port};
		System.out.println(name+ " "+ ip +" "+port);
			DB db=DB.get_DB_Instance();
			int insertRes=db.insertRecord(table_name, params, values);
			if(insertRes!=-1) {
				return true;
			}else {
				return false;
			}
		
	}
	
	public Host getHostById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Host host=new Host();
		DB db=DB.get_DB_Instance();
		ResultSet result=db.getRecordFromTable(table_name, id);
		host=Host.ConvertToHost(result);
		return host;
	}
	
	public boolean DeleteHost(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB db=DB.get_DB_Instance();
		if(db.DeleteRecord(table_name, Integer.toString(id))){
			return true;
		}else {
			return false;
		}
	}
	
	public Host UpdateHost(int id,String[] values) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB db=DB.get_DB_Instance();
		ArrayList<String> updatedParams=new ArrayList<>(); ArrayList<String> updatedValues=new ArrayList<>();
		for(int i=0;i<values.length;i++) {
			if(values[i]!=null) {
				if(i==0) {
					updatedParams.add("name");
					updatedValues.add(values[i]);
				}
				if(i==1) {
					updatedParams.add("ip");
					updatedValues.add(values[i]);
				}
				if(i==2) {
					updatedParams.add("port");
					updatedValues.add(values[i]);
				}
			}
		}
		if(db.updateRecordsOnTable(table_name, updatedParams, updatedValues, Integer.toString(id))) {
			return getHostById(id);
		}else {
			return null;
		}
	}
	
	// get Host Owner 
	
	public Owner getHostOwner(int Hostid) throws Exception {
		Host host=getHostById(Hostid);
		int Owner_id=host.getOwner_id();
		OwnerIntegrator ownerIntegrator=new OwnerIntegrator();
		return ownerIntegrator.getOwnerById(Owner_id);
	}
	
	public boolean OwnerExist(int OwnerId) throws Exception {
		OwnerIntegrator ownerIntegrator=new OwnerIntegrator();
		if(ownerIntegrator.getOwnerById(OwnerId)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean AssignOwner(int OwnerId,int host) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DB db=DB.get_DB_Instance();
		ArrayList<String> args= new ArrayList<>(); args.add("owner_id");
		ArrayList<String> Vals= new ArrayList<>(); Vals.add(Integer.toString(OwnerId));
		return db.updateRecordsOnTable(table_name, args, Vals, Integer.toString(host));
	}
	

}
