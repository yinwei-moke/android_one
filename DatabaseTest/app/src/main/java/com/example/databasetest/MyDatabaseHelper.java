package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    public static final String CREATE_BOOK = "CREATE TABLE BOOK ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "name text)";

    public static final String CATEGORY = "CREATE TABLE Category("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";


    private Context xContext;
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        xContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BOOK);
        db.execSQL(CATEGORY);
        Toast.makeText(xContext, "创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
        Toast.makeText(xContext, "升级数据库（创建新表）", Toast.LENGTH_SHORT).show();

    }
}
