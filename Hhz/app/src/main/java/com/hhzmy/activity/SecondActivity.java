package com.hhzmy.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hhzmy.adpater.Guide_pagerAdapter;
import com.hhzmy.hhz.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends FragmentActivity {

    private ViewPager guide_vp_pager;
    private List<View> list;
    private ImageView guide_pass_ib;
    private ImageView guide_start_ib;
    private RadioGroup guide_pager_radiogroup;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        //初始化控件ID
        guide_vp_pager = (ViewPager) findViewById(R.id.guide_vp_pager);
        guide_pass_ib = (ImageView) findViewById(R.id.guide_pass_Ib);
        guide_start_ib = (ImageView) findViewById(R.id.guide_start_Ib);
        guide_pager_radiogroup = (RadioGroup) findViewById(R.id.guide_pager_radiogroup);

        View view1 = LayoutInflater.from(this).inflate(R.layout.guide_1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.guide_2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.guide_3, null);
        list = new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        //设置viewpager
        settingpager();
        guide_pass_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        guide_start_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void settingpager() {
        // 为veiwpager添加适配器
        guide_vp_pager.setAdapter(new Guide_pagerAdapter(list));
        //添加指示点
        setdot();
        // viewpager滑动监听
        guide_vp_pager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int prostion) {
                if (prostion == 2) {
                    guide_pass_ib.setVisibility(View.GONE);
                    guide_start_ib.setVisibility(View.VISIBLE);
                } else {
                    guide_start_ib.setVisibility(View.GONE);
                    guide_pass_ib.setVisibility(View.VISIBLE);
                }
                guide_pager_radiogroup.check(prostion);
            }
        });
    }

    // 给viewpager设置小圆点
    private void setdot() {
        int wrop = RadioGroup.LayoutParams.WRAP_CONTENT;
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(wrop, wrop);
        params.leftMargin = 5;
        for (int i = 0; i < list.size(); i++) {
            RadioButton button = new RadioButton(SecondActivity.this);
            button.setEnabled(false);
            button.setId(i);
            button.setButtonDrawable(R.drawable.dot_selector);
            if (i == 0) {
                guide_pager_radiogroup.addView(button);
            } else {
                guide_pager_radiogroup.addView(button, params);
            }
        }
        guide_pager_radiogroup.check(0);

    }
}

