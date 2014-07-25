package com.utfpr.restfulclient.helper;

import android.os.AsyncTask;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetTask extends AsyncTask<String, String, String> {

	private final String url;
	private final Callback<String> callback;

	GetTask(String url, Callback<String> callback) {
		this.url = url;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(String... params) {
		final Client client = Client.create();
		final WebResource resource = client.resource(url);
		final ClientResponse response = resource.accept(
				MIMETypes.APPLICATION_JSON.getName()).get(ClientResponse.class);
		return response.getEntity(String.class);
	}

	@Override
	protected void onPostExecute(String result) {
		callback.callback(result);
		super.onPostExecute(result);
	}

}
