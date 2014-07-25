package com.utfpr.restfulclient.helper;

import android.os.AsyncTask;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class PostTask extends AsyncTask<String, String, String> {
	private final String url;
	private final String requestBody;
	private final Callback<String> callback;

	PostTask(String url, String requestBody, Callback<String> callback) {
		this.url = url;
		this.requestBody = requestBody;
		this.callback = callback;
	}

	protected String doInBackground(String... params) {
		final Client client = Client.create();
		final WebResource resource = client.resource(url);
		final ClientResponse response = resource.type(
				MIMETypes.APPLICATION_JSON.getName()).post(
				ClientResponse.class, requestBody);
		if (response.getStatus() != 201 && response.getStatus() != 200) {
			throw new RuntimeException("failed: http error code = "
					+ response.getStatus());
		}
		final String responseEntity = response.getEntity(String.class)
				.replaceAll("\\\\", "");
		return responseEntity.substring(1, responseEntity.length() - 1);
	}

	protected void onPostExecute(String result) {
		callback.callback(result);
		super.onPostExecute(result);
	}
}
