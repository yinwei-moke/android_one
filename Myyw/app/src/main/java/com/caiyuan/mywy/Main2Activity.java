package com.caiyuan.mywy;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("This is Main2Activity","This is Main2 TaskId "+getTaskId());
        setContentView(R.layout.activity_main2);

        Button button2 = (Button)findViewById(R.id.ywTwo);
        button2.setOnClickListener(this);
        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(intent);


    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.ywTwo:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Main2Activity.this);
                alertDialog.setTitle("This is 对话框");//对话框标题
                alertDialog.setMessage("信息介绍？");//显示信息
                alertDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                default:
                    break;
        }
    }
}
