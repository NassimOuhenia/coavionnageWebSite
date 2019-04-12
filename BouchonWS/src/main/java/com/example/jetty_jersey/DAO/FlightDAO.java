package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import com.example.jetty_jersey.model.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class FlightDAO extends DAO<Flight> {

	public static List<Flight> list = new ArrayList<Flight>();
	// un fois la bdd est en place
	public DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {
	
		daofactory = f;
	}
	

	/*
	 * Ajoute un object vol dans la base de donnees
	 */

	
	public String put(Flight obj) {
		TransportClient client = DAOFactory.getConnextion();
		try {
		    String str = null;
		    String modele = obj.getModelePlane();
		    if (modele == null) {
			modele = "avion";
		    }
			IndexResponse response = client.prepareIndex("flight", "_doc")
					.setSource(jsonBuilder()
						.startObject()
							.field("date", obj.getDate())
							.field("departureAirport", obj.getDepartureAirport())
							.field("arrivalAirport", obj.getArrivalAirport())
							.field("travelTime", obj.getTravelTime())
							.field("price", obj.getPrice())
							.field("time", obj.getTimep())
							.field("typeFlight", obj.getTypeFlight())
							.field("modelePlane", modele)
							.field("pilot", obj.getPilot().getFirstName())
							.field("seatLeft", obj.getSeatLeft())
						.endObject())
					.get();
			if (response.status() == RestStatus.CREATED) {
				return "{" + "\"status\":\"201\"," + "\"id\":\"" + response.getId() + "\"" + "}";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{" + "\"status\":\"500\"," + "\"error\":\"Flight couldnt be created \"" + "}";
	}

	 @Override
		public boolean delete(Flight obj, String idFlight) {
			TransportClient client = DAOFactory.getConnextion();
			try {
				DeleteResponse response = client.prepareDelete("book", "_doc", idFlight)
						.execute()
						.actionGet();
				return true;
			} catch(ElasticsearchException e) {
				if (e.status() == RestStatus.CONFLICT)
					e.printStackTrace();
			}
			return false;
		}

		@Override
		public boolean update(Flight obj, String idFlight) {
			TransportClient client = DAOFactory.getConnextion();
			try {
				client.prepareUpdate("flight", "_doc", idFlight)
						.setDoc(jsonBuilder().startObject()
								.field("date", obj.getDate())
								.field("departureAirport", obj.getDepartureAirport())
								.field("arrivalAirport", obj.getArrivalAirport())
								.field("travelTime", obj.getTravelTime())
								.field("price", obj.getPrice())
								.field("time", obj.getTimep())
								.field("typeFlight", obj.getTypeFlight())
								.field("pilot", obj.getPilot())
								.field("seatLeft", obj.getSeatLeft())
							.endObject())
						.get();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

	@Override
	public List<Flight> get() {
		return list;
	}

	/*
	 * Retourne un vol en fonction de son id
	 */

	@Override
	public List<Flight> get(String id) {
		TransportClient client = DAOFactory.getConnextion();
		ArrayList<Flight> list = new ArrayList<Flight>();
		GetResponse get = client.prepareGet("flight", "_doc", id).get();
		if (get.isSourceEmpty()) {
			return list;
		}
		Map<String, Object> map = get.getSource();
		Flight f = new Flight(
			get.getId(),
			map.get("date").toString(),
			map.get("departureAirport").toString(),
			map.get("arrivalAirport").toString(),
			Double.valueOf(map.get("travelTime").toString()),
			Double.valueOf(map.get("price").toString()),
			map.get("time").toString(),
			map.get("typeFlight").toString(),
			map.get("modelePlane").toString(),
			DAOFactory.getInstance().getPiloteDAO().get(map.get("pilot").toString()).get(0),
			Integer.parseInt(map.get("seatLeft").toString()));
		list.add(f);
		return list;
	}

	/*
	 * Retourne une liste de vols en fonction de la recherche
	 */

	public List<Flight> get(Recherche r) {
		TransportClient client = DAOFactory.getConnextion();
		
		int typeFlight = 0;
		if (r.getTypeLocal() == null)
		    typeFlight++;
	
		QueryBuilder query = QueryBuilders.queryStringQuery(
				"typeFlight:'" + Integer.toString(typeFlight) +
				"' AND departureAirport:'" + r.getDeparture() + 
				"' AND arrivalAirport:'" + r.getArrival() +
				"'");

		SearchResponse response = client.prepareSearch("flight")
			.setTypes("_doc")
				.setQuery(QueryBuilders.matchAllQuery())
				.setSize(10000)
			.get();

		SearchHit[] result = response.getHits().getHits();
		ArrayList<Flight> list = new ArrayList<Flight>();
		for (SearchHit sh : result) {
			Map<String, Object> map = sh.getSourceAsMap();
			System.out.println(map.get("typeFlight").equals(Integer.toString(typeFlight)));
			if (//map.get("typeFlight").equals(Integer.toString(typeFlight))
				map.get("date").equals(r.getDate())
				&& map.get("departureAirport").equals(r.getDeparture())
				&& map.get("arrivalAirport").equals(r.getArrival())
				&& Integer.parseInt(map.get("seatLeft").toString()) > 0) {
			    
			    Flight f = new Flight(
				sh.getId(),
				map.get("date").toString(),
				map.get("departureAirport").toString(),
				map.get("arrivalAirport").toString(),
				Double.valueOf(map.get("travelTime").toString()),
				Double.valueOf(map.get("price").toString()),
				map.get("time").toString(),
				map.get("typeFlight").toString(),
				map.get("modelePlane").toString(),
				DAOFactory.getInstance().getPiloteDAO().get(map.get("pilot").toString()).get(0),
				Integer.parseInt(map.get("seatLeft").toString()));
			    list.add(f);
			}
		}
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
		    System.out.println(list.get(i).getDepartureAirport());
		}
		return list;

	}
	
	public List<Flight> getFlightsForPilots(String idPilot) {
	    TransportClient client = daofactory.getConnextion();
	    
	    ArrayList<Flight> list = new ArrayList<Flight>();
	    
	    SearchResponse response = client.prepareSearch("flight")
		    .setTypes("_doc")
		    .setQuery(QueryBuilders.matchAllQuery())
		    .setSize(10000)
		    .get();
	    
	    SearchHit[] result = response.getHits().getHits();
	    
	    for (int i = 0; i < result.length; i++) {
		Map<String, Object> map = result[i].getSourceAsMap();
		
		if (map.get("pilot").toString().equals(idPilot)) {
		    	Flight f = new Flight(
				result[i].getId(),
				map.get("date").toString(),
				map.get("departureAirport").toString(),
				map.get("arrivalAirport").toString(),
				Double.valueOf(map.get("travelTime").toString()),
				Double.valueOf(map.get("price").toString()),
				map.get("time").toString(),
				map.get("typeFlight").toString(),
				map.get("modelePlane").toString(),
				DAOFactory.getInstance().getPiloteDAO().get(map.get("pilot").toString()).get(0),
				Integer.parseInt(map.get("seatLeft").toString()));
			    list.add(f);
		}
	    }
	    
	    return list;
	}

}