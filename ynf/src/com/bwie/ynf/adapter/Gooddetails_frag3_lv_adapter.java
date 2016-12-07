package com.bwie.ynf.adapter;

import java.util.List;
import com.bwie.ynf.R;
import com.bwie.ynf.bean.DetailedBean.DataBean.CommentsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Gooddetails_frag3_lv_adapter extends BaseAdapter {
	private List<CommentsBean> list;
	private Context context;

	public Gooddetails_frag3_lv_adapter(List<CommentsBean> list, Context context) {
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
			convertView = convertView.inflate(context, R.layout.gooddetails_frag3_lv_item, null);
			vh = new ViewHolder();
			vh.use_user_username = (TextView) convertView.findViewById(R.id.use_user_username);
			vh.createtime = (TextView) convertView.findViewById(R.id.createtime);
			vh.comment_context = (TextView) convertView.findViewById(R.id.comment_context);
			vh.use_user_icon = (ImageView) convertView.findViewById(R.id.use_user_icon);
			vh.comment_img1 = (ImageView) convertView.findViewById(R.id.comment_img1);
			vh.comment_img3 = (ImageView) convertView.findViewById(R.id.comment_img2);
			vh.comment_img3 = (ImageView) convertView.findViewById(R.id.comment_img3);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		ImageLoader loader = ImageLoader.getInstance();
		loader.displayImage(list.get(position).getUser().getIcon(), vh.use_user_icon);
		vh.use_user_username.setText(list.get(position).getUser().getNick_name() + "");
		vh.createtime.setText(list.get(position).getCreatetime() + "");
		vh.comment_context.setText(list.get(position).getContent() + "");
		return convertView;
	}

	class ViewHolder {
		ImageView use_user_icon, comment_img1, comment_img2, comment_img3;
		TextView comment_context, use_user_username, createtime;
	}
}
