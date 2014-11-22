package com.cc.demoduan1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {
	// Ä�Æ°á»�ng dáº«n SD Card
	final String MEDIA_PATH = new String("/sdcard/");
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	
	// HÃ m dá»±ng
	public SongsManager(){
		
	}
	
	/**
	 * HÃ m Ä‘á»�c táº¥t cáº£ táº­p tin mp3 trÃªn sdcard
	 * vÃ  lÆ°u trá»¯ chÃºng trong ArrayList
	 * */
	public ArrayList<HashMap<String, String>> getPlayList(){
		File home = new File(MEDIA_PATH);
		if (home.listFiles(new FileExtensionFilter())!=null && home.listFiles(new FileExtensionFilter()).length > 0) {
			for (File file : home.listFiles(new FileExtensionFilter())) {
				HashMap<String, String> song = new HashMap<String, String>();
				song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
				song.put("songPath", file.getPath());
				
				// ThÃªm má»—i bÃ i hÃ¡t Ä‘áº¿n SongList
				songsList.add(song);
			}
		}
		return songsList;
	}
	
	/**
	 * Class Ä‘á»ƒ lá»�c nhá»¯ng táº­p tin cÃ³ Ä‘uÃ´i .mp3
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}
