package com.example.mongo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DuplicateKeyException;

public class AutoIncrement<T> {
	 
	MongoTemplate mongoTemplate;
	T objectforT;
	String collectionname;
	public AutoIncrement(T obj,MongoTemplate mongotemplate){
		this.objectforT=obj;
		this.mongoTemplate=mongotemplate;
	}
	public void  autoIncrement() throws Exception{
		 
		  while(true){
			 
			 DB db= mongoTemplate.getDb();
			 BasicDBObject obj= new BasicDBObject();
			 Annotation anotation= ((Annotation)objectforT.getClass().getAnnotations()[0]);
		     try {
		    	 collectionname=anotation.annotationType().getDeclaredMethods()[1].invoke(anotation, (Object[])null ).toString();
				System.out.println(collectionname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			}
		     
			 obj.append("_id", -1);
			 DBCollection collection=db.getCollection(collectionname);
			 DBCursor seq = collection.find(new BasicDBObject(),new BasicDBObject("_id",1)).sort(obj);
			//reflection for method setter
			 
			 Class<?> aclass=objectforT.getClass();
			 try {
				Method setIdMethod = aclass.getMethod("setId",int.class);
				setIdMethod.invoke(objectforT,seq.hasNext()?(int) seq.next().get("_id")+1:1);
				Method getIdMethod = aclass.getMethod("getId");
				System.out.println(getIdMethod.invoke(objectforT));
			} catch (Exception e ) {
				// TODO Auto-generated catch block
				throw e;
			}			 
			 
			 try {
				mongoTemplate.insert(objectforT,collectionname);
				break;
			} catch (DuplicateKeyException e) {
				System.out.println(e.getMessage());
				continue;
			}catch (Exception e) {
				throw e;
			}
		 }
		  
		  
		 
	  }	
}
