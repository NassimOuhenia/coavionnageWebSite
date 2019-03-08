package com.example.jetty_jersey.DAO;

import java.util.ArrayList;
import java.util.List;

import com.example.jetty_jersey.model.Plane;

public class PlaneDAO extends DAO<Plane>{
	
	List<Plane> listPlane=new ArrayList<Plane>();
	private DAOFactory daofactory;

	public PlaneDAO(DAOFactory f) {

		daofactory = f;
	}

	@Override
	public boolean create(Plane obj) {
		// TODO Auto-generated method stub
		return false;
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
	public List<Plane> get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
