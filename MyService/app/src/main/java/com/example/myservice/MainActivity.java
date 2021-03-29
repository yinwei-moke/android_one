package com.example.myservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ONGOING_NOTIFICATION_ID = 1;

    //这里调用了service里面Binder 此activity绑定了里面的service的Binder方法
    private MyService.DownLoadBinder binder;

    /**
     * onServiceConnected（）会在service绑定Activity时调用
     * 这个时候就可以在activity里面结合场景使用service
     */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            binder = (MyService.DownLoadBinder) service;
            binder.startBinder();
            binder.getProgress();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBt = (Button) findViewById(R.id.start);//启动service
        Button stopBt = (Button) findViewById(R.id.stop);//结束service
        Button bindActivity = (Button) findViewById(R.id.bindActivity);//绑定service 启动activity一起启动service
        Button unBindActivity = (Button) findViewById(R.id.unBindActivity);//绑定service 销毁activity后结束service
        startBt.setOnClickListener(this);
        stopBt.setOnClickListener(this);
        bindActivity.setOnClickListener(this);
        unBindActivity.setOnClickListener(this);
        Button startIntentActivity = (Button) findViewById(R.id.startIntentActivity);//使用IntentActivity
        startIntentActivity.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stop:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bindActivity:
                Intent intent = new Intent(this, MyService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unBindActivity:
                unbindService(connection);
                break;
            case R.id.startIntentActivity:
                Log.v("yw", "Thread id is " + Thread.currentThread().getName());
                Intent intent1 = new Intent(this, MainActivity.class);
                startService(intent1);
                break;
            default:
                break;
        }

    }
}