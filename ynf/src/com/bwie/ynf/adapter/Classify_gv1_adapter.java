package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class Classify_gv1_adapter extends BaseAdapter {
	private Context context;
	private List<String> itemlist;

	public Classify_gv1_adapter(Context context, List<String> itemlist) {
		super();
		this.context = context;
		this.itemlist = itemlist;
	}

	@Override
	public int getCount() {
		return itemlist.size();
	}

	@Override
	public Object getItem(int position) {
		return itemlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoler vh = null;
		if (convertView == null) {
			convertView = View.inflate(this.context,
					R.layout.classify_gv1_item, null);
			vh = new ViewHoler();
			vh.button = ((Button) convertView.findViewById(R.id.classify_gv1_button));
			convertView.setTag(vh);
		} else {
			vh = (ViewHoler) convertView.getTag();
		}
		vh.button.setText(itemlist.get(position).toString()+"");
		return convertView;
	}
	class ViewHoler {
		Button button;
	}

}
