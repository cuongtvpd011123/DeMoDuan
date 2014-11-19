package com.cc.demoduan1;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class PlayListsActivity extends ListActivity {
	public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_lists);

		ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();

		SongsManager plm = new SongsManager();
		// láº¥y táº¥t cáº£ bÃ i hÃ¡t tá»« sdcard
		this.songsList = plm.getPlayList();

		// láº·p danh sÃ¡ch
		for (int i = 0; i < songsList.size(); i++) {
			// táº¡o má»›i HashMap
			HashMap<String, String> song = songsList.get(i);

			// thÃªm HashList Ä‘áº¿n ArrayList
			songsListData.add(song);
		}

		// ThÃªm menuItems Ä‘áº¿n ListView
		ListAdapter adapter = new SimpleAdapter(this, songsListData,
				R.layout.playlist_item, new String[] { "songTitle" }, new int[] {
						R.id.songTitle });

		setListAdapter(adapter);

		ListView lv = getListView();
		// gÃ¡n sá»± kiá»‡n cho má»—i bÃ i hÃ¡t Ä‘Æ°á»£c chá»�n
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// láº¥y vá»‹ trÃ­ bÃ i hÃ¡t
				int songIndex = position;
				
				Intent in = new Intent(getApplicationContext(),
						ThietBiDDActivity.class);
				// Gá»­i vá»‹ trÃ­ bÃ i hÃ¡t Ä‘áº¿n PlayerActivity
				in.putExtra("songIndex", songIndex);
				setResult(100, in);
				// Ä�Ã³ng PlayListView
				finish();
			}
		});

	}
}
