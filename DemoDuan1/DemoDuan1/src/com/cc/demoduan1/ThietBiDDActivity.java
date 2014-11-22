package com.cc.demoduan1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ThietBiDDActivity extends Activity implements OnCompletionListener, SeekBar.OnSeekBarChangeListener {
	private ImageButton btnPlay;
	private ImageButton btnForward;
	private ImageButton btnBackward;
	private ImageButton btnNext;
	private ImageButton btnPrevious;
	private ImageButton btnPlaylist;
	private ImageButton btnRepeat;
	private ImageButton btnShuffle;
	private SeekBar songProgressBar;
	private TextView songTitleLabel;
	private TextView songCurrentDurationLabel;
	private TextView songTotalDurationLabel;
	// Media Player
	private  MediaPlayer mp;
	// Handler Ä‘á»ƒ cáº­p nháº­t UI timer, progress bar,...
	private Handler mHandler = new Handler();;
	private SongsManager songManager;
	private Utilities utils;
	private int seekForwardTime = 5000; // 5000 ms
	private int seekBackwardTime = 5000; // 5000 ms
	private int currentSongIndex = 0; 
	private boolean isShuffle = false;
	private boolean isRepeat = false;
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players);
		
		// CÃ¡c nÃºt chÆ¡i nháº¡c
		btnPlay = (ImageButton) findViewById(R.id.btnPlay);
		btnForward = (ImageButton) findViewById(R.id.btnForward);
		btnBackward = (ImageButton) findViewById(R.id.btnBackward);
		btnNext = (ImageButton) findViewById(R.id.btnNext);
		btnPrevious = (ImageButton) findViewById(R.id.btnPrevious);
		btnPlaylist = (ImageButton) findViewById(R.id.btnPlaylist);
		btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
		btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
		songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
		songTitleLabel = (TextView) findViewById(R.id.songTitle);
		songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
		songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
		
		// Mediaplayer
		mp = new MediaPlayer();
		songManager = new SongsManager();
		utils = new Utilities();
		
		// Listeners
		songProgressBar.setOnSeekBarChangeListener(this); // quan trá»�ng
		mp.setOnCompletionListener(this); // quan trá»�ng
		
		// Láº¥y danh sÃ¡ch bÃ i hÃ¡t tá»« tháº» nhá»›
		songsList = songManager.getPlayList();
		
		// Máº·c Ä‘á»‹nh chÆ¡i bÃ i hÃ¡t Ä‘áº§u tiÃªn trong danh sÃ¡ch
		if(songsList.size() > 0)
			playSong(0);

		/**
		 * Sá»± kiá»‡n nÃºt Play
		 * chÆ¡i bÃ i hÃ¡t vÃ  chuyá»ƒn Ä‘áº¿n nÃºt Pause
		 * táº¡m dá»«ng má»™t bÃ i hÃ¡t vÃ  chuyá»ƒn thÃ nh nÃºt play
		 * */
		btnPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// kiá»ƒm tra cÃ³ Ä‘ang chÆ¡i bÃ i nÃ o khÃ´ng
				if(mp.isPlaying()){
					if(mp!=null){
						mp.pause();
						// Thay Ä‘á»•i Ä‘áº¿n nÃºt pause
						btnPlay.setImageResource(R.drawable.btn_play);
					}
				}else{
					// trá»Ÿ láº¡i chÆ¡i bÃ i hÃ¡t
					if(mp!=null && songsList.size()>0){
						mp.start();
						// chuyá»ƒn nÃºt nháº¥n Ä‘áº¿n nÃºt pause.
						btnPlay.setImageResource(R.drawable.btn_pause);
					}
				}
			}
		});
		
		/**
		 * Sá»± kiá»‡n cho nÃºt Forward
		 * chuyá»ƒn bÃ i hÃ¡t Ä‘áº¿n giÃ¢y káº¿ tiáº¿p nÃ o Ä‘Ã³
		 * */
		btnForward.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// láº¥y vá»‹ trÃ­ bÃ i hÃ¡t hiá»‡n táº¡i				
				int currentPosition = mp.getCurrentPosition();
				// kiá»ƒm tra náº¿u thá»� gian seekForward nhá»� hÆ¡n thá»�i lÆ°á»£ng bÃ i hÃ¡t
				if(currentPosition + seekForwardTime <= mp.getDuration()){
					// chuyá»ƒn bÃ i hÃ¡t Ä‘áº¿n vá»‹ trÃ­ Ä‘Ã³
					mp.seekTo(currentPosition + seekForwardTime);
				}else{
					// chuyá»ƒn Ä‘áº¿n vá»‹ trÃ­ káº¿t thÃºc bÃ i hÃ¡t
					mp.seekTo(mp.getDuration());
				}
			}
		});
		
		/**
		 * Sá»± kiá»‡n cho nÃºt Backward
		 * Chuyá»ƒn lÃ¹i bÃ i hÃ¡t Ä‘áº¿n vá»‹ trÃ­ nÃ o Ä‘Ã³
		 * */
		btnBackward.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// láº¥y vá»‹ trÃ­ bÃ i hÃ¡t hiá»‡n hÃ nh				
				int currentPosition = mp.getCurrentPosition();
				// náº¿u thá»�i gian seekBackward >=0
				if(currentPosition - seekBackwardTime >= 0){
					// chuyá»ƒn lÃ¹i bÃ i hÃ¡t Ä‘áº¿n vá»‹ trÃ­ Ä‘Ã³.
					mp.seekTo(currentPosition - seekBackwardTime);
				}else{
					// chuyá»ƒn vá»� vá»‹ trÃ­ Ä‘áº§u
					mp.seekTo(0);
				}
			}
		});
		
		/**
		 * Sá»± kiá»‡n cho nÃºt Next
		 * ChÆ¡i bÃ i hÃ¡t káº¿ tiáº¿p vá»›i vá»‹ trÃ­ currentSongIndex + 1
		 * */
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// náº¿u bÃ i hÃ¡t hiá»‡n táº¡i khÃ´ng lÃ  bÃ i cuá»‘i cÃ¹ng trong danh sÃ¡ch
				if(currentSongIndex < (songsList.size() - 1)){
					playSong(currentSongIndex + 1);
					currentSongIndex = currentSongIndex + 1;
				}else{
					// chÆ¡i bÃ i Ä‘áº§u tiÃªn náº¿u bÃ i hiá»‡n táº¡i lÃ  bÃ i cuá»‘i cÃ¹ng
					if(songsList.size()>0) {
						playSong(0);
						currentSongIndex = 0;
					}
				}
			}
		});
		
		/**
		 * Sá»± kiá»‡n cho nÃºt Back
		 * ChÆ¡i bÃ i hÃ¡t trÆ°á»›c Ä‘Ã³ vá»›i vá»‹ trÃ­ currentSongIndex - 1
		 * */
		btnPrevious.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(currentSongIndex > 0){
					playSong(currentSongIndex - 1);
					currentSongIndex = currentSongIndex - 1;
				}else{
					// chÆ¡i bÃ i cuá»‘i cÃ¹ng
					if(songsList.size()>0) {
						playSong(songsList.size() - 1);
						currentSongIndex = songsList.size() - 1;
					}
				}
				
			}
		});
		
		/**
		 * Sá»± kiá»‡n cho nÃºt Repeat
		 * Báº­t cá»� Repeat
		 * */
		btnRepeat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(isRepeat){
					isRepeat = false;
					Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
					btnRepeat.setImageResource(R.drawable.btn_repeat);
				}else{
					// gÃ¡n cá»� Repeat = true
					isRepeat = true;
					Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
					// gÃ¡n cá»� Shuffle = false
					isShuffle = false;
					btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
					btnShuffle.setImageResource(R.drawable.btn_shuffle);
				}	
			}
		});
		
		/**
		 * Sá»± kiá»‡n cho nÃºt Shuffle
		 * Báº­t cá»� Shuffle
		 * */
		btnShuffle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(isShuffle){
					isShuffle = false;
					Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
					btnShuffle.setImageResource(R.drawable.btn_shuffle);
				}else{
					// gÃ¡n cá»� Shuffle = true
					isShuffle= true;
					Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
					// gÃ¡n cá»� shuffle = false
					isRepeat = false;
					btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
					btnRepeat.setImageResource(R.drawable.btn_repeat);
				}	
			}
		});
		
		/**
		 * GÃ¡n sá»± kiá»‡n cho nÃºt Play List
		 * Khá»Ÿi Ä‘á»™ng giao diá»‡n hiá»ƒn thá»‹ danh sÃ¡ch bÃ i hÃ¡t
		 * */
		btnPlaylist.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), PlayListsActivity.class);
				startActivityForResult(i, 100);			
			}
		});
		
	}
	
	/**
	 * Nháº­n bÃ i hÃ¡t Ä‘Æ°á»£c chá»�n tá»« danh sÃ¡ch
	 * vÃ  chÆ¡i bÃ i hÃ¡t Ä‘Ã³
	 * */
	@Override
    protected void onActivityResult(int requestCode,
                                     int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
         	 currentSongIndex = data.getExtras().getInt("songIndex");
         	 // chÆ¡i bÃ i hÃ¡t Ä‘Æ°á»£c chá»�n
             playSong(currentSongIndex);
        }
 
    }
	
	/**
	 * HÃ m Ä‘áº¿ chÆ¡i má»™t bÃ i hÃ¡t
	 * @param songIndex - thá»© tá»± bÃ i hÃ¡t trong danh sÃ¡ch (tÃ­nh tá»« 0)
	 * */
	public void  playSong(int songIndex){
		// ChÆ¡i bÃ i hÃ¡t
		try {
        	mp.reset();
			mp.setDataSource(songsList.get(songIndex).get("songPath"));
			mp.prepare();
			mp.start();
			// Hiá»ƒn thá»‹ tiÃªu Ä‘á»� bÃ i hÃ¡t
			String songTitle = songsList.get(songIndex).get("songTitle");
        	songTitleLabel.setText(songTitle);
			
        	// Thay Ä‘á»•i button play thÃ nh nÃºt pause
			btnPlay.setImageResource(R.drawable.btn_pause);
			
			// gÃ¡n giÃ¡ trá»‹ progress bar
			songProgressBar.setProgress(0);
			songProgressBar.setMax(100);
			
			// cáº­p nháº­t progress bar
			updateProgressBar();			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cáº­p nháº­t timer trÃªn seekbar
	 * */
	public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);        
    }	
	
	/**
	 * Background Runnable thread
	 * */
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
			   long totalDuration = mp.getDuration();
			   long currentDuration = mp.getCurrentPosition();
			  
			   // hiá»ƒn thá»‹ tá»•ng thá»�i gian bÃ i hÃ¡t
			   songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
			   // Hiá»ƒn thá»‹ lÆ°á»£ng thá»�i gian bÃ i hÃ¡t Ä‘Ã£ chÆ¡i
			   songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));
			   
			   // Cáº­p nháº­t cho progress
			   int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
			   //Log.d("Progress", ""+progress);
			   songProgressBar.setProgress(progress);
			   
			   // Cháº¡y luá»“ng nÃ y sau má»—i 100 ms
		       mHandler.postDelayed(this, 100);
		   }
		};
		
	/**
	 * 
	 * */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		
	}

	/**
	 * Khi ngÆ°á»�i dÃ¹ng báº¯t Ä‘áº§u di chuyá»ƒn thanh trÆ°á»£t
	 * */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// gá»¡ bá»� Handler tá»« viá»‡c cáº­p nháº­t progress
		mHandler.removeCallbacks(mUpdateTimeTask);
    }
	
	/**
	 * Khi ngÆ°á»�i dÃ¹ng di chuyá»ƒn thanh trÆ°á»£t
	 * */
	@Override
    public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);
		
		// di chuyá»ƒn Ä‘áº¿n giÃ¢y nÃ o Ä‘Ã³
		mp.seekTo(currentPosition);
		
		// cáº­p nháº­t timer cho progress
		updateProgressBar();
    }

	/**
	 * khi bÃ i hÃ¡t vá»«a chÆ¡i xong
	 * náº¿u repeat = true chÆ¡i bÃ i hÃ¡t Ä‘Ã³ má»™t láº§n ná»¯a
	 * náº¿u shuffle = true chÆ¡i ngáº«u nhiÃªn bÃ i hÃ¡t
	 * */
	@Override
	public void onCompletion(MediaPlayer arg0) {
		
		// kiá»ƒm tra repeat = true hoáº·c false
		if(isRepeat){
			// repeat = true chÆ¡i bÃ i hÃ¡t láº§n ná»¯a
			playSong(currentSongIndex);
		} else if(isShuffle){
			// shuffle = true, chÆ¡i bÃ i ngáº«u nhiÃªn
			Random rand = new Random();
			currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
			playSong(currentSongIndex);
		} else{
			// repeat = false && shuffle = false - chÆ¡i bÃ i káº¿ tiáº¿p
			if(currentSongIndex < (songsList.size() - 1)){
				playSong(currentSongIndex + 1);
				currentSongIndex = currentSongIndex + 1;
			}else{
				// chÆ¡i bÃ i Ä‘áº§u tiÃªn
				if(songsList.size()>0) {
					playSong(0);
					currentSongIndex = 0;
				}
			}
		}
	}
	
	@Override
	 public void onDestroy(){
	 super.onDestroy();
	    mp.release();
	 }
	

}
