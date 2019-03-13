package com.example.jetty_jersey.DAO;

import java.util.List;

public abstract class DAO<T>{
	  
	  public abstract boolean put(T obj);

	  public abstract boolean delete(T obj);

	  public abstract boolean update(T obj);
	  
	  public abstract List<T> get();
	 

}
