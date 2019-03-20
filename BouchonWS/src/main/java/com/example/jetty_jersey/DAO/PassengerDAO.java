package com.example.jetty_jersey.DAO;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.example.jetty_jersey.model.Passenger;


public class PassengerDAO extends DAO<Passenger>{
	private static List<Passenger> list=new ArrayList<Passenger>();
	//un fois la bdd est en place
	private DAOFactory daofactory;

	public PassengerDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public IndexResponse put(Passenger obj) {
		TransportClient client = daofactory.getConnextion();  
		try { 
			IndexResponse response = client.prepareIndex("passager", "_doc").setSource(
					jsonBuilder()
					.startObject()
					.field("firstName",obj.getFirstName())
					.field("lastName",obj.getLastName())
					.field("mail",obj.getMail())
					.field("password",obj.getPassword())
					.endObject()
					).get(); 
			return response; 
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean delete(Passenger obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Passenger obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Passenger> get() {
		
		return list;
	}

	

	

}
