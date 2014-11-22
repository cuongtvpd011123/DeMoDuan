package com.cc.demoduan1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DanhSachBHActivity extends Activity {
	TextView Bai1, Bai2, Bai3, Bai4, Bai5, Bai6,
			Bai7, Bai8, Bai9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_bh);
		Bai1 = (TextView) findViewById(R.id.textView1);
		Bai2 = (TextView) findViewById(R.id.textView2);
		Bai3 = (TextView) findViewById(R.id.textView3);
		Bai4 = (TextView) findViewById(R.id.textView4);
		Bai5 = (TextView) findViewById(R.id.textView5);
		Bai6 = (TextView) findViewById(R.id.textView6);
		Bai7 = (TextView) findViewById(R.id.textView7);
		Bai8 = (TextView) findViewById(R.id.textView8);
		Bai9 = (TextView) findViewById(R.id.textView9);
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
		Bai4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat4Activity.class);
				startActivity(intent);
			}
		});
		Bai5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat5Activity.class);
				startActivity(intent);
			}
		});
		Bai6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat6Activity.class);
				startActivity(intent);
			}
		});
		Bai7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat7Activity.class);
				startActivity(intent);
			}
		});
		Bai8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat8Activity.class);
				startActivity(intent);
			}
		});
		Bai9.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DanhSachBHActivity.this, Baihat9Activity.class);
				startActivity(intent);
			}
		});
	}
}
