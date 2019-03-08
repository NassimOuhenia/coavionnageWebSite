package com.example.jetty_jersey.DAO;

import java.util.List;

import com.example.jetty_jersey.model.Passenger;

public class PassengerDAO extends DAO<Passenger>{
	private DAOFactory daofactory;

	public PassengerDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public boolean create(Passenger obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Passenger obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Passenger obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Passenger> get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
