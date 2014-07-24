package com.utfpr.restfulclient.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.utfpr.restfulclient.helper.JsonHelper;

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

	// CRUD using JsonHelper
	public void create(Context context) throws JSONException {
		JsonHelper.post(context, "http://192.168.0.5:8080/restfulServer/posts",
				this.toJSON(), new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						Log.i("post#create", "created: " + arg1[1]);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						Log.i("post#create", "failure: " + arg3.getMessage());
					}
				});
	}

	// static get post by id
	public static Post getById(String id) throws ClassNotFoundException,
			SQLException {

		// if (rs.next()) {
		// Post post = new Post(rs.getString("id"), rs.getString("title"),
		// rs.getString("content"), User.getById(rs
		// .getString("user_id")),
		// Category.getCategoriesByPostId(rs.getString("id")));
		// return post;
		// }

		return null;
	}

	public static List<Post> index() {

		JsonHelper.get("http://192.168.0.5:8080/restfulServer/posts", null,
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						// TODO Auto-generated method stub
						Log.i("posts: ", response.toString());
					}
				});
		return null;
	}
}