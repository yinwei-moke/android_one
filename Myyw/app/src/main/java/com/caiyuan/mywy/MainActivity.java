package com.caiyuan.mywy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Additem:
                Toast.makeText(this, "You chicked Add", Toast.LENGTH_SHORT).show();
            case R.id.Removeitem:
                Toast.makeText(this, "You chicked remove", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("This is MainActivity","This is Main TaskId "+getTaskId());
        setContentView(R.layout.frist_layout);

        Button button1 = (Button)findViewById(R.id.ywOne);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //!Toast使用 (参数1要求的CONTEXT对象，也就是Toast的上下文)，（参数二文本内容），（参数三Toast显示时长）
                //Toast.makeText(MainActivity.this, "You chicked Button 1", Toast.LENGTH_SHORT).show();

//                跳转页面 显示
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

                //跳转页面 隐式 ( action名字很重要，注意别看错了)
                //Intent intent1 = new Intent("com.caiyuan.mywy.ACTION_START");
                //startActivity(intent1);

//                Intent intent1 = new Intent("Intent.ACTION_VIEW");
//                intent1.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent1);

            }
        });

//        Log.v("data","中文测试");
//        String str = R.string.new_yw;

    }
}
