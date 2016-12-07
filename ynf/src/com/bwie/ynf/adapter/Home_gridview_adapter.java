package com.bwie.ynf.adapter;

import java.util.List;
import com.bwie.ynf.R;
import com.bwie.ynf.bean.HomeData.DataBean.GroupBriefsBean.BriefListBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Home_gridview_adapter extends BaseAdapter {

	private List<BriefListBean> briefListBeans;
	private Context context;
	private ImageLoader loader;

	public Home_gridview_adapter(List<BriefListBean> briefListBeans,
			Context context) {
		super();
		this.briefListBeans = briefListBeans;
		this.context = context;
	}

	@Override
	public int getCount() {
		return briefListBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return briefListBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	//
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.home_gv_item, null);
			vh = new ViewHolder();
			vh.home_gv_item_img = (ImageView) convertView
					.findViewById(R.id.home_gv_item_img);
			vh.home_gv_item_goodname = (TextView) convertView
					.findViewById(R.id.home_gv_item_goodname);
			vh.home_gv_item_market_price = (TextView) convertView
					.findViewById(R.id.home_gv_item_market_price);
			vh.home_gv_item_shop_price = (TextView) convertView
					.findViewById(R.id.home_gv_item_shop_price);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		loader = ImageLoader.getInstance();
		loader.displayImage(briefListBeans.get(position).getGoods_img(),
				vh.home_gv_item_img);
		vh.home_gv_item_goodname.setText(briefListBeans.get(position)
				.getGoods_name() + "");
		vh.home_gv_item_shop_price.setText("гд"
				+ briefListBeans.get(position).getShop_price());
		vh.home_gv_item_market_price.setText("гд"
				+ briefListBeans.get(position).getMarket_price() + "");
		vh.home_gv_item_market_price.getPaint().setFlags(
				Paint.STRIKE_THRU_TEXT_FLAG);
		return convertView;
	}

	class ViewHolder {
		TextView home_gv_item_goodname, home_gv_item_shop_price,
				home_gv_item_market_price;
		ImageView home_gv_item_img;
	}
}
