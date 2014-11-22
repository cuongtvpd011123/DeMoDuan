package com.cc.demoduan1;

import android.R;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class OnlineActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		
		Intent intent; // Reusable Intent for each tab
		//
		//Tạo các tab trong chương trình
		intent = new Intent().setClass(this, TrangChuActivity.class);
		spec = tabHost.newTabSpec("tab1").setIndicator("Trang Chủ",
				res.getDrawable(R.drawable.ic_delete))
				.setContent(intent);
		tabHost.addTab(spec);
		//
		intent = new Intent().setClass(this, PlayListActivity.class);
		spec = tabHost.newTabSpec("tab2").setIndicator("PLAYLIST",
				res.getDrawable(R.drawable.ic_delete)).setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(1);
		//
		intent = new Intent().setClass(this, BaiHatOLActivity.class);
		spec = tabHost.newTabSpec("tab3").setIndicator("Bài Hát",
				res.getDrawable(R.drawable.ic_delete)).setContent(intent);
		tabHost.addTab(spec);
		//
		intent = new Intent().setClass(this, NgheSiOLActivity.class);
		spec = tabHost.newTabSpec("tab4").setIndicator("Nghệ Sĩ",
				res.getDrawable(R.drawable.ic_delete)).setContent(intent);
		tabHost.addTab(spec);
    }
}
