package com.cc.demoduan1;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class OnlineActivity extends Activity {
	public static String URL="http://www.nhaccuatui.com/";
	WebView webview ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online);
		webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(URL);
}
}
