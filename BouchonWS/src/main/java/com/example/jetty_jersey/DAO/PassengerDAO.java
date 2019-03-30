package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.model.Passenger;
import com.example.jetty_jersey.model.Pilot;


public class PassengerDAO extends DAO<Passenger>{
	private static List<Passenger> list=new ArrayList<Passenger>();
	//un fois la bdd est en place
	//private DAOFactory daofactory;

	public PassengerDAO(DAOFactory f) {

		//daofactory = f;
	}

	@Override
	public boolean put(Passenger obj) {
		return list.add(obj);
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
	
	
public Passenger SearchPassenger(Passenger p) {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMail().equals(p.getMail())&& list.get(i).getPassword().equals(p.getPassword())  ) {
				return list.get(i);
			}
			
		}
		return null;
	}

	

	

}