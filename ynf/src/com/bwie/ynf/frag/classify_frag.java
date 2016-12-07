package com.bwie.ynf.frag;

import java.util.ArrayList;
import java.util.List;

import com.bwie.ynf.ProductdetailsActivity;
import com.bwie.ynf.R;
import com.bwie.ynf.adapter.Classify_gv1_adapter;
import com.bwie.ynf.adapter.Classify_gv2_adapter;
import com.bwie.ynf.bean.ClassifyData;
import com.bwie.ynf.bean.ClassifyData.DataBean;
import com.bwie.ynf.bean.ClassifyData.DataBean.GoodsBriefBean;
import com.bwie.ynf.util.HttpUtil;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class classify_frag extends Fragment {
	private View view;
	private ImageView ivDeleteText;
	private EditText etSearch;
	private com.bwie.ynf.view.MyGridview classify_gv_view1;
	private com.bwie.ynf.view.MyGridview classify_gv_view2;
	private List<String> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_classify, container, false);
		info();
		// 设置搜索框效果
		search_box();
		setadapter();
		return view;
	}

	/*public void calculate() {
		WindowManager manager = this.getActivity().getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		int hei = metrics.heightPixels;
	}*/

	// 获取控件
	private void info() {
		ivDeleteText = (ImageView) view.findViewById(R.id.ivDeleteText);
		etSearch = (EditText) view.findViewById(R.id.etSearch);
		classify_gv_view1 = (com.bwie.ynf.view.MyGridview) view
				.findViewById(R.id.classify_gv_view1);
		classify_gv_view2 = (com.bwie.ynf.view.MyGridview) view
				.findViewById(R.id.classify_gv_view2);
	}

	// 设置搜索框效果
	private void search_box() {
		ivDeleteText.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				etSearch.setText("");
			}
		});

		etSearch.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {
				if (s.length() == 0) {
					ivDeleteText.setVisibility(View.GONE);
				} else {
					ivDeleteText.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	public void setadapter() {
		list = new ArrayList<String>();
		list.add("补水保湿");
		list.add("美白提亮");
		list.add("实惠套装");
		list.add("贴式面膜");
		list.add("控油祛痘");
		list.add("洁面乳");
		classify_gv_view1.setAdapter(new Classify_gv1_adapter(getActivity(),
				list));
		new AsyncTask<String, Void, Void>() {

			private String jsonData;
			private ClassifyData classify_data;
			private DataBean data;
			private List<GoodsBriefBean> goodsBrief;

			@Override
			protected Void doInBackground(String... params) {
				try {
					jsonData = HttpUtil.getjson(params[0]);
					Gson gson = new Gson();
					classify_data = gson.fromJson(jsonData, ClassifyData.class);
					data = classify_data.getData();
					goodsBrief = data.getGoodsBrief();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				classify_gv_view2.setAdapter(new Classify_gv2_adapter(
						getActivity(), goodsBrief));
				classify_gv_view2.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
						Intent intent=new Intent(getActivity(), ProductdetailsActivity.class);
						intent.putExtra("goodsid", goodsBrief.get(position).getId());
						startActivity(intent);
					}
				});
			};
		}.execute("http://m.yunifang.com/yunifang/mobile/category/list?random=60729&encode=d5f7520dad446ad974110b1b2cb499c8");

	}
}
