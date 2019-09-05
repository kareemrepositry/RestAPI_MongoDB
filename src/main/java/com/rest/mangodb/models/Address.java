package com.rest.mangodb.models;


public class Address {
	
	public String Location;
	public String city;
	public String state;
	
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Address() {
		
	}
	
	public Address(String location, String city, String state) {
		super();
		Location = location;
		this.city = city;
		this.state = state;
	}
	
	
	
	


}
