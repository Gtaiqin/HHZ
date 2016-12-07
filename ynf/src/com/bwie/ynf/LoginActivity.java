package com.bwie.ynf;

import java.util.HashMap;
import com.bwie.ynf.R;
import com.bwie.ynf.frag.Login_frag_one;
import com.bwie.ynf.frag.Login_frag_two;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView.ScaleType;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends FragmentActivity implements PlatformActionListener {

	private RadioGroup login_menu;
	private RadioButton login_menu2;
	private RadioButton login_menu1;
	private FragmentManager manager;
	@SuppressWarnings("unused")
	private FragmentTransaction transaction;
	private Login_frag_one login_fragone;
	private Login_frag_two login_fragtwo;
	private FragmentTransaction trnTransaction;
	private ImageLoader loader;
	private ImageView first_shop;
	private ImageView login_back;
	private ImageView login_qq;
	private String userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		infoview();
		ShareSDK.initSDK(this);
		// 创建fragment 以及展示
		manager = getSupportFragmentManager();
		transaction = manager.beginTransaction();
		trnTransaction = getSupportFragmentManager().beginTransaction();
		login_fragone = new Login_frag_one();
		login_fragtwo = new Login_frag_two();
		trnTransaction.add(R.id.frame_login, login_fragone, "login_fragone");
		trnTransaction.add(R.id.frame_login, login_fragtwo, "login_fragtwo");
		switchTag("login_fragone");
		trnTransaction.commit();
		// 首单立减20图片
		loader = ImageLoader.getInstance();
		first_shop.setScaleType(ScaleType.FIT_XY);
		loader.displayImage("http://image.hmeili.com/yunifang/images/goods/ad1//1503311533748344878180.png",
				first_shop);
		onclick();

	}

	// 切换fragment
	private void switchTag(String str) {
		FragmentTransaction transaction = manager.beginTransaction();
		if (str.equals("login_fragone")) {
			transaction.show(login_fragone);
			transaction.hide(login_fragtwo);
		} else if (str.equals("login_fragtwo")) {
			transaction.show(login_fragtwo);
			transaction.hide(login_fragone);
		}
		transaction.commit();
	}

	// 得到控件
	private void infoview() {
		login_menu = (RadioGroup) findViewById(R.id.login_menu);
		login_menu1 = (RadioButton) findViewById(R.id.login_menu1);
		login_menu2 = (RadioButton) findViewById(R.id.login_menu2);
		first_shop = (ImageView) findViewById(R.id.first_shop);
		login_back = (ImageView) findViewById(R.id.login_back);
		login_qq = (ImageView) findViewById(R.id.login_qq);
	}

	// 监听事件
	private void onclick() {
		login_menu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.login_menu1:
					login_menu1.setBackgroundColor(Color.WHITE);
					login_menu2.getBackground().setAlpha(0);
					switchTag("login_fragone");
					break;
				case R.id.login_menu2:
					login_menu1.getBackground().setAlpha(0);
					login_menu2.setBackgroundColor(Color.WHITE);
					switchTag("login_fragtwo");
					break;
				default:
					break;
				}
			}
		});
		login_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// 第三方QQ登陆
		login_qq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Platform qq = ShareSDK.getPlatform(LoginActivity.this, QQ.NAME);
				qq.setPlatformActionListener(LoginActivity.this);
				qq.authorize();// 单独授权
				qq.showUser(null);// 授权并获取用户信息
			}
		});
	}

	@Override
	public void onCancel(Platform arg0, int arg1) {

	}

	@Override
	public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
		// 通过打印res数据看看有哪些数据是你想要的
		if (action == Platform.ACTION_USER_INFOR) {
			PlatformDb platDB = platform.getDb();// 获取数平台数据DB
			// 通过DB获取各种数据
			platDB.getToken();
			platDB.getUserGender();
			String icon = platDB.getUserIcon();
			platDB.getUserId();
			userName = platDB.getUserName();
			// System.out.print(""+userName);
			SharedPreferences.Editor editor = getSharedPreferences("User_data", MODE_PRIVATE).edit();
			editor.putString("user_icon_url", icon);
			editor.putString("user_d3username", userName);
			editor.commit();
			finish();
			resterUsername();
		}
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {

	}

	public String resterUsername() {
		finish();
		return userName;
	}
}
