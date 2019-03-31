package com.example.jetty_jersey.DAO;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;

import com.example.jetty_jersey.model.Reservation;

public class ReservationDAO extends DAO<Reservation> {
	
	private DAOFactory daofactory;
	
	public ReservationDAO(DAOFactory f) {
		daofactory = f;
	}
	

	@Override
	public String put(Reservation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation obj, String idPassenger) {
		// TODO Auto-generated method stub
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

}
