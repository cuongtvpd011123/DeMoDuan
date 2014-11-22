package com.cc.demoduan1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DanhSachBHActivity extends Activity {
	TextView Bai1, Bai2, Bai3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_bh);
		Bai1 = (TextView) findViewById(R.id.textView1);
		Bai2 = (TextView) findViewById(R.id.textView2);
		Bai3 = (TextView) findViewById(R.id.textView3);
		Bai1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, BaihatActivity.class);
				startActivity(intent); 
			}
		});
		Bai2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat2Activity.class);
				startActivity(intent);
			}
		});
		Bai3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat3Activity.class);
				startActivity(intent);
			}
		});
	}
}
