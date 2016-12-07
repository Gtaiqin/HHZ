package com.bwie.ynf.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

	public MyDatabase(Context context) {
		super(context, "mysqlite.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 执行有更改的sql语句
		db.execSQL(
				"create table goods (_id integer primary key autoincrement,goodid varchar(20), goodname varchar(20),price double,count integer,url varchar(20))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
