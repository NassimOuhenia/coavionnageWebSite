package com.example.jetty_jersey.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import com.example.jetty_jersey.model.*;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

public class FlightDAO extends DAO<Flight> {
	
	private static List<Flight> list = new ArrayList<Flight>();
	// un fois la bdd est en place
	// private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {
		list.add(new Flight("2019-03-21", "orly", "CDG", null, 35, 2, "travel", null, new Plane(), new Pilot(), 0));
		list.add(new Flight("2019-03-21", "orly", "CDG", null, 35, 2, "travel", null, new Plane(), new Pilot(), 0));
	}

	@Override
	public String put(Flight obj) {
		TransportClient client = DAOFactory.getConnextion();
		try {
			IndexResponse response = client.prepareIndex("flight", "_doc")
					.setSource(jsonBuilder().startObject().field("date", obj.getDate())
							.field("departureAirport", obj.getDepartureAirport())
							.field("arrivalAirport", obj.getArrivalAirport()).field("travelTime", obj.getTravelTime())
							.field("price", obj.getPrice()).field("typeFlight", obj.getTypeFlight())
							.field("plane", obj.getPlane()).field("pilot", obj.getPilot()).endObject())
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

	public List<Flight> search(String local, String travel, String date, String departure, String arrival) {
		ArrayList<Flight> l = new ArrayList<Flight>();
		for (Flight f : list) {
			if ((f.getTypeFlight().equals(local) || f.getTypeFlight().equals(travel)) && f.getDate().equals(date)
					&& f.getArrivalAirport().equals(arrival) && f.getDepartureAirport().equals(departure))
				l.add(f);
		}
		return l;
	}

	@Override
	public List<Flight> get(String id) {
		TransportClient client = DAOFactory.getConnextion();
		ArrayList<Flight> list = new ArrayList<Flight>();
		GetResponse get = client.prepareGet("flight", "_doc", id).get();
		if (get.isSourceEmpty()) {
			return list;
		}
		Map<String, Object> map = get.getSource();
		Flight f = new Flight(map.get("date").toString(), map.get("departureAirport").toString(),
				map.get("arrivalAirport").toString(), id, Double.valueOf(map.get("travelTime").toString()),
				Double.valueOf(map.get("price").toString()), map.get("typeFlight").toString(), id,
				(Plane) map.get("plane"), (Pilot) map.get("pilot"), 0);
		list.add(f);
		return list;
	}

	public List<Flight> get(Recherche r) {
		TransportClient client = DAOFactory.getConnextion();

		int typeFlight = 0;
		if (r.getTypeLocal() == null) {
			typeFlight++;
		}

		SearchResponse response = client.prepareSearch("flight").setTypes("_doc")
				.setQuery(QueryBuilders.termQuery("typeflight", typeFlight))
				.setQuery(QueryBuilders.termQuery("departureAirport", r.getDeparture()))
				.setQuery(QueryBuilders.termQuery("arrivalAirport", r.getArrival()))
				.setQuery(QueryBuilders.termQuery("date", r.getDate())).get();

		SearchHit[] result = response.getHits().getHits();
		ArrayList<Flight> list = new ArrayList<Flight>();
		for (SearchHit sh : result) {
			Map<String, Object> map = sh.getSourceAsMap();
			Flight f = new Flight(map.get("date").toString(), map.get("departureAirport").toString(),
					map.get("arrivalAirport").toString(), null, Double.valueOf(map.get("travelTime").toString()),
					Double.valueOf(map.get("price").toString()), map.get("typeFlight").toString(), null,
					(Plane) map.get("plane"), (Pilot) map.get("pilot"), typeFlight);
			list.add(f);
		}
		return list;

	}

}