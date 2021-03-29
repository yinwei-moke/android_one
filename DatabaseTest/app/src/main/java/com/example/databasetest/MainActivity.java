package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取SQLiteOpenHelper对象 这里使用MyDatabaseHelper 是因为继承自SQLiteOpenHelper
        //后面数字代表版本库 如果需要添加表或者删除表 需要改版本库
        db = new MyDatabaseHelper(this, "Bootbase", null, 5);
        Button baseButton = (Button) findViewById(R.id.base);
        baseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.getWritableDatabase();
            }
        });

        //添加测试
        Button AddButton = (Button) findViewById(R.id.addBase);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用SQLiteOpenHelper对象的getWritableDatabase 获取sqLiteDatabase进行添加数据操作
                SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("price", 16.96);
                sqLiteDatabase.insert("BOOK", null, values);
                //对比下面一条语句 表名可以不分大小写
                ContentValues values1 = new ContentValues();
                values1.put("name", "我是谁");
                values1.put("author", "皮卡丘");
                values1.put("price", 16.99);
                sqLiteDatabase.insert("Book", null, values1);
                Log.v("yw", "确定进入此方法");
            }
        });

        //修改测试
        Button updateButton = (Button) findViewById(R.id.updateBase);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.56);
                sqLiteDatabase.update("book", values, "name = ?", new String[]{"The Da Vinci Code"});
            }
        });

        //删除测试
        Button deleteButton = (Button) findViewById(R.id.deleteBase);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqlLiteDatabase = db.getWritableDatabase();
                sqlLiteDatabase.delete("Book", "price < ?", new String[]{"15"});

            }
        });

        //查询测试
        Button selectButton = (Button) findViewById(R.id.selectBase);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqlLiteDatabase = db.getWritableDatabase();
                //Cursor使用 查询时 保存所有表数据
                Cursor cursor = sqlLiteDatabase.query("BOOK", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("YW Base", "book name is " + name);
                        Log.d("YW Base", "book price is " + price);
                        Log.d("YW Base", "book author is " + author);

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        Button replaceButton = (Button) findViewById(R.id.replaceData);
        replaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
                sqLiteDatabase.beginTransaction();//开启事务
                try {
                    sqLiteDatabase.delete("BOOK", null, null);

                    ContentValues values = new ContentValues();
                    values.put("name", "Game of Thrones");
                    values.put("author", "George Martin");
                    values.put("price", 20.85);
                    sqLiteDatabase.insert("Book", null, values);
                    sqLiteDatabase.setTransactionSuccessful();//事务已经执行成功
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sqLiteDatabase.endTransaction();//最后结束事务
                }
            }
        });
    }
}