package com.bwie.ynf.adapter;

import java.util.List;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomePagerAdapter extends PagerAdapter {
	private List<ImageView> listimg;

	public HomePagerAdapter() {
		super();
	}

	public HomePagerAdapter(List<ImageView> listimg) {
		super();
		this.listimg = listimg;
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
	public Object instantiateItem(ViewGroup container, int position) {
		View view = listimg.get(position % listimg.size());
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}
