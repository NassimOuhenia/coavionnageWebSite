package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.model.Plane;

public class PlaneDAO extends DAO<Plane>{
	
	private static List<Plane> listPlane=new ArrayList<Plane>();
	// pour l'instant base de donnée pas mise en place
	//private DAOFactory daofactory;

	public PlaneDAO(DAOFactory f) {
		//daofactory = f;
	}
	
	@Override
	public boolean put(Plane obj) {
		
		return listPlane.add(obj);
	}

	@Override
	public boolean delete(Plane obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Plane obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Plane> get() {
		
		return listPlane;
	}

	

}
