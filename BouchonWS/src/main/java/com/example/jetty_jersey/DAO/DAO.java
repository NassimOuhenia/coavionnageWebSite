package com.example.jetty_jersey.DAO;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;

public abstract class DAO<T>{
	  
	  public abstract IndexResponse put(T obj);

	  public abstract boolean delete(T obj);

	  public abstract boolean update(T obj);
	  
	  public abstract List<T> get();
	 
	  public abstract java.util.Map<String, Object> get(String id);
}
