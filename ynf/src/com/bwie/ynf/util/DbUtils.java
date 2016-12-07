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

	// �������
	public boolean add(Goods goods) {
		SQLiteDatabase database = db.getWritableDatabase();
		ContentValues values = new ContentValues();// ����map��װ�Ķ����������ֵ
		values.put("goodid", goods.getGoodid());
		values.put("goodname", goods.getGoodname());
		values.put("price", goods.getPrice());
		values.put("count", goods.getCount());
		values.put("url", goods.getUrl());
		// table: ���� , nullColumnHack������Ϊ�գ���ʾ���һ������, values:����һ�е�ֵ ,
		// ����ֵ���������������е�Id ��-1�������ʧ��
		long result = database.insert("goods", null, values);// �ײ�����ƴװsql���
		// �ر����ݿ����
		database.close();
		if (result != -1) {// -1�������ʧ��
			return true;
		} else {
			return false;
		}
	}

	// ɾ������
	public int delete(String goodid) {
		// ִ��sql�����ҪsqliteDatabase����
		// ����getReadableDatabase����,����ʼ�����ݿ�Ĵ���
		SQLiteDatabase database = db.getWritableDatabase();
		// table ������, whereClause: ɾ������, whereArgs��������ռλ���Ĳ��� ; ����ֵ���ɹ�ɾ��������
		int result = database.delete("goods", "goodid = ?", new String[] { goodid });
		// �ر����ݿ����
		database.close();
		return result;
	}

	// �޸�����
	public int update(String goodid, int count) {

		// ִ��sql�����ҪsqliteDatabase����
		// ����getReadableDatabase����,����ʼ�����ݿ�Ĵ���
		SQLiteDatabase database = db.getWritableDatabase();
		ContentValues values = new ContentValues();// ����map��װ�Ķ����������ֵ
		values.put("count", count + 1);
		// table:����, values�����µ�ֵ, whereClause:���µ�����,
		// whereArgs������������ռλ����ֵ,����ֵ���ɹ��޸Ķ�����
		int result = database.update("goods", values, "goodid = ?", new String[] { goodid });
		// �ر����ݿ����
		db.close();
		return result;
	}

	// ��ȡ����
	public List<Goods> read() {

		// ִ��sql�����ҪsqliteDatabase����
		// ����getReadableDatabase����,����ʼ�����ݿ�Ĵ���
		SQLiteDatabase database = db.getReadableDatabase();

		// table:����, columns����ѯ������,���null�����ѯ�����У� selection:��ѯ����,
		// selectionArgs������ռλ���Ĳ���ֵ,
		// groupBy:��ʲô�ֶη���, having:���������, orderBy:��ʲô�ֶ�����
		Cursor cursor = database.query("goods", null, null, null, null, null, null);
		// ����Cursor�е�����
		List<Goods> list = new ArrayList<Goods>();
		if (cursor != null && cursor.getCount() > 0) {// �ж�cursor���Ƿ��������

			// ѭ���������������ȡÿһ�е�����
			while (cursor.moveToNext()) {// �������α��ܷ�λ����һ��
				Goods goods = new Goods();
				// ��ȡ����
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
			cursor.close();// �رս����

		}
		// �ر����ݿ����
		database.close();
		return list;
	}

	// ��ȡ����
	public int idread(String id) {

		// ִ��sql�����ҪsqliteDatabase����
		// ����getReadableDatabase����,����ʼ�����ݿ�Ĵ���
		SQLiteDatabase database = db.getReadableDatabase();
		// table:����, columns����ѯ������,���null�����ѯ�����У� selection:��ѯ����,
		// selectionArgs������ռλ���Ĳ���ֵ,
		// groupBy:��ʲô�ֶη���, having:���������, orderBy:��ʲô�ֶ�����
		Cursor cursor = database.rawQuery("select count from goods where goodid=?", new String[] { id });
		int count = 0;
		// ����Cursor�е�����
		List<Goods> list = new ArrayList<Goods>();
		if (cursor != null && cursor.getCount() > 0) {// �ж�cursor���Ƿ��������

			// ѭ���������������ȡÿһ�е�����
			while (cursor.moveToNext()) {// �������α��ܷ�λ����һ��
				count = cursor.getInt(cursor.getColumnIndex("count"));
			}
			cursor.close();// �رս����

		}
		// �ر����ݿ����
		database.close();
		return count;
	}
}
