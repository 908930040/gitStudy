package com.yc.dao.redis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;


public class RedisCache implements Cache{
	
	/**
	 * 日志对象 
	 */
	private static Logger logger=LogManager.getLogger( RedisCache.class);
	
	/**
	 * 对象的编号
	 */
	private String id;
	
	private Jedis redisClient=createRedis();
	
	//用于同步的锁 
	private ReadWriteLock readWriteLock=new ReentrantReadWriteLock( );
	
	public RedisCache(String id) {
		if( id==null){
			throw new IllegalArgumentException("Cache instance requires an Id");
		}
		logger.debug("create an cache instance with id:"+id);
		this.id=id;
	}
	
	/**
	 *jedis 从连接池中获取
	 * @return
	 */
	public static Jedis createRedis() {
		Jedis jedis=RedisPool.getPool().getResource();
		return jedis;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	

	@Override
	public void putObject(Object key, Object value) {
		byte[] keybyte=SerializableUtil.serialize(key);
		byte[] valuebyte=SerializableUtil.serialize(value);
		this.redisClient.set(keybyte, valuebyte);
	}

	@Override
	public Object getObject(Object key) {
		//缓存穿透 
		byte[] values=this.redisClient.get( SerializableUtil.serialize(key));
		if( values==null){
			return null;
		}
		Object obj=SerializableUtil.unserialize(values);
		return obj;
	}

	@Override
	public Object removeObject(Object key) {
		byte[] keybyte=SerializableUtil.serialize(key);
		return this.redisClient.expire(keybyte, 0);
	}

	@Override
	public void clear() {
		this.redisClient.flushDB();
	}

	@Override
	public int getSize() {
		long size=this.redisClient.dbSize();
		int s=Integer.valueOf(size+"");
		return s;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}
