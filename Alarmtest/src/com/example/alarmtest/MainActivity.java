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
		//��ȡ���ӵĹ�����
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	    
	}
    public void alarmOne(View view){
    	//��ȡ��ǰϵͳ��ʱ��
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //����ʱ��Ի���
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,new OnTimeSetListener() {
			
			public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
				 Calendar c=Calendar.getInstance();//��ȡ���ڶ���    
                 c.setTimeInMillis(System.currentTimeMillis());        //����Calendar����
                 c.set(Calendar.HOUR_OF_DAY, arg1);        //��������Сʱ��
                 c.set(Calendar.MINUTE, arg2);            //�������ӵķ�����
                 c.set(Calendar.SECOND, 0);                //�������ӵ�����
                 c.set(Calendar.MILLISECOND, 0);            //�������ӵĺ�����
				System.out.println("����ʱ�� :"+arg1+":"+arg2);  
				//���͹㲥
				Intent intent = new Intent(MainActivity.this, RingReceive.class);    //����Intent����
				intent.setAction("com.example.alarm");
				PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, 
                             intent, PendingIntent.FLAG_CANCEL_CURRENT);  
				//��������
				if(c.getTimeInMillis() < System.currentTimeMillis()){
                    System.out.println("�ڶ������ʱ����");
                    alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis()+24*60*60*1000,pi); 
				}else{
					long currentTime = System.currentTimeMillis();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy��-MM��dd��-HHʱmm��ss��");
					Date date = new Date(currentTime);
					System.out.println("��ǰʱ��:"+formatter.format(date));
					
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
