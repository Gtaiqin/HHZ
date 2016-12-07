package com.bwie.ynf.frag;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.bwie.ynf.R;
import com.bwie.ynf.adapter.Gooddetails_frag1_lv_adapter;
import com.bwie.ynf.bean.UrlDataBean;
import com.bwie.ynf.view.MyListview;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Detailed_gooddetails extends Fragment {
	private View view;
	private com.bwie.ynf.view.MyListview gooddetails_frag1_lv_view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_detailed_gooddetails, container, false);
		SharedPreferences preferences = getActivity().getSharedPreferences("detailed_jsondata", getActivity().MODE_PRIVATE);
		String goods_desc = preferences.getString("goods_desc", null);
		// 得到控件
		infoview();
		// 解析商品详情图片
		Gson gson = new Gson();
		@SuppressWarnings("unused")
		List<UrlDataBean> list = new ArrayList<UrlDataBean>();
		Type type = new TypeToken<List<UrlDataBean>>() {
		}.getType();
		List<UrlDataBean> UrlData = gson.fromJson(goods_desc, type);
		list = UrlData;
		gooddetails_frag1_lv_view.setAdapter(new Gooddetails_frag1_lv_adapter(list, getActivity()));
		gooddetails_frag1_lv_view.setDivider(null);
		return view;
	}

	private void infoview() {
		gooddetails_frag1_lv_view = (MyListview) view.findViewById(R.id.gooddetails_frag1_lv_view);
	}
}
