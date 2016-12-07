package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.R;
import com.bwie.ynf.bean.UrlDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class Gooddetails_frag1_lv_adapter extends BaseAdapter {
	private List<UrlDataBean> list;
	private Context context;

	public Gooddetails_frag1_lv_adapter(List<UrlDataBean> list, Context context) {
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
			convertView = convertView.inflate(context, R.layout.gooddetails_frag1_lv_item, null);
			vh = new ViewHolder();
			vh.gooddetails_frag1_imgview = (ImageView) convertView.findViewById(R.id.gooddetails_frag1_imgview);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(list.get(position).getUrl(), vh.gooddetails_frag1_imgview);
		vh.gooddetails_frag1_imgview.setScaleType(ScaleType.FIT_XY);
		return convertView;
	}

	class ViewHolder {
		ImageView gooddetails_frag1_imgview;
	}
}
