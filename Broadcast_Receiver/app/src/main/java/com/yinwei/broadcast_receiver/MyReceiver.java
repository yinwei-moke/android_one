package com.yinwei.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) throws UnsupportedOperationException {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "接受自定义标准广播（异步，所有流程同步进行）", Toast.LENGTH_SHORT).show();
        //截断有序广播必须在标准广播 （内吗？）
        abortBroadcast();
    }
}