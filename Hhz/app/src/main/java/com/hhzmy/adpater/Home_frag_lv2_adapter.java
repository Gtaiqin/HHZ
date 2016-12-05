package com.hhzmy.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hhzmy.activity.DetailsActivity;
import com.hhzmy.hhz.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by w9072 on 2016/11/14.
 */

public class Home_frag_lv2_adapter extends BaseAdapter implements View.OnClickListener {
    private List<String> list;
    private Context context;
    ImageLoader loader = ImageLoader.getInstance();
    private String ImageUrl = "http://image1.suning.cn";


    public Home_frag_lv2_adapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
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
            view = view.inflate(context, R.layout.home_frag_lv2_item, null);
            vh = new ViewHolder();
            vh.home_frag_lv2_item_img = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img);
            vh.home_frag_lv2_item_img1 = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img1);
            vh.home_frag_lv2_item_img2 = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img2);
            vh.home_frag_lv2_item_img3 = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img3);
            vh.home_frag_lv2_item_img4 = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img4);
            vh.home_frag_lv2_item_img5 = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img5);
            vh.home_frag_lv2_item_img6 = (ImageView) view.findViewById(R.id.home_frag_lv2_item_img6);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.home_frag_lv2_item_img1.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.home_frag_lv2_item_img2.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.home_frag_lv2_item_img3.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.home_frag_lv2_item_img4.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.home_frag_lv2_item_img5.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.home_frag_lv2_item_img6.setScaleType(ImageView.ScaleType.FIT_XY);
        loader.init(ImageLoaderConfiguration.createDefault(context));
        loader.displayImage(ImageUrl + list.get(0), vh.home_frag_lv2_item_img);
        loader.displayImage(ImageUrl + list.get(1), vh.home_frag_lv2_item_img1);
        loader.displayImage(ImageUrl + list.get(2), vh.home_frag_lv2_item_img2);
        loader.displayImage(ImageUrl + list.get(3), vh.home_frag_lv2_item_img3);
        loader.displayImage(ImageUrl + list.get(4), vh.home_frag_lv2_item_img4);
        loader.displayImage(ImageUrl + list.get(5), vh.home_frag_lv2_item_img5);
        loader.displayImage(ImageUrl + list.get(6), vh.home_frag_lv2_item_img6);
        List<ImageView> img_list = new ArrayList<>();
        img_list.add(vh.home_frag_lv2_item_img1);
        img_list.add(vh.home_frag_lv2_item_img2);
        img_list.add(vh.home_frag_lv2_item_img3);
        img_list.add(vh.home_frag_lv2_item_img4);
        img_list.add(vh.home_frag_lv2_item_img5);
        img_list.add(vh.home_frag_lv2_item_img6);
        for (int a = 0; a < img_list.size(); a++) {
            img_list.get(a).setOnClickListener(this);
        }
        return view;
    }
    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.home_frag_lv2_item_img1:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv2_item_img2:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv2_item_img3:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv2_item_img4:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv2_item_img5:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
            case R.id.home_frag_lv2_item_img6:
                intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                break;
        }

    }
    class ViewHolder {
        ImageView home_frag_lv2_item_img,
                home_frag_lv2_item_img1,
                home_frag_lv2_item_img2,
                home_frag_lv2_item_img3,
                home_frag_lv2_item_img4,
                home_frag_lv2_item_img5,
                home_frag_lv2_item_img6;
    }
}
