package com.example.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class InitMongoService {

	@Autowired
	MongoTemplate mongoTemplate;

	public void init() {
		try {
			if (!mongoTemplate.collectionExists(Person.class)) {
				Person originalperson = new Person();
				originalperson.setFirstName("firstname1");
				originalperson.setLastName("firstname1");
				AutoIncrement<Person> auto = new AutoIncrement<Person>(
						originalperson, mongoTemplate);
				auto.autoIncrement();
				Person continueperson = new Person();
				continueperson.setFirstName("firstname2");
				continueperson.setLastName("firstname2");
				auto = new AutoIncrement<Person>(continueperson, mongoTemplate);
				auto.autoIncrement();
				continueperson = new Person();
				continueperson.setFirstName("firstname3");
				continueperson.setLastName("firstname3");
				auto = new AutoIncrement<Person>(continueperson, mongoTemplate);
				auto.autoIncrement();
			}

			if (!mongoTemplate.collectionExists(Task.class)) {

				Task originalTask = new Task();
				originalTask.setTaskStatus(TaskStatus.Completed.toString());
				originalTask.setTaskDescription("none");
				originalTask.setTaskName("None");
				originalTask.setTaskPriority(TaskPriority.High.toString());
				AutoIncrement<Task> auto = new AutoIncrement<Task>(
						originalTask, mongoTemplate);
				auto.autoIncrement();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	/*
	 * private void autoIncrement(Person person){
	 * 
	 * while(true){
	 * 
	 * DB db= mongoTemplate.getDb(); BasicDBObject obj= new BasicDBObject();
	 * Annotation anotation=
	 * ((Annotation)person.getClass().getAnnotations()[0]); try {
	 * System.out.println
	 * (anotation.annotationType().getDeclaredMethods()[1].invoke(anotation,
	 * (Object[])null )); } catch (IllegalAccessException |
	 * IllegalArgumentException | InvocationTargetException | SecurityException
	 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * obj.append("_id", -1); DBCollection
	 * collection=db.getCollection("persons"); DBCursor seq =
	 * collection.find(new BasicDBObject(),new
	 * BasicDBObject("_id",1)).sort(obj); person.setId(seq.hasNext()?(int)
	 * seq.next().get("_id")+1:1); System.out.println(person.getId()); try {
	 * mongoTemplate.insert(person,"persons"); break; } catch
	 * (DuplicateKeyException e) { System.out.println(e.getMessage()); continue;
	 * }catch (Exception e) { throw e; } }
	 * 
	 * 
	 * 
	 * }
	 */
}
