package com.example.shared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击事件使用SharedPreferences存储所有数据
                 */
                SharedPreferences.Editor editor  =  getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                editor.putBoolean("boolean", false);
                editor.putInt("数字", 123);
                editor.putString("String", "字符串存储");
                editor.apply();
            }
        });

        Button button1 = (Button) findViewById(R.id.Restore_Button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 拿出来sharedPreferences数据 必须指定相应name
                 * 日志打印出来成功
                 */
                SharedPreferences sharedPreferences  =  getSharedPreferences("data", Context.MODE_PRIVATE);
                String str = sharedPreferences.getString("String", "");
                int num = sharedPreferences.getInt("数字", 0);
                Boolean bool = sharedPreferences.getBoolean("boolean", false);

                Log.v("yw","字符串提取 + " + str);
                Log.v("yw","数字提取 + " + num);
                Log.v("yw","布尔值提取 + " + bool);

            }
        });
    }
}