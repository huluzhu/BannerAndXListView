package com.example.dell.bannerandxlistview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 胡计强 on 2017/06/29.
 */
public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pre(id integer primary key autoincrement,title text,site text,type text,image text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
