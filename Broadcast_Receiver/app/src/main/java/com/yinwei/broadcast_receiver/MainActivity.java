package com.yinwei.broadcast_receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private IntentFilter intentFilter;

    final String service = Context.CONNECTIVITY_SERVICE;

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //接受自定义广播
//        Button button1 = (Button) findViewById(R.id.buttonPanel);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("com.yinwei.broadcaset_RECEIVER");
//                intent.setPackage(getPackageName());
//                sendBroadcast(intent);
//            }
//        });
        //尝试截断有序广播(标准广播 和 有序广播使用一个intent-filter name) 然后改动 sendBroadcast --> sendOrderedBroadcast
        Button button2 = (Button) findViewById(R.id.buttonPanel2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.yinwei.broadcaset.MY_BROADCAST");
                intent.setPackage(getPackageName());
                sendOrderedBroadcast(intent, null);
            }
        });

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        intentFilter.addAction("android.permission.CHANGE_NETWORK_STATE");
//        intentFilter.addAction("android.permission.CHANGE_WIFI_STATE");

        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        Log.d("guangbo","是否进入到销毁步骤");
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);

    }


    class NetworkChangeReceiver extends BroadcastReceiver {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(service);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        @Override
        public void onReceive(Context context, Intent intent) {

//            Toast.makeText(context,"看看是否接收到了开机广播 （静态广播）",Toast.LENGTH_SHORT).show();

            //这里条件判断应该出了问题 还没有解决 或者线程出了问题
            if (networkInfo != null && networkInfo.isConnected()) {
                Log.d("guangbo", "有网络" );
                Toast.makeText(context, "有网络 ", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("guangbo", "没有网络" );
                Toast.makeText(context, "没有网络 ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
