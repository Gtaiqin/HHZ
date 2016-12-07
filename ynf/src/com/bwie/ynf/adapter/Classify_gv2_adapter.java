package com.bwie.ynf.adapter;

import java.util.List;
import com.bwie.ynf.R;
import com.bwie.ynf.bean.ClassifyData.DataBean.GoodsBriefBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Classify_gv2_adapter extends BaseAdapter {
	private Context context;
	private List<GoodsBriefBean> goodsBriefBeans;

	public Classify_gv2_adapter(Context context,
			List<GoodsBriefBean> goodsBriefBeans) {
		super();
		this.context = context;
		this.goodsBriefBeans = goodsBriefBeans;
	}

	@Override
	public int getCount() {
		return goodsBriefBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return goodsBriefBeans.get(position);
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
			convertView = convertView.inflate(context,
					R.layout.classify_gv2_item, null);
			vh = new ViewHolder();
			vh.classify_gv2_item_img = (ImageView) convertView
					.findViewById(R.id.classify_gv2_item_img);
			vh.classify_gv2_item_goodname = (TextView) convertView
					.findViewById(R.id.classify_gv2_item_goodname);
			vh.classify_gv2_item_shop_price = (TextView) convertView
					.findViewById(R.id.classify_gv2_item_shop_price);
			vh.classify_gv2_item_market_price = (TextView) convertView
					.findViewById(R.id.classify_gv2_item_market_price);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(goodsBriefBeans.get(position).getGoods_img(),
				vh.classify_gv2_item_img);
		vh.classify_gv2_item_goodname.setText(goodsBriefBeans.get(position)
				.getGoods_name());
		vh.classify_gv2_item_shop_price.setText("гд"
				+ goodsBriefBeans.get(position).getShop_price());
		vh.classify_gv2_item_market_price.setText("гд"
				+ goodsBriefBeans.get(position).getMarket_price());
		vh.classify_gv2_item_market_price.getPaint().setFlags(
				Paint.STRIKE_THRU_TEXT_FLAG);
		return convertView;
	}

	class ViewHolder {
		ImageView classify_gv2_item_img;
		TextView classify_gv2_item_goodname, classify_gv2_item_shop_price,
				classify_gv2_item_market_price;
	}
}
