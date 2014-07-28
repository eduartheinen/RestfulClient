package com.utfpr.restfulclient;

import android.app.Activity;
import android.content.Intent;
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
		NetworkHandler rest = NetworkHandler.getInstance();
		rest.write("http://192.168.0.5:8080/restfulServer/login", User.class,
				new User(null, username, password, null), new Callback<User>() {

					@Override
					public void callback(User user) {
						if(user != null){
							Log.i("onLogin - callback@LoginActivity",
									user.toString());
							handleLogin(user);
						}
						Toast.makeText(getApplicationContext(), "Problemas com o acesso. Verifique seus dados e tente novamente.", Toast.LENGTH_LONG).show();
					}
				});
	}

	private void handleLogin(User user) {
		Log.i("handleLogin@LoginActivity", "hai!");
		Intent intent = new Intent(this, MainActivity.class);
		Bundle params = new Bundle();
		params.putInt("code", 123);
		params.putString("name", "Fulano");
		intent.putExtras(params);

		startActivity(intent);
	}
}
