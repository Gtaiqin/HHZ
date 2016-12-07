package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.R;
import com.bwie.ynf.bean.user_lv_column;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class User_lv_adapter extends BaseAdapter {
	private List<user_lv_column> list;
	private Context context;

	public User_lv_adapter() {
		super();
	}

	public User_lv_adapter(List<user_lv_column> list, Context context) {
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
		ViewHolder vh;
		if (convertView == null) {
			convertView = convertView.inflate(context, R.layout.user_list_item, null);
			vh = new ViewHolder();
			vh.user_lv_img = (ImageView) convertView.findViewById(R.id.user_lv_img);
			vh.tv_user_c = (TextView) convertView.findViewById(R.id.tv_user_c);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.user_lv_img.setImageResource(list.get(position).getImage_id());
		vh.tv_user_c.setText(list.get(position).getColumn_name());
		return convertView;
	}

	class ViewHolder {
		ImageView user_lv_img;
		TextView tv_user_c;
	}
}
