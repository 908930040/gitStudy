package com.yc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.yc.bean.Person;
import com.yc.dao.BaseDao;
import com.yc.dao.PersonDao;

//操作sqlSession.find  selectOne insert update delete
@Repository
public class BaseDaoMybatisImpl<T> extends SqlSessionDaoSupport implements BaseDao<T>{
	
	private String mapperlocation="com.yc.mapper.";
	
	@Override
	@Resource(name="sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}



	@Override
	public List<T> findAll(T t, String sqlId) {
		return super.getSqlSession().selectList( mapperlocation+t.getClass().getSimpleName()+"Mapper."+sqlId);
	}

}
