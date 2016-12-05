package com.hhzmy.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hhzmy.activity.DetailsActivity;
import com.hhzmy.bean.Home_DataBean;
import com.hhzmy.hhz.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.hhzmy.hhz.R.id.home_frag_lv4_item_img;

/**
 * Created by w9072 on 2016/11/14.
 */

public class Home_frag_lv4_adapter extends BaseAdapter implements View.OnClickListener {
    private List<Home_DataBean.DataBean.TagBean> mTagBean;
    private List<Home_DataBean.DataBean> mDataBean;
    private Context context;
    private String ImageUrl = "http://image1.suning.cn";
    ImageLoader loader = ImageLoader.getInstance();


    public Home_frag_lv4_adapter(List<Home_DataBean.DataBean.TagBean> mTagBean, List<Home_DataBean.DataBean> mDataBean, Context context) {
        this.mTagBean = mTagBean;
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
            view = view.inflate(context, R.layout.home_frag_lv4_item, null);
            vh = new ViewHolder();
            vh.home_frag_lv4_item_img = (ImageView) view.findViewById(home_frag_lv4_item_img);
            vh.home_frag_lv4_item_img1 = (ImageView) view.findViewById(R.id.home_frag_lv4_item_img1);
            vh.home_frag_lv4_item_img2 = (ImageView) view.findViewById(R.id.home_frag_lv4_item_img2);
            vh.home_frag_lv4_item_img3 = (ImageView) view.findViewById(R.id.home_frag_lv4_item_img3);
            vh.home_frag_lv4_item_img4 = (ImageView) view.findViewById(R.id.home_frag_lv4_item_img4);
            vh.home_frag_lv4_item_img5 = (ImageView) view.findViewById(R.id.home_frag_lv4_item_img5);
            vh.home_frag_lv4_item_img6 = (ImageView) view.findViewById(R.id.home_frag_lv4_item_img6);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.home_frag_lv4_item_img.setScaleType(ImageView.ScaleType.FIT_XY);
        loader.init(ImageLoaderConfiguration.createDefault(context));
        loader.displayImage(ImageUrl + mTagBean.get(i).getPicUrl(), vh.home_frag_lv4_item_img);

        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(0).getPicUrl(), vh.home_frag_lv4_item_img1);
        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(1).getPicUrl(), vh.home_frag_lv4_item_img2);
        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(2).getPicUrl(), vh.home_frag_lv4_item_img3);
        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(3).getPicUrl(), vh.home_frag_lv4_item_img4);
        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(4).getPicUrl(), vh.home_frag_lv4_item_img5);
        loader.displayImage(ImageUrl + mDataBean.get(i).getTag().get(5).getPicUrl(), vh.home_frag_lv4_item_img6);
        List<ImageView> img_list = new ArrayList<>();
        img_list.add(vh.home_frag_lv4_item_img1);
        img_list.add(vh.home_frag_lv4_item_img2);
        img_list.add(vh.home_frag_lv4_item_img3);
        img_list.add(vh.home_frag_lv4_item_img4);
        img_list.add(vh.home_frag_lv4_item_img5);
        img_list.add(vh.home_frag_lv4_item_img6);
        for (int a = 0; a < img_list.size(); a++) {
            img_list.get(a).setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.home_frag_lv4_item_img1:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv4_item_img2:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv4_item_img3:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv4_item_img4:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv4_item_img5:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv4_item_img6:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
        }

    }

    class ViewHolder {
        ImageView home_frag_lv4_item_img,
                home_frag_lv4_item_img1,
                home_frag_lv4_item_img2,
                home_frag_lv4_item_img3,
                home_frag_lv4_item_img4,
                home_frag_lv4_item_img5,
                home_frag_lv4_item_img6;
    }
}
