package com.hhzmy.adpater;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Guide_pagerAdapter extends PagerAdapter {
	
	private List<View> list;

	public Guide_pagerAdapter(List<View> list) {
		super();
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list.get(position));
		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));

	}
}
