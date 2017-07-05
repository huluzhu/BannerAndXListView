package com.example.dell.bannerandxlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 胡计强 on 2017/06/29.
 */
public class MyMessage {
    private SQLiteDatabase db;
    private String table = "pre";

    public MyMessage(Context context) {
        MyHelper helper = new MyHelper(context);
        db = helper.getWritableDatabase();
    }

    //向数据库添加数据
    public void insert(List<XlistviewBean.ListBean> list) {
        try {
            db.beginTransaction();
            for (XlistviewBean.ListBean listBean : list) {
                ContentValues values = new ContentValues();
                values.put("title", listBean.getTitle());
                values.put("site", listBean.getDate());
                values.put("type", listBean.getType());
                values.put("image", "");
                db.insert(table, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    //从数据库查询
    public List<XlistviewBean.ListBean> query() {
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        List<XlistviewBean.ListBean> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            XlistviewBean.ListBean b = new XlistviewBean.ListBean();
            b.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            b.setDate(cursor.getString(cursor.getColumnIndex("site")));
            b.setType(cursor.getString(cursor.getColumnIndex("type")));

            list.add(b);
        }
        return list;
    }

    //清除数据库中的数据
    public void delete() {
        db.delete(table, null, null);
    }

}
