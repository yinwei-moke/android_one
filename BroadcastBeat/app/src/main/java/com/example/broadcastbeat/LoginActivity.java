package com.example.broadcastbeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);//获取SharedPreferences
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);//CheckBos对象
        Boolean isRemember = preferences.getBoolean("checkbox",false);//CheckBox复选框默认没被选中
        EditText account = (EditText)findViewById(R.id.accountEdit);//获取EditText id的值
        EditText password = (EditText)findViewById(R.id.passwordEdit);//获取EditText id的值
        Button login =(Button) findViewById(R.id.login);//获取按钮的对象
        checkBox.setChecked(true);//
        SharedPreferences.Editor editor = preferences.edit();//获取SharedPreferences.editor对象

        if(isRemember) {

            //将账号和密码都设置到文本框之中
            //先获取SharedPreferences对象的getString SharedPreferences对象在内存里面开辟一块空间 然后给EditText指定值这就保存下来了
            account.setText(preferences.getString("account", ""));
            password.setText(preferences.getString("password", ""));
            checkBox.setChecked(true);

        }
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String at = account.getText().toString();
                    String pw = password.getText().toString();
                    if(at.equals("admin") && pw.equals("123456")){
                        if(checkBox.isChecked()){//检查复选框是否被选中
                            editor.putBoolean("checkbox",true);
                            editor.putString("account", at);
                            editor.putString("password", pw);
                        }else{
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();//销毁的目的是让他back回去之后不会默认选中（记住密码）单选框
                    }else{
                        Toast.makeText(LoginActivity.this, "密码或者用户名输入错误", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}