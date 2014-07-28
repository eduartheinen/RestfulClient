package com.utfpr.restfulclient.helper;

import android.os.AsyncTask;
import android.util.Log;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetTask extends AsyncTask<String, String, String> {

	private final String url;
	private final Callback<String> callback;

	public GetTask(String url, Callback<String> callback) {
		this.url = url;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(String... params) {
		final Client client = Client.create();
		final WebResource resource = client.resource(url);
		final ClientResponse response = resource.accept(
				MIMETypes.APPLICATION_JSON.getName()).get(ClientResponse.class);
		
		if (response.getStatus() != 201 && response.getStatus() != 200) {
			Log.e("response", response.toString());
		}

		String responseEntity = response.getEntity(String.class);
		Log.i("doInBackground@GetTask", responseEntity);
		
		return responseEntity;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		callback.callback(result);
	}

}
