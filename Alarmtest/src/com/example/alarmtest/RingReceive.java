package com.example.alarmtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RingReceive extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		if ("com.example.alarm".equals(arg1.getAction())) {
			Log.i("test","ƒ÷÷”œÏ¡À");
			Intent intent = new Intent(arg0,RingActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			arg0.startActivity(intent);
		}
	}

}
