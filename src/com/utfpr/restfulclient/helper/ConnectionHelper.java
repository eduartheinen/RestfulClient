package com.utfpr.restfulclient.helper;

import javax.ws.rs.core.MediaType;

import android.util.Log;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public enum ConnectionHelper {
	instance();

	private ClientConfig clientConfig;
	private Client client;
	private WebResource resource;

	private ConnectionHelper() {
		this.clientConfig = new DefaultClientConfig();
		this.client = Client.create(clientConfig);
		this.clientConfig.getProperties().put(
				JSONConfiguration.FEATURE_POJO_MAPPING, true);
		this.resource = client
				.resource("http://192.168.0.5:8080/restfulServer");
	}

	public void get(String path) {
		String teste = resource.path(path).accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		Log.i("connectionHelper#get", teste);
	}

}
