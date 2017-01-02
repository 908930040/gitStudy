package com.yc.dao;

import java.util.List;

import com.yc.bean.Person;

public interface PersonDao {
	
	public List<Person> findAll();
}
