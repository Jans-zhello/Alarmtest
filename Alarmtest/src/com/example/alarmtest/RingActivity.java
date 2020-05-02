package com.example.alarmtest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class RingActivity extends Activity {
  private MediaPlayer mplPlayer;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ring);
		mplPlayer = MediaPlayer.create(this,R.raw.one);
		mplPlayer.start();
		mplPlayer.setLooping(true); 
	}
  public void stop(View view){
	 mplPlayer.stop();
	 finish();
  }
}
