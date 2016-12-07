package com.bwie.ynf;

import java.util.ArrayList;
import java.util.List;

import com.bwie.ynf.R;
import com.bwie.ynf.R.id;
import com.bwie.ynf.R.layout;
import com.bwie.ynf.R.menu;
import com.bwie.ynf.adapter.setting_lv_adapter;
import com.bwie.ynf.util.UpdateManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private Button back_login;
	private ListView setting_lv_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_settings);
		back_login = (Button) findViewById(R.id.back_login);
		setting_lv_view = (ListView) findViewById(R.id.setting_lv_view);
		back_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences.Editor editor = getSharedPreferences("User_data", MODE_PRIVATE).edit();
				editor.putString("user_icon_url", null);
				editor.putString("user_d3username", null);
				editor.commit();
				Toast.makeText(SettingsActivity.this, "已退出登陆", 0).show();
				finish();
			}
		});
		List<String> list = new ArrayList<String>();
		list.add("拨打电话联系客服");
		list.add("检查最新版本");
		setting_lv_view.setAdapter(new setting_lv_adapter(list, this));
		setting_lv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if (position == 0) {
					// 用intent启动拨打电话
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13021215516"));
					startActivity(intent);
				} else if (position == 1) {
					new UpdateManager(SettingsActivity.this).requestAPKData();
				}
			}
		});
	}

}
