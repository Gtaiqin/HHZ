package com.bwie.ynf.adapter;

import java.util.List;
import com.bwie.ynf.R;
import com.bwie.ynf.bean.DetailedBean.DataBean.GoodsBean.AttributesBean;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Gooddetails_frag2_lv_adapter extends BaseAdapter {
	private List<AttributesBean> list;
	private Context context;

	public Gooddetails_frag2_lv_adapter(List<AttributesBean> list, Context context) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = convertView.inflate(context, R.layout.gooddetails_frag2_lv_item, null);
			vh = new ViewHolder();
			vh.attr_name = (TextView) convertView.findViewById(R.id.attr_name);
			vh.attr_value = (TextView) convertView.findViewById(R.id.attr_value);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.attr_name.setText(list.get(position).getAttr_name() + "");
		vh.attr_value.setText(list.get(position).getAttr_value() + "");
		return convertView;
	}

	class ViewHolder {
		TextView attr_name, attr_value;
	}
}
