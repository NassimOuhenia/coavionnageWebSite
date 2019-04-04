package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		put(new Flight("1","2019-03-21", "orly", "CDG", 35, 2, "travel", "travel", null, null, 0));
		put(new Flight("2","2019-03-21", "orly", "CDG", 35, 2, "travel", "travel", null, null, 0));
		daofactory = f;
	}

	/*
	 * Ajoute un object vol dans la base de donnees
	 */

	@Override
	public String put(Flight obj) {
		TransportClient client = DAOFactory.getConnextion();
		try {
			IndexResponse response = client.prepareIndex("flight", "_doc")
					.setSource(jsonBuilder().startObject().field("date", obj.getDate())
							.field("departureAirport", obj.getDepartureAirport())
							.field("arrivalAirport", obj.getArrivalAirport()).field("travelTime", obj.getTravelTime())
							.field("price", obj.getPrice()).field("time", obj.getTime())
							.field("typeFlight", obj.getTypeFlight()).field("plane", obj.getPlane())
							.field("pilot", obj.getPilot()).field("seatLeft", obj.getSeatLeft()).endObject())
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
		Flight f = new Flight(get.getId(), map.get("date").toString(), map.get("departureAirport").toString(),
				map.get("arrivalAirport").toString(), Double.valueOf(map.get("travelTime").toString()),
				Double.valueOf(map.get("price").toString()), map.get("time").toString(),
				map.get("typeFlight").toString(), (Plane) map.get("plane"), (Pilot) map.get("pilot"),
				Integer.parseInt(map.get("seatLeft").toString()));
		list.add(f);
		return list;
	}

	/*
	 * Retourne une liste de vols en fonction de la recherche
	 */

	public List<Flight> get(Recherche r) {
		TransportClient client = DAOFactory.getConnextion();

	
		QueryBuilder query = QueryBuilders.queryStringQuery(
				"departureAirport:'" + r.getDeparture() + "' AND arrivalAirport:'" + r.getArrival() + "'");

		SearchResponse response = client.prepareSearch("flight").setTypes("_doc").setQuery(query).get();

		SearchHit[] result = response.getHits().getHits();
		ArrayList<Flight> list = new ArrayList<Flight>();
		for (SearchHit sh : result) {
			Map<String, Object> map = sh.getSourceAsMap();
			
			System.out.println();
			Flight f = new Flight(sh.getId(), map.get("date").toString(), map.get("departureAirport").toString(),
					map.get("arrivalAirport").toString(), Double.valueOf(map.get("travelTime").toString()),
					Double.valueOf(map.get("price").toString()), map.get("time").toString(),
					map.get("typeFlight").toString(), (Plane) map.get("plane"), (Pilot) map.get("pilot"),
					Integer.parseInt(map.get("seatLeft").toString()));
			list.add(f);
		}
		for(Flight f : list) {
			System.out.println("arrivé => "+ f.getIdFlight());
		}
		return list;

	}

}