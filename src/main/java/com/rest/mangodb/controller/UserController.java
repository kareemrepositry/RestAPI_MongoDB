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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.mangodb.repositry.UserRepositry;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.rest.mangodb.models.User;

@RestController
@RequestMapping("/Users")
@Api(value = "User Details", description = "User Details Fetch from MongoDB")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		})
public class UserController {

	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	MongoTemplate mongotemplate;

	
	@ApiOperation(value = "Get All User Details", response = User.class)
	@GetMapping("/")
	public List<User> getAllUsers() {
		return userRepositry.findAll();
	}

	@ApiOperation(value = "Creating New User Details", response = User.class)
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userRepositry.save(user);

	}
	
	@ApiOperation(value = "Creating Single User Details")
	@GetMapping("/{id}")
	public User getUserByID(@PathVariable("id") String id) {
		return userRepositry.findByObjectId(id);
	}
	
	@ApiOperation(value = "Update Single User Details")
	@PutMapping("/{id}")
	public void updateUserByID(@PathVariable("id") String id, @Valid @RequestBody User user) {
		Update upd=new Update().set("firstname",user.getFirstname()).set("address", user.getAddress())
				.set("lastname", user.getLastname());
		mongotemplate.updateFirst(new Query(Criteria.where("id").is(id)), upd, User.class);
	}

	@ApiOperation(value = "Deleting Single User Details")
	@DeleteMapping("/{id}")
	public void deleteUserByID(@PathVariable("id") String id) {
		userRepositry.delete(userRepositry.findByObjectId(id));
	}
	
	@ApiOperation(value = "Get User Details by first name")
	@GetMapping("/get/{name}")
	public User getUserByName(@PathVariable("name") String name) {
		return userRepositry.findByfirstname(name);
	}

}
