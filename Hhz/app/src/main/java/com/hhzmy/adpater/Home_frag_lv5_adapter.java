package com.hhzmy.adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hhzmy.bean.Home_DataBean;
import com.hhzmy.hhz.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import static com.hhzmy.hhz.R.id.home_frag_lv5_item_img;

/**
 * Created by w9072 on 2016/11/14.
 */

public class Home_frag_lv5_adapter extends BaseAdapter {
    private List<Home_DataBean.DataBean> mDataBean;
    private Context context;
    private String ImageUrl = "http://image1.suning.cn";
    ImageLoader loader = ImageLoader.getInstance();


    public Home_frag_lv5_adapter(List<Home_DataBean.DataBean> mDataBean, Context context) {
        this.mDataBean = mDataBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDataBean.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            view = view.inflate(context, R.layout.home_frag_lv5_item, null);
            vh = new ViewHolder();
            vh.home_frag_lv5_item_img = (ImageView) view.findViewById(home_frag_lv5_item_img);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.home_frag_lv5_item_img.setScaleType(ImageView.ScaleType.FIT_XY);
        loader.init(ImageLoaderConfiguration.createDefault(context));
        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(0).getPicUrl(), vh.home_frag_lv5_item_img);
        return view;
    }


    class ViewHolder {
        ImageView home_frag_lv5_item_img;
    }
}
