package com.example.jetty_jersey.DAO;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;

import com.example.jetty_jersey.model.Flight;
import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Reservation;

public class ReservationDAO extends DAO<Reservation> {

    public DAOFactory daofactory;

    public ReservationDAO(DAOFactory f) {
	daofactory = f;
    }

    @Override
    public String put(Reservation r) {
	TransportClient client = DAOFactory.getConnextion();

	SearchResponse res = client.prepareSearch("book").setTypes("_doc").setQuery(QueryBuilders.matchAllQuery())
		.setSize(10000).get();

	SearchHit[] result = res.getHits().getHits();

	for (int i = 0; i < result.length; i++) {
	    Map<String, Object> map = result[i].getSourceAsMap();
	    if (map.get("idFlight").toString().equals(r.getIdFlight())
		    && map.get("idPassenger").toString().equals(r.getIdPassenger())) {
		int np = Integer.parseInt(map.get("numberPlace").toString()) + r.getNumberPlace();
		try {
		    UpdateResponse update = client.prepareUpdate("book", "_doc", result[i].getId())
			    .setDoc(jsonBuilder().startObject().field("numberPlace", np).endObject()).get();
		    if (update.status() == RestStatus.OK) {
			return "{" + "\"status\":\"201\"," + "\"message\":\"Well booked\"" + "}";
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return "{" + "\"status\":\"400\"," + "\"error\":\"Can not book the flight\"" + "}";
	    }
	}
	try {
	    IndexResponse response = client.prepareIndex("book", "_doc")
		    .setSource(jsonBuilder().startObject().field("idFlight", r.getIdFlight())
			    .field("idPassenger", r.getIdPassenger()).field("numberPlace", r.getNumberPlace())
			    .field("confirmed", "0").endObject())
		    .get();
	    if (response.status() == RestStatus.CREATED) {
		return "{" + "\"status\":\"201\"," + "\"message\":\"Well booked\"" + "}";
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return "{" + "\"status\":\"400\"," + "\"error\":\"Can not book the flight\"" + "}";
    }

    @Override
    public boolean delete(Reservation obj, String idReservation) {
	TransportClient client = DAOFactory.getConnextion();
	try {
	    DeleteResponse response = client.prepareDelete("book", "_doc", idReservation).execute().actionGet();
	    return true;
	} catch (ElasticsearchException e) {
	    if (e.status() == RestStatus.CONFLICT)
		e.printStackTrace();
	}
	return false;
    }

    @Override
    public boolean update(Reservation r, String idReservation) {
	TransportClient client = DAOFactory.getConnextion();
	try {
	    UpdateResponse update = client.prepareUpdate("book", "_doc", idReservation)
		    .setDoc(jsonBuilder().startObject().field("confirmed", "1").endObject()).get();
	    if (update.status() == RestStatus.OK) {
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

    public List<Flight> getFlightForPassenger(String idPassenger, String status) {
	TransportClient client = daofactory.getConnextion();

	ArrayList<Flight> list = new ArrayList<Flight>();

	SearchResponse response = client.prepareSearch("book").setTypes("_doc").setQuery(QueryBuilders.matchAllQuery())
		.setSize(10000).get();

	SearchHit[] result = response.getHits().getHits();

	for (int i = 0; i < result.length; i++) {

	    Map<String, Object> map = result[i].getSourceAsMap();

	    if (map.get("idPassenger").toString().equals(idPassenger) && map.get("confirmed").toString().equals(status)) {
		Flight f = DAOFactory.getInstance().getFlightDAO().get(map.get("idFlight").toString()).get(0);
		list.add(f);
	    }
	}

	return list;
    }

    public List<Passenger> getPassengerForPilots(String idPilot, String idFlight) {
	TransportClient client = DAOFactory.getConnextion();

	ArrayList<Passenger> list = new ArrayList<Passenger>();

	SearchResponse response = client.prepareSearch("book").setTypes("_doc").setQuery(QueryBuilders.matchAllQuery())
		.setSize(10000).get();

	SearchHit[] result = response.getHits().getHits();

	for (int i = 0; i < result.length; i++) {
	    Map<String, Object> map = result[i].getSourceAsMap();

	    if (result[i].getId().equals(idFlight)) {
		list.add(daofactory.getPassengerDAO().get(map.get("idPassenger").toString()).get(0));
	    }
	}

	return list;
    }

}
