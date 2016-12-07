package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.R;
import com.bwie.ynf.bean.MoreBean.DataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class More_gv_adapter extends BaseAdapter {
	private List<DataBean> list;
	private Context context;

	public More_gv_adapter(List<DataBean> list, Context context) {
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
			convertView = View.inflate(context, R.layout.more_gv_item, null);
			vh = new ViewHolder();
			vh.more_gv_item_img = (ImageView) convertView.findViewById(R.id.more_gv_item_img);
			vh.more_gv_item_goodname = (TextView) convertView.findViewById(R.id.more_gv_item_goodname);
			vh.more_gv_item_market_price = (TextView) convertView.findViewById(R.id.more_gv_item_market_price);
			vh.more_gv_item_shop_price = (TextView) convertView.findViewById(R.id.more_gv_item_shop_price);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(list.get(position).getGoods_img(), vh.more_gv_item_img);
		vh.more_gv_item_goodname.setText(list.get(position).getGoods_name() + "");
		vh.more_gv_item_shop_price.setText("гд" + list.get(position).getShop_price());
		vh.more_gv_item_market_price.setText("гд" + list.get(position).getMarket_price() + "");
		vh.more_gv_item_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return convertView;
	}

	class ViewHolder {
		TextView more_gv_item_goodname, more_gv_item_shop_price, more_gv_item_market_price;
		ImageView more_gv_item_img;
	}
}
