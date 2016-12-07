package com.bwie.ynf.util;

import java.util.ArrayList;
import java.util.List;

import com.bwie.ynf.bean.Goods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbUtils {

	private MyDatabase db;

	public DbUtils(Context context) {
		db = new MyDatabase(context);
	}

	// 添加数据
	public boolean add(Goods goods) {
		SQLiteDatabase database = db.getWritableDatabase();
		ContentValues values = new ContentValues();// 是用map封装的对象，用来存放值
		values.put("goodid", goods.getGoodid());
		values.put("goodname", goods.getGoodname());
		values.put("price", goods.getPrice());
		values.put("count", goods.getCount());
		values.put("url", goods.getUrl());
		// table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 ,
		// 返回值：代表添加这个新行的Id ，-1代表添加失败
		long result = database.insert("goods", null, values);// 底层是在拼装sql语句
		// 关闭数据库对象
		database.close();
		if (result != -1) {// -1代表添加失败
			return true;
		} else {
			return false;
		}
	}

	// 删除数据
	public int delete(String goodid) {
		// 执行sql语句需要sqliteDatabase对象
		// 调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase database = db.getWritableDatabase();
		// table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
		int result = database.delete("goods", "goodid = ?", new String[] { goodid });
		// 关闭数据库对象
		database.close();
		return result;
	}

	// 修改数据
	public int update(String goodid, int count) {

		// 执行sql语句需要sqliteDatabase对象
		// 调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase database = db.getWritableDatabase();
		ContentValues values = new ContentValues();// 是用map封装的对象，用来存放值
		values.put("count", count + 1);
		// table:表名, values：更新的值, whereClause:更新的条件,
		// whereArgs：更新条件的占位符的值,返回值：成功修改多少行
		int result = database.update("goods", values, "goodid = ?", new String[] { goodid });
		// 关闭数据库对象
		db.close();
		return result;
	}

	// 读取数据
	public List<Goods> read() {

		// 执行sql语句需要sqliteDatabase对象
		// 调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase database = db.getReadableDatabase();

		// table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件,
		// selectionArgs：条件占位符的参数值,
		// groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
		Cursor cursor = database.query("goods", null, null, null, null, null, null);
		// 解析Cursor中的数据
		List<Goods> list = new ArrayList<Goods>();
		if (cursor != null && cursor.getCount() > 0) {// 判断cursor中是否存在数据

			// 循环遍历结果集，获取每一行的内容
			while (cursor.moveToNext()) {// 条件，游标能否定位到下一行
				Goods goods = new Goods();
				// 获取数据
				String goodid = cursor.getString(cursor.getColumnIndex("goodid"));
				String goodname = cursor.getString(cursor.getColumnIndex("goodname"));
				double price = cursor.getDouble(cursor.getColumnIndex("price"));
				int count = cursor.getInt(cursor.getColumnIndex("count"));
				String url = cursor.getString(cursor.getColumnIndex("url"));

				goods.setGoodid(goodid);
				goods.setGoodname(goodname);
				goods.setPrice(price);
				goods.setCount(count);
				goods.setUrl(url);
				list.add(goods);
			}
			cursor.close();// 关闭结果集

		}
		// 关闭数据库对象
		database.close();
		return list;
	}

	// 读取数据
	public int idread(String id) {

		// 执行sql语句需要sqliteDatabase对象
		// 调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase database = db.getReadableDatabase();
		// table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件,
		// selectionArgs：条件占位符的参数值,
		// groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
		Cursor cursor = database.rawQuery("select count from goods where goodid=?", new String[] { id });
		int count = 0;
		// 解析Cursor中的数据
		List<Goods> list = new ArrayList<Goods>();
		if (cursor != null && cursor.getCount() > 0) {// 判断cursor中是否存在数据

			// 循环遍历结果集，获取每一行的内容
			while (cursor.moveToNext()) {// 条件，游标能否定位到下一行
				count = cursor.getInt(cursor.getColumnIndex("count"));
			}
			cursor.close();// 关闭结果集

		}
		// 关闭数据库对象
		database.close();
		return count;
	}
}
