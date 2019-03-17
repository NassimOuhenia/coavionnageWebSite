package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import com.example.jetty_jersey.model.Plane;

public class PlaneDAO extends DAO<Plane>{
	
	private static List<Plane> listPlane=new ArrayList<Plane>();
	// pour l'instant base de donnée pas mise en place
	private DAOFactory daofactory;

	public PlaneDAO(DAOFactory f) {
		daofactory = f;
	}
	
	@Override
	public boolean put(Plane obj) {
	    	TransportClient client = daofactory.getConnextion();
	    	IndexResponse response;
	    	
	    	try {
	    	    response = client.prepareIndex("plane", "_doc").setSource(
	    		jsonBuilder()
	    			.startObject()
	    				.field("modele", obj.getModele())
	    				.field("name" , obj.getName())
	    				.field("numberplace", obj.getNumberplace())
	    			.endObject())
	    		.get();
	    	    return response.getResult().toString().equals("CREATED");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
	    	return false;
	}

	@Override
	public boolean delete(Plane obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Plane obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Plane> get() {
		
		return listPlane;
	}

	

}
