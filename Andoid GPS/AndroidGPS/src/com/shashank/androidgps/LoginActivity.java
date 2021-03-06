package com.shashank.androidgps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et = (EditText)findViewById(R.id.editText1);
				
				if (et.getText().length() <= 0)
				{
					et.setError("Please enter your name");
					return ;
				} else 
				{
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					intent.putExtra("username", et.getText().toString());
					startActivity(intent);
				}

			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
