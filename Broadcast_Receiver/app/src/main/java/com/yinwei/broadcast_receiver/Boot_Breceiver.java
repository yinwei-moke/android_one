package com.yinwei.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Boot_Breceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) throws UnsupportedOperationException {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "接收静态广播", Toast.LENGTH_SHORT).show();

    }
}
