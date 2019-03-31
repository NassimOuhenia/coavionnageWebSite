package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import com.example.jetty_jersey.model.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class FlightDAO extends DAO<Flight> {
	private static List<Flight> list=new ArrayList<Flight>();
	//un fois la bdd est en place
	private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {
		list.add(new Flight("1", "2019-03-21", "orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("2", "2019-03-21", "orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("3", "2019-03-21", "paris", "paris", 35, 2, "travel", new Plane(), new Pilot()));
		
		daofactory = f;
	}
	

	@Override
	public String put(Flight obj) {
		TransportClient  client = daofactory.getConnextion(); 
		try { 
			IndexResponse response = client.prepareIndex("flight","_doc").setSource(
					jsonBuilder()
					.startObject()
					.field("date",obj.getDate())
					.field("departureAirport",obj.getDeparture_airport())
					.field("arrivalAirport",obj.getArrival_airport())
					.field("travelTime",obj.getTravelTime())
					.field("price",obj.getPrice())
					.field("typeflight",obj.getTypeflight())
					.field("plane",obj.getPlane())
					.field("pilot",obj.getPilot())
					.endObject()
					).get(); 
			if (response.status() == RestStatus.CREATED) {
			    return "{" +
					"\"status\":\"201\"," +
					"\"id\":\"" + response.getId() + "\"" +
					"}";
			}
		}catch (IOException e ) { 
			e.printStackTrace();
		}
		return "{" +
		    "\"status\":\"500\"," +
		    "\"error\":\"Flight couldnt be created \"" +
		    "}";
	}

	@Override
	public boolean delete(Flight obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Flight obj, String idPassenger) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Flight> get() {
		
		return list;
	}
	
	public List<Flight> search(String local, String travel, String date, String departure, String arrival) { 
	
		ArrayList<Flight> l = new ArrayList <Flight>();
		for(Flight f : list) {
			if(f.getArrival_airport().equals(arrival)
				&& f.getDeparture_airport().equals(departure))
				l.add(f);
		}
		return l;
	}


	@Override
	public List<Flight> get(String id) {
	    TransportClient client = daofactory.getConnextion();
	    ArrayList<Flight> list = new ArrayList<Flight>();
	    GetResponse get = client.prepareGet("flight", "_doc", id).get();
	    if (get.isSourceEmpty()) {
		return list;
	    }
	    Map<String, Object> map = get.getSource();
	    Flight f = new Flight(id, (String)map.get("date"), (String)map.get("departureAirport"), (String)map.get("arrivalAirport"),
		    Double.valueOf(map.get("travelTime").toString()), Double.valueOf(map.get("price").toString()), (String)map.get("typeFlight"),
		    (Plane)map.get("plane"), (Pilot)map.get("pilot"));
	    list.add(f);
	    return list;
	}

	
	public String book (Reservation r) {
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
	
	public List<Flight> get(Recherche r) {
	    TransportClient client = daofactory.getConnextion();
	    
	    int typeFlight = 0;
	    if (r.getTypeLocal() == null) {
		typeFlight++;
	    }
	    
	    SearchResponse response = client.prepareSearch("flight")
		    .setTypes("_doc")
		    	.setQuery(QueryBuilders.termQuery("typeflight", typeFlight))
			.setQuery(QueryBuilders.termQuery("departureAirport", r.getDeparture()))
			.setQuery(QueryBuilders.termQuery("arrivalAirport", r.getArrival()))
			.setQuery(QueryBuilders.termQuery("date", r.getDate()))
			.get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    ArrayList<Flight> list = new ArrayList<Flight>();
	    for (SearchHit sh : result) {
		Map<String, Object> map = sh.getSourceAsMap();
		Flight f = new Flight(sh.getId(), map.get("date").toString(), map.get("departureAirport").toString(), map.get("arrivalAirport").toString(),
		    Double.valueOf(map.get("travelTime").toString()), Double.valueOf(map.get("price").toString()), map.get("typeFlight").toString(),
		    (Plane)map.get("plane"), (Pilot)map.get("pilot"));
		list.add(f);
	    }
	    return list;
	    
	}
	
	public String updateBook(String idPassenger) {
		TransportClient client = daofactory.getConnextion();
		try {
			UpdateResponse update = client.prepareUpdate("book", "_doc", idPassenger)
					.setDoc(jsonBuilder()
							.startObject()
							.field("confirmed",1)
							.endObject()).get();
			if(update.status() == RestStatus.OK) {
			    return "{" +
				    	"\"status\":\"200\"," +
				    	"\"message\":\"Well confirmed\"" +
				    	"}";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{" +
	    	"\"status\":\"400\"," +
	    	"\"error\":\"Can not confirm the booking\"" +
	    	"}";
	}
	
}

