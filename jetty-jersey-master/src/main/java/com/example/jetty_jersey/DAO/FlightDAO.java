package com.example.jetty_jersey.DAO;

import java.util.List;

import com.example.jetty_jersey.model.Flight;

public class FlightDAO extends DAO<Flight> {
	private DAOFactory daofactory;

	public FlightDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public boolean create(Flight obj) {
		// TODO Auto-generated method stub
		return false;
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
	public List<Flight> get(int id) {
		
		return null;
	}

	

	
}

