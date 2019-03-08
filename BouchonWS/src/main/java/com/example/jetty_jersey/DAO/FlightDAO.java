package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.model.Flight;

public class FlightDAO extends DAO<Flight> {
	private static List<Flight> list=new ArrayList<Flight>();
	//un fois la bdd est en place
	//private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {

		//daofactory = f;
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

	

	
}

