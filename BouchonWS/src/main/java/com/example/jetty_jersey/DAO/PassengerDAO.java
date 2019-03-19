package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;

import com.example.jetty_jersey.model.Passenger;


public class PassengerDAO extends DAO<Passenger>{
	private static List<Passenger> list=new ArrayList<Passenger>();
	//un fois la bdd est en place
	//private DAOFactory daofactory;

	public PassengerDAO(DAOFactory f) {

		//daofactory = f;
	}

	@Override
	public IndexResponse put(Passenger obj) {
		return null;
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
	public List<Passenger> get() {
		
		return list;
	}

	

	

}
