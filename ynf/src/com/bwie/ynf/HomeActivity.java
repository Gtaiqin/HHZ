package com.bwie.ynf;

import com.bwie.ynf.R;
import com.bwie.ynf.frag.classify_frag;
import com.bwie.ynf.frag.home_frag;
import com.bwie.ynf.frag.shopping_frag;
import com.bwie.ynf.frag.user_frag;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {
	// 退出时的时间
	private long mExitTime;
	private home_frag home;
	private classify_frag classify;
	private shopping_frag shop;
	private user_frag user;
	private RadioGroup tab_menu;
	@SuppressWarnings("unused")
	private RadioButton tab_home;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private NetworkInfo networkInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		networkInfo = connectivityManager.getActiveNetworkInfo();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		info();
		// 设置fragment
		home = new home_frag();
		classify = new classify_frag();
		manager = getSupportFragmentManager();
		shop = new shopping_frag();
		user = new user_frag();
		transaction = manager.beginTransaction();
		FragmentTransaction trnTransaction = getSupportFragmentManager().beginTransaction();
		trnTransaction.add(R.id.main_content, home, "home");
		trnTransaction.add(R.id.main_content, classify, "classify");
		trnTransaction.add(R.id.main_content, shop, "shop");
		trnTransaction.add(R.id.main_content, user, "user");
		switchTag("home");
		trnTransaction.commit();
		// 监听切换fragment
		initView();
		if (networkInfo == null || !networkInfo.isAvailable()) {
			Toast.makeText(getApplicationContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "网络可用", Toast.LENGTH_SHORT).show();
		}

	}

	// 获取控件
	private void info() {
		tab_home = (RadioButton) findViewById(R.id.tab_home);
	}

	public void initView() {
		tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
		tab_menu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.tab_home:
					switchTag("home");
					break;
				case R.id.tab_classify:
					switchTag("classify");
					break;
				case R.id.tab_shop:
					switchTag("shop");
					break;
				case R.id.tab_user:
					switchTag("user");
					break;
				default:
					break;
				}

			}
		});
	}

	private void switchTag(String str) {
		FragmentTransaction transaction = manager.beginTransaction();
		if (str.equals("home")) {
			transaction.show(home);
			transaction.hide(classify);
			transaction.hide(shop);
			transaction.hide(user);
		} else if (str.equals("classify")) {
			transaction.show(classify);
			transaction.hide(home);
			transaction.hide(shop);
			transaction.hide(user);
		} else if (str.equals("shop")) {
			transaction.show(shop);
			transaction.hide(classify);
			transaction.hide(home);
			transaction.hide(user);
		} else if (str.equals("user")) {
			transaction.show(user);
			transaction.hide(classify);
			transaction.hide(shop);
			transaction.hide(home);
		}
		transaction.commit();
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
			Toast.makeText(HomeActivity.this, "再按一次退出应用", 0).show();
			mExitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}
}
