package com.hhzmy.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hhzmy.frag.classify_frag;
import com.hhzmy.frag.home_frag;
import com.hhzmy.frag.shopcar_frag;
import com.hhzmy.frag.user_frag;
import com.hhzmy.hhz.R;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity {
    // 退出时的时间
    private long mExitTime;
    private RadioGroup tab_menu;
    private FragmentTransaction mTransaction;
    private home_frag home;
    private classify_frag classify;
    private shopcar_frag shop;
    private user_frag user;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //实例化fragment对象并添加集合
        initData();
        //默认打开首页页面
        replace_frag(0);
        //选项卡切换fragment
        setfragment();

    }

    //实例化fragment对象并添加集合
    private void initData() {
        list = new ArrayList<Fragment>();
        list.add(new home_frag());
        list.add(new classify_frag());
        list.add(new shopcar_frag());
        list.add(new user_frag());
    }

    //点击选项卡替换页面
    public void setfragment() {
        tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
        tab_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.tab_home:
                        replace_frag(0);
                        break;
                    case R.id.tab_classify:
                        replace_frag(1);
                        break;
                    case R.id.tab_shop:
                        replace_frag(2);
                        break;
                    case R.id.tab_user:
                        replace_frag(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //执行替换
    public void replace_frag(int possion) {
        //多个界面切换需要每次执行先beginTransaction()之后在replace，最后提交
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.main_content, list.get(possion));
        mTransaction.commit();
        home_frag hf = new home_frag();
        Bundle bundle = new Bundle();
        bundle.putString("aa", "aa");
        hf.setArguments(bundle);
    }

    // 对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("ShowToast")
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(HomeActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
