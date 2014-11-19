package com.cc.demoduan1;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText txtuser, txtpassword;
	ImageView imglogin;
	//1. Khai bao file luu tru
	public static final String pre_DataLogin = "Pre_Login";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtuser = (EditText) findViewById(R.id.txtuser);
		txtpassword = (EditText) findViewById(R.id.txtpassword);
		imglogin = (ImageView) findViewById(R.id.imglogin);
		imglogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String u = txtuser.getText().toString();
				String p = txtpassword.getText().toString();
				if(u.equals("cuongtvpd01123") && p.equals("cugio1999!#")){
					savePreferences();
					Toast.makeText(getApplicationContext(), "Login thanh cong!!!", Toast.LENGTH_SHORT).show();
					finish();
				}else {
					Toast.makeText(getApplicationContext(), "Login that bai!!!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
	}
	//Luu data sharedPreferences
	protected void savePreferences() {
		// create the shared preferences object
		SharedPreferences loginShardPreference = getSharedPreferences(
				pre_DataLogin, Activity.MODE_PRIVATE);

		// obtain an editor to add data to (my)SharedPreferences object
		SharedPreferences.Editor myEditor = loginShardPreference.edit();

		// put some <key/value> data in the preferences object
		myEditor.putString("username", txtuser.getText().toString());
		myEditor.putString("password", txtpassword.getText().toString());

		myEditor.commit();
	}// savePreferences
}
