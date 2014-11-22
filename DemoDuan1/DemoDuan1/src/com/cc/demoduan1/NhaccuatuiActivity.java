package com.cc.demoduan1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NhaccuatuiActivity extends Activity {
TextView lblbaihat;
Button btndn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nhaccuatui);
		btndn = (Button)findViewById(R.id.btndn);
		btndn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NhaccuatuiActivity.this, LoginActivity.class);
				startActivity(intent); 
			}
		});
		lblbaihat=(TextView)findViewById(R.id.lblbaihat);
		lblbaihat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NhaccuatuiActivity.this, DanhSachBHActivity.class);
				startActivity(intent);
			}
		});
	}
}
