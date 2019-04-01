package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;
import com.example.jetty_jersey.model.*;

public class FlightDAO extends DAO<Flight> {

	private static List<Flight> list = new ArrayList<Flight>();
	// un fois la bdd est en place
	// private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {
		list.add(new Flight("1", "2019-03-07", "Orly", "CDG", 35, 3, "12:00", "travel", new Plane(),
				new Pilot("Toto", "", "", "", 0, ""), 5));
		list.add(new Flight("2", "2019-03-07", "Orly", "CDG", 40, 2, "12:00", "travel", new Plane(),
				new Pilot("Gerard", "", "", "", 0, ""), 6));
		list.add(new Flight("3", "2019-12-07", "CDG", "Orly", 35, 2, "12:00", "travel", new Plane(),
				new Pilot("Nassim", "", "", "", 0, ""), 5));
		list.add(new Flight("4", "2019-03-23", "Orly", "CDG", 35, 2, "12:00", "travel", new Plane(),
				new Pilot("Jo", "", "", "", 0, ""), 4));

		// daofactory = f;
	}

	@Override
	public boolean put(Flight obj) {
		obj.setPilot(new Pilot("a", "a", "a", "a", 0, "certifié"));
		obj.setPlane(new Plane(4, "modele", "avion", 2));
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
			try {
				if ((f.getTypeflight().equals(local) || f.getTypeflight().equals(travel)) && f.getDate().equals(date)
						&& f.getArrivalAirport().equals(arrival) && f.getDepartureAirport().equals(departure))
					l.add(f);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return l;
	}

}