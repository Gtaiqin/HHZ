package com.hhzmy.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.bean.Home_DataBean;
import com.hhzmy.hhz.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by w9072 on 2016/11/14.
 */

public class Home_frag_gv1_adapter extends BaseAdapter {
    private List<Home_DataBean.DataBean.TagBean> list;
    private Context context;
    ImageLoader loader = ImageLoader.getInstance();
    private String ImageUrl = "http://image1.suning.cn";


    public Home_frag_gv1_adapter(List<Home_DataBean.DataBean.TagBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            view = view.inflate(context, R.layout.home_frag_gv1_item, null);
            vh = new ViewHolder();
            vh.home_frag_gv1_item_img = (ImageView) view.findViewById(R.id.home_frag_gv1_item_img);
            vh.home_frag_gv1_item_tv = (TextView) view.findViewById(R.id.home_frag_gv1_item_tv);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        loader.init(ImageLoaderConfiguration.createDefault(context));
        loader.displayImage(ImageUrl + list.get(i).getPicUrl(), vh.home_frag_gv1_item_img);
        vh.home_frag_gv1_item_tv.setText(list.get(i).getElementName());
        return view;
    }

    class ViewHolder {
        ImageView home_frag_gv1_item_img;
        TextView home_frag_gv1_item_tv;
    }
}
