package com.example.myservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    /**
     * 这里指定service 然后绑定给Activity
     * 随后获取ServiceConnection对象
     * 调用service里 binder里面的方法
     */
    private DownLoadBinder binder = new DownLoadBinder();

    class DownLoadBinder extends Binder{

        public void startBinder(){
            Log.v("yw", "进入Binder方法");
        }

        public int getProgress(){
            Log.v("yw", "binder绑定成功");
            return 0;

        }

    }


    //构造方法
    public MyService() {
    }

    //回调方法最开始的一个 加载布局 绑定事件

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("yw", "开始onCreate方法 启动service服务");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("yw", "进入onStartCommand方法");
        return super.onStartCommand(intent, flags, startId);
    }

    ////回调方法 销毁前
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("yw", "开始onDestroy方法 销毁service服务");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }
}