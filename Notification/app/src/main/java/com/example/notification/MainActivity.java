package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startNoBt = (Button) findViewById(R.id.startN);
        startNoBt.setOnClickListener(this);
        Button pending = (Button) findViewById(R.id.pending);
        pending.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.startN:

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification.Builder notification = new Notification.Builder(this);
                /**
                 * 创建一个ID为normal通知渠道，只会在第一次执行的时候才会创建
                 * 当下一次执行创建代码时，系统会检测到该通知渠道已经存在，就不会影响效率
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelID = "minecraft";
                    String channelName = "我的世界";
                    if (manager != null) {
                        manager.createNotificationChannel(new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH));
                    }
                    notification.setChannelId(channelID);
                }
                notification
                        .setContentTitle("This is yw 通知")//指定标题内容
                        .setContentText("这是通知的内容")//通知正文
                        .setWhen(System.currentTimeMillis())//通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .build();
                manager.notify(1, notification.build());

                break;

            case R.id.pending:
                NotificationManager manager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


                Intent intent = new Intent(this, com.example.notification.Notification.class);
                TaskStackBuilder builder = TaskStackBuilder.create(this);
                builder.addNextIntentWithParentStack(intent);
                PendingIntent pi = builder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);//获取PendingIntent对象
                Notification.Builder notification2 = new Notification.Builder(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelID = "minecraft";
                    String channelName = "我的世界";
                    if (manager2 != null) {
                        manager2.createNotificationChannel(new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH));
                    }
                    notification2.setChannelId(channelID);
                }
                notification2
                        .setContentTitle("This is yw 通知")//指定标题内容
                        .setContentText("这是通知的内容")//通知正文
                        .setWhen(System.currentTimeMillis())//通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher)//设置通知的小图标
                        .setContentIntent(pi)//通知跳转到 pi 指定的页面
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .build();
                manager2.notify(2, notification2.build());
                break;
            default:
                break;
        }
    }
}