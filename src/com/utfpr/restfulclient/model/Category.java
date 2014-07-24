package com.utfpr.restfulclient.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {
	private String id, name;

	public Category() {
	}

	public Category(String id, String name) {
		this.id = id;
		this.setName(name);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("name", this.name);
		return obj.toString();
	}
}
