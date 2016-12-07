package com.bwie.ynf;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bwie.ynf.R;
import com.bwie.ynf.adapter.Guide_pagerAdapter;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SecondActivity extends FragmentActivity {

	private ViewPager vp_pager;
	private List<View> list;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_second);
		vp_pager = (ViewPager) findViewById(R.id.vp_pager);

		View view1 = LayoutInflater.from(this).inflate(R.layout.guide_1, null);
		View view2 = LayoutInflater.from(this).inflate(R.layout.guide_2, null);
		View view3 = LayoutInflater.from(this).inflate(R.layout.guide_3, null);
		View view4 = LayoutInflater.from(this).inflate(R.layout.guide_4, null);
		View view5 = LayoutInflater.from(this).inflate(R.layout.guide_5, null);
		list = new ArrayList<View>();
		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);
		list.add(view5);
		settingpager();
	}

	public void settingpager() {
		// ÃÌº”veiwpager  ≈‰∆˜
		vp_pager.setAdapter(new Guide_pagerAdapter(list));
		// Œ™viewpager…Ë÷√ª¨∂Øº‡Ã˝
		vp_pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageSelected(int prostion) {
				Log.e("TAG", "" + prostion);
				if (prostion == 4) {
					Timer timer = new Timer();
					TimerTask task = new TimerTask() {

						@Override
						public void run() {
							Intent intent = new Intent(SecondActivity.this,
									HomeActivity.class);
							startActivity(intent);
							finish();
						}
					};
					timer.schedule(task, 3000);
				}
			}

		});
	}
}
