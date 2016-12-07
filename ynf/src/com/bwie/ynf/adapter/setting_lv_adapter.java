package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class setting_lv_adapter extends BaseAdapter {
	private List<String> list;
	private Context context;
	private TextView view;

	public setting_lv_adapter(List<String> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = convertView.inflate(context, R.layout.setting_lv_item, null);
		view = (TextView) convertView.findViewById(R.id.setting_text);
		view.setText(list.get(position));
		return convertView;
	}

}
