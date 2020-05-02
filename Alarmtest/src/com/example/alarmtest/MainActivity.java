package com.example.alarmtest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TimePicker;

public class MainActivity extends Activity {
     private AlarmManager alarmManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取闹钟的管理者
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	    
	}
    public void alarmOne(View view){
    	//获取当前系统的时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //弹出时间对话框
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,new OnTimeSetListener() {
			
			public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
				 Calendar c=Calendar.getInstance();//获取日期对象    
                 c.setTimeInMillis(System.currentTimeMillis());        //设置Calendar对象
                 c.set(Calendar.HOUR_OF_DAY, arg1);        //设置闹钟小时数
                 c.set(Calendar.MINUTE, arg2);            //设置闹钟的分钟数
                 c.set(Calendar.SECOND, 0);                //设置闹钟的秒数
                 c.set(Calendar.MILLISECOND, 0);            //设置闹钟的毫秒数
				System.out.println("设置时间 :"+arg1+":"+arg2);  
				//发送广播
				Intent intent = new Intent(MainActivity.this, RingReceive.class);    //创建Intent对象
				intent.setAction("com.example.alarm");
				PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, 
                             intent, PendingIntent.FLAG_CANCEL_CURRENT);  
				//设置闹钟
				if(c.getTimeInMillis() < System.currentTimeMillis()){
                    System.out.println("第二天这个时候响");
                    alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis()+24*60*60*1000,pi); 
				}else{
					long currentTime = System.currentTimeMillis();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
					Date date = new Date(currentTime);
					System.out.println("当前时间:"+formatter.format(date));
					
					alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
				}
			}
		}, hour, minute, true);
        timePickerDialog.show();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
