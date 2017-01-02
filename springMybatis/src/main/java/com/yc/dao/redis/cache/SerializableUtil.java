package com.yc.dao.redis.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class SerializableUtil {
	/*
	 *将对象序列化 
	 */
	public static byte[] serialize( Object obj){
		ObjectOutputStream oos=null;
		ByteArrayOutputStream baos=null;
		byte[] bs=null;
		try {
			baos=new ByteArrayOutputStream(); //内存缓存输出流   
			oos=new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bs=baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if( baos!=null){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bs;
	}
	
	/**
	 * 反序列化 
	 * @param obj
	 * @return
	 */
	public static Object unserialize( byte[] bs){
		ByteArrayInputStream bais=null;
		Object obj=null;
		try {
			bais=new ByteArrayInputStream( bs );
			ObjectInputStream ois=new ObjectInputStream( bais);
			obj=ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if( bais!=null){
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
}
