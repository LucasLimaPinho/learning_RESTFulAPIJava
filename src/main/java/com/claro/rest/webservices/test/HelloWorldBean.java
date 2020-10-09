package com.claro.rest.webservices.test;

public class HelloWorldBean {

	private String message;
	private int id = 1337;
	private String key = "foo bar";

	public HelloWorldBean(String message) {
		
		this.message = message;
		this.id = id;
		this.key = key;		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + ", id=" + id + ", key=" + key + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
