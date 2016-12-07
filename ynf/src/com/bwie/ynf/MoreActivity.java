package com.bwie.ynf;

import java.util.List;

import com.bwie.ynf.adapter.More_gv_adapter;
import com.bwie.ynf.bean.HomeData;
import com.bwie.ynf.bean.HomeData.DataBean;
import com.bwie.ynf.bean.MoreBean;
import com.bwie.ynf.util.HttpUtil;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MoreActivity extends Activity {

	private GridView more_gv_view;
	private NetworkInfo networkInfo;
	private MoreBean moreBean;
	private List<com.bwie.ynf.bean.MoreBean.DataBean> dataBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		networkInfo = connectivityManager.getActiveNetworkInfo();
		setContentView(R.layout.activity_more);
		more_gv_view = (GridView) findViewById(R.id.more_gv_view);
		ifInternet();
		more_gv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent(MoreActivity.this, ProductdetailsActivity.class);
				intent.putExtra("goodsid", dataBean.get(position).getId());
				startActivity(intent);
			}
		});
	}

	// 判断网络状态加载数据
	private void ifInternet() {
		if (networkInfo == null || !networkInfo.isAvailable()) {
			Toast.makeText(getApplicationContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
			SharedPreferences preferences = getSharedPreferences("moredata", MODE_PRIVATE);
			String moredata = preferences.getString("moredata", "");
			if (moredata == null) {
				Toast.makeText(this, "请检查网络", 0).show();
			} else {
				Gson gson = new Gson();
				moreBean = gson.fromJson(moredata, MoreBean.class);
				dataBean = moreBean.getData();
				more_gv_view.setAdapter(new More_gv_adapter(dataBean, MoreActivity.this));
			}
		} else {
			Toast.makeText(getApplicationContext(), "网络可用", Toast.LENGTH_SHORT).show();
			Internetgetdata();
		}
	}

	// 联网请求数据
	public void Internetgetdata() {
		new AsyncTask<String, Void, Void>() {

			@Override
			protected Void doInBackground(String... params) {
				HttpUtil util = new HttpUtil();
				try {
					@SuppressWarnings("static-access")
					String json = util.getjson(params[0]);
					SharedPreferences.Editor editor = getSharedPreferences("moredata", MODE_PRIVATE).edit();
					editor.putString("moredata", json);
					editor.commit();
					Gson gson = new Gson();
					moreBean = gson.fromJson(json, MoreBean.class);
					dataBean = moreBean.getData();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				more_gv_view.setAdapter(new More_gv_adapter(dataBean, MoreActivity.this));
			}
		}.execute(
				"http://m.yunifang.com/yunifang/mobile/goods/getall?random=87749&encode=ac6bd45b8f50b626a6843b294af8fed5");
	}
}
