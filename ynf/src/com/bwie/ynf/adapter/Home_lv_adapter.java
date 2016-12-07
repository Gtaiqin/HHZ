package com.bwie.ynf.adapter;

import java.util.List;

import com.bwie.ynf.ProductdetailsActivity;
import com.bwie.ynf.R;
import com.bwie.ynf.bean.HomeData.DataBean.GroupBriefsBean;
import com.bwie.ynf.view.MyGridview;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Home_lv_adapter extends BaseAdapter {

	private List<GroupBriefsBean> groupbriefs;
	private Context context;
	private ImageLoader loader;

	public Home_lv_adapter(List<GroupBriefsBean> groupbriefs, Context context) {
		super();
		this.groupbriefs = groupbriefs;
		this.context = context;
	}

	@Override
	public int getCount() {
		return groupbriefs.size();
	}

	@Override
	public Object getItem(int position) {
		return groupbriefs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("static-access")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = convertView.inflate(context, R.layout.home_lv_item,
					null);
			vh = new ViewHolder();
			vh.prefecture_img = (ImageView) convertView
					.findViewById(R.id.prefecture_img);
			vh.home_gv_view = (MyGridview) convertView
					.findViewById(R.id.home_gv_view);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		loader = ImageLoader.getInstance();
		loader.displayImage(groupbriefs.get(position).getImage(),
				vh.prefecture_img);
		vh.home_gv_view.setAdapter(new Home_gridview_adapter(groupbriefs.get(
				position).getBriefList(), context));
		vh.home_gv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent=new Intent(context, ProductdetailsActivity.class);
				intent.putExtra("goodsid", groupbriefs.get(position).getBriefList().get(arg2).getId());
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	class ViewHolder {
		ImageView prefecture_img;
		com.bwie.ynf.view.MyGridview home_gv_view;
	}
}
