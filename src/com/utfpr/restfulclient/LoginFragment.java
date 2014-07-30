package com.utfpr.restfulclient;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

	EditText username, password;
	Button loginButton;

	public LoginFragment() {
	}

	public interface OnLoginListener {
		public void onLogin(String username, String password);
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);
		username = (EditText) rootView.findViewById(R.id.editTextUsername);
		password = (EditText) rootView.findViewById(R.id.editTextPassword);
		loginButton = (Button) rootView.findViewById(R.id.buttonLogin);

		loginButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				login();
			}
		});

		SharedPreferences config = getActivity().getSharedPreferences(
				"ConfigFile", LoginActivity.MODE_PRIVATE);
		String lastLogin = config.getString("lastLogin", "");
		username.setText(lastLogin);

		return rootView;
	}

	private void login() {
		Log.i("onClickListener@loginButton", "hai!");
		OnLoginListener host = (OnLoginListener) getActivity();

		host.onLogin(username.getText().toString(), password.getText()
				.toString());
	}
}