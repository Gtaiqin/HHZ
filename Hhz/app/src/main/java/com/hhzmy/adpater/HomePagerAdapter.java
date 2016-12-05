package com.hhzmy.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.activity.WebActivity;

import java.util.List;

public class HomePagerAdapter extends PagerAdapter {
    private List<ImageView> listimg;
    private List<String> pagerurl;
    private Context context;

    public HomePagerAdapter(List<ImageView> listimg, List<String> pagerurl, Context context) {
        this.listimg = listimg;
        this.pagerurl = pagerurl;
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = listimg.get(position % listimg.size());
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", pagerurl.get(position % listimg.size()));
                context.startActivity(intent);
            }
        });
        return imageView;

       /* View view = listimg.get(position % 3);
        container.addView(view);
        return view;*/
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
