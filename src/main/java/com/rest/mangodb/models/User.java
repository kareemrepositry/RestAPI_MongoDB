package com.rest.mangodb.models;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Query;

@Document(collection = "userinfo")
public class User {

	@Id
	public ObjectId _id;
	
	@Indexed(unique = true)
	public String id;
	
	
	public String firstname;
	public String lastname;
	public String address;
	
	public User() {
	}	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public User(ObjectId _id, String id, String firstname, String lastname, String address) {
		super();
		this._id = _id;
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}


	
}
