package com.utfpr.restfulclient.helper;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JsonHelper {
	private static String serverAddress = "http://192.168.0.5:8080/restfulServer/";
	private static AsyncHttpClient client = new AsyncHttpClient();

	public static void get(String resourcePath, RequestParams params,
			JsonHttpResponseHandler responseHandler) {
		//loopj
		client.get(serverAddress + resourcePath, params, responseHandler);
		
		//jersey
		ClientConfig cc = new DefaultClientConfig();
	    Client c = Client.create(cc);
	    WebResource r = c.resource(serverAddress);
	}

	public static void post(Context context, String resourcePath, String obj,
			AsyncHttpResponseHandler responseHandler) {

		ByteArrayEntity entity;
		try {
			entity = new ByteArrayEntity(obj.getBytes("UTF-8"));
			client.post(context, serverAddress + resourcePath, entity, "application/json",
					responseHandler);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
