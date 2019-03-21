package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;
import com.example.jetty_jersey.model.*;

public class FlightDAO extends DAO<Flight> {

	private static List<Flight> list = new ArrayList<Flight>();
	// un fois la bdd est en place
	// private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {
		list=new ArrayList<Flight>();
		list.add(new Flight("1", "2019-03-07", "Orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("2", "2019-03-07", "Orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("3", "2019-05-05", "paris", "paris", 35, 2, "local", new Plane(), new Pilot()));
		list.add(new Flight("1", "2019-12-07", "CDG", "Orly", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("2", "2019-03-23", "Orly", "CDG", 35, 2, "travel", new Plane(), new Pilot()));
		list.add(new Flight("3", "2019-10-07", "paris", "paris", 35, 2, "local", new Plane(), new Pilot()));
		// daofactory = f;
	}

	@Override
	public boolean put(Flight obj) {
		return list.add(obj);
	}

	@Override
	public boolean delete(Flight obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Flight obj) {
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
			if ((f.getTypeflight().equals(local) || f.getTypeflight().equals(travel)) && f.getDate().equals(date)
					&& f.getArrivalAirport().equals(arrival) && f.getDepartureAirport().equals(departure))
				l.add(f);
		}
		return l;
	}

}