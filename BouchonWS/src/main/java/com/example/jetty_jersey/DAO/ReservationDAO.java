package com.example.jetty_jersey.DAO;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;

import com.example.jetty_jersey.model.Reservation;

public class ReservationDAO extends DAO<Reservation> {
	
	private DAOFactory daofactory;
	
	public ReservationDAO(DAOFactory f) {
		daofactory = f;
	}
	

	@Override
	public String put(Reservation r) {
	    TransportClient  client = daofactory.getConnextion();
		try { 
		    IndexResponse response = client.prepareIndex("book","_doc").setSource(
			    jsonBuilder()
			    	.startObject()
			    		.field("idFlight", r.getIdFlight())
					.field("idPassenger", r.getIdPassenger())
					.field("numberPlace", r.getNumberPlace())
					.field("confrimed", 0)
				.endObject()
				).get(); 
		    if (response.status() == RestStatus.CREATED) {
			return "{" +
			    	"\"status\":\"201\"," +
			    	"\"message\":\"Well booked\"" +
			    	"}";
		    }
		}catch (IOException e ) { 
			e.printStackTrace();
		}
		return "{" +
	    		"\"status\":\"400\"," +
	    		"\"error\":\"Can not book the flight\"" +
	    		"}";
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation r, String idReservation) {
	    TransportClient client = daofactory.getConnextion();
		try {
			UpdateResponse update = client.prepareUpdate("book", "_doc", idReservation)
					.setDoc(jsonBuilder()
							.startObject()
							.field("confirmed",1)
							.endObject()).get();
			if(update.status() == RestStatus.OK) {
			    return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reservation> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
