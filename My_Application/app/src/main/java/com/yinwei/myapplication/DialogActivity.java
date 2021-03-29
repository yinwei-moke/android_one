package com.yinwei.myapplication;

import android.app.Activity;
import android.os.Bundle;

//如果使用对话框形式打开页面 需要继承Acitvity
public class DialogActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }
}
