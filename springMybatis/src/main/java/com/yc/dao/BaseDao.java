package com.yc.dao;

import java.util.List;

import com.yc.bean.Person;

public interface BaseDao<T> {
	
	public List<T> findAll(T t,String sqlId);
}
