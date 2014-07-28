package com.utfpr.restfulclient;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.utfpr.restfulclient.LoginFragment.OnLoginListener;
import com.utfpr.restfulclient.helper.Callback;
import com.utfpr.restfulclient.helper.NetworkHandler;
import com.utfpr.restfulclient.model.User;

public class LoginActivity extends Activity implements OnLoginListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new LoginFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLogin(String username, String password) {
		SharedPreferences config = getPreferences(LoginActivity.MODE_PRIVATE);
		SharedPreferences.Editor editor = config.edit();
		editor.putString("lastLogin", username);
		NetworkHandler rest = NetworkHandler.getInstance();
		try {
			rest.write("http://10.0.2.2:8080/restfulServer/login", User.class,
					new User(null, username, password, null), new Callback<User>() {

						@Override
						public void callback(User user) {
							try {
								handleLogin(user);
							} catch (Exception e) {
								Toast.makeText(
										getApplicationContext(),
										"Problemas com o acesso. Verifique seus dados e tente novamente.",
										Toast.LENGTH_LONG).show();
							}

						}

					});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleLogin(User user) {
		Log.i("handleLogin@LoginActivity", "hai!");
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("user", user);

		startActivity(intent);
	}
}
