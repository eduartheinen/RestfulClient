package com.utfpr.restfulclient;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

	public LoginFragment() {
	}

	public interface OnLoginListener {
		public void onLogin(String username, String password);
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);

		Button loginButton = (Button) rootView.findViewById(R.id.buttonLogin);

		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.i("onClickListener@loginButton", "hai!");
				OnLoginListener host = (OnLoginListener) getActivity();
				EditText username = (EditText) getView().findViewById(R.id.editTextUsername);
				EditText password = (EditText) getView().findViewById(R.id.editTextPassword);

				host.onLogin(username.getText().toString(), password.getText().toString());
			}
		});
		return rootView;
	}
}