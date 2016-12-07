package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.R;
import com.bwie.ynf.bean.DetailedBean.DataBean.ActivityBean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Detailed_lv_view1_adapter extends BaseAdapter {
	private List<ActivityBean> list;
	private Context context;

	public Detailed_lv_view1_adapter(List<ActivityBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("static-access")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = convertView.inflate(context, R.layout.detailed_lv1_item, null);
			vh = new ViewHolder();
			vh.detailed_lvitem_text = (TextView) convertView.findViewById(R.id.detailed_lvitem_text);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.detailed_lvitem_text.setText(list.get(position).getTitle());
		return convertView;
	}

	class ViewHolder {
		TextView detailed_lvitem_text;
	}
}
