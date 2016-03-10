package com.example.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface TaskRepository extends MongoRepository<Task, Integer>{
	 List<Task> findByTaskArchived(@Param("archivedfalse") int taskArchivedFalse);
	 List<Task> findByTaskStatus(@Param("status") String taskStatus);
	 
}
