package com.utfpr.restfulclient.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post {
	private String id, title, content, excerpt;
	private User author;
	private List<Category> categories;

	public Post() {

	}

	public Post(String id, String title, String content, User author,
			List<Category> categories) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.excerpt = content.substring(0, Math.min(content.length(), 255));
		this.author = author;
		this.setCategories(categories);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String toJSON() throws JSONException {
		JSONArray categories = new JSONArray();
		for (Category category : this.categories) {
			categories.put(new JSONObject(category.toJSON()));
		}

		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("author", new JSONObject(this.author.toJSON()));
		obj.put("title", this.title);
		obj.put("content", this.content);
		obj.put("excerpt", this.excerpt);
		obj.put("categories", categories);

		return obj.toString();
	}
}