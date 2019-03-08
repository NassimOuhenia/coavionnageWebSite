package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;
import com.example.jetty_jersey.model.Pilot;

public class PilotDAO extends DAO<Pilot> {
	private static List<Pilot> list=new ArrayList<Pilot>();
	//un fois la bdd est en place
	//private DAOFactory daofactory;

	public PilotDAO(DAOFactory f) {

	//	daofactory = f;
	}

	@Override
	public boolean put(Pilot obj) {
		// TODO Auto-generated method stub
		return list.add(obj);
	}

	@Override
	public boolean delete(Pilot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pilot obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pilot> get() {
		return list;
	}

}
