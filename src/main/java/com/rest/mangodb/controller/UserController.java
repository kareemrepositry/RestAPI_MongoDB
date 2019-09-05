package com.rest.mangodb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoOperationsExtensionsKt;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.mangodb.repositry.UserRepositry;
import com.rest.mangodb.models.User;

@RestController
public class UserController {

	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	MongoTemplate mongotemplate;

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepositry.findAll();
	}
	
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userRepositry.save(user);
	}

	@GetMapping("/{id}")
	public User getUserByID(@PathVariable("id") String id) {
		return userRepositry.findByObjectId(id);
	}

	@PutMapping("/{id}")
	public void updateUserByID(@PathVariable("id") String id, @Valid @RequestBody User user) {
		
		System.out.println(" User Details"+user.getFirstname()+" "+user.getLastname()+" "+user.getAddress());
		
		//added by karim
		System.out.println(" User Details"+user.getFirstname()+" "+user.getLastname()+" "+user.getAddress());
	
		//added by venkat
		System.out.println(" User Details"+user.getFirstname()+" "+user.getLastname()+" "+user.getAddress());
		
		Update upd=new Update().set("firstname",user.getFirstname()).set("address", user.getAddress())
				.set("lastname", user.getLastname());
		mongotemplate.updateFirst(new Query(Criteria.where("id").is(id)), upd, User.class);
	}

	@DeleteMapping("/{id}")
	public void deleteUserByID(@PathVariable("id") String id) {
		userRepositry.delete(userRepositry.findByObjectId(id));
	}

}
