package com.utfpr.restfulclient.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.spi.service.ServiceFinder;

public class NetworkHandler {
	private static NetworkHandler instance;

	public static synchronized NetworkHandler getInstance() {
		if (instance == null) {
			instance = new NetworkHandler();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	private NetworkHandler() {
		ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());
	}

	public <T> void read(final String url, final Class<T> clazz,
			final Callback<T> callback) throws InterruptedException,
			ExecutionException {
		new GetTask(url, new Callback<String>() {

			@Override
			public void callback(String result) {
				callback.callback(new GsonBuilder().create().fromJson(result,
						clazz));
				Log.i("houston", "read@NetworkHandler: " + result);
			}
		}).execute();
	}

	public <T> void readList(final String url, final Class<T[]> clazz,
			final Callback<List<T>> callback) throws InterruptedException,
			ExecutionException {
		new GetTask(url, new Callback<String>() {

			@Override
			public void callback(String result) {
				Log.i("houston", "readList@NetworkHandler: " + result);
				final T[] array = new GsonBuilder().create().fromJson(result,
						clazz);
				callback.callback(new ArrayList<T>(Arrays.asList(array)));
			}
		}).execute();
	}

	public <T> void syncReadList(final String url, final Class<T[]> clazz,
			final Callback<List<T>> callback) throws InterruptedException,
			ExecutionException {

		GetTask task = new GetTask(url, new Callback<String>() {

			@Override
			public void callback(String result) {
				Log.i("houston", "syncReadList@NetworkHandler: " + result);
				final T[] array = new GsonBuilder().create().fromJson(result,
						clazz);
				callback.callback(new ArrayList<T>(Arrays.asList(array)));
			}
		});

		task.execute().get();
	}

	public <T> void write(final String url, final Class<T> clazz, final T t,
			final Callback<T> callback) throws InterruptedException,
			ExecutionException {

		final Gson gson = new GsonBuilder().create();
		new PostTask(url, gson.toJson(t), new Callback<String>() {

			@Override
			public void callback(String result) {
				callback.callback(gson.fromJson(result, clazz));
				Log.i("houston", "write@NetworkHandler: " + result);
			}
		}).execute();
	}
}
