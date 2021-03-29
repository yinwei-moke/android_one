package com.example.androidthreadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//ANDROID不允许在子线程改变UI，必须在主线程下使用
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int UPDATAE_TEXT = 2;
    private TextView textView;

    //Handler主要用于发送和处理消息
    private Handler handler = new Handler() {
        //这里打错过一个单词 handlerMessage => handleMessage
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("yw", "是否进入改变UI");
            switch (msg.what) {
                case UPDATAE_TEXT:
                    textView.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        Button changeBt = (Button) findViewById(R.id.change);
        changeBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         *这里使用异步消息处理机制
                         *Message是线程之间传递的消息
                         *MessageQueue是消息队列的意思，存储所有Message
                         *Looper是每一个 MessageQueue的管家调用Looper的loop（）就会进入到一个无线循环之中
                         * 当MessageQueue每有一条Message就会将它取出来
                         */
                        Message message = new Message();
                        message.what = UPDATAE_TEXT;
                        Log.v("yw", "是否进入此线程");
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}