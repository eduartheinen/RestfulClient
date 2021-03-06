package com.utfpr.restfulclient.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String id, username, password, email;

	public User() {
	}

	public User(String id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("username", this.username);
		obj.put("password", this.password);
		obj.put("email", this.email);
		return obj.toString();
	}
}
