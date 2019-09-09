package com.rest.mangodb.repositry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rest.mangodb.models.User;


public interface UserRepositry extends MongoRepository<User,String> {
	
	User findBy_id(ObjectId id);
	
	@Query("{'id': ?0}")
	User findByObjectId(String id);

	@Query("{'firstname': ?0}")
	User findByfirstname(String name);
}
