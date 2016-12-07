package com.bwie.ynf.frag;

import java.util.List;

import com.bwie.ynf.R;
import com.bwie.ynf.adapter.Gooddetails_frag2_lv_adapter;
import com.bwie.ynf.bean.DetailedBean;
import com.bwie.ynf.bean.DetailedBean.DataBean;
import com.bwie.ynf.bean.DetailedBean.DataBean.GoodsBean;
import com.bwie.ynf.bean.DetailedBean.DataBean.GoodsBean.AttributesBean;
import com.bwie.ynf.view.MyListview;
import com.google.gson.Gson;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Detailed_goodparameter extends Fragment {
	private View view;
	private com.bwie.ynf.view.MyListview gooddetails_frag2_lv_view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_detailed_goodparameter, container, false);
		infoview();
		SharedPreferences preferences = getActivity().getSharedPreferences("detailed_jsondata",
				getActivity().MODE_PRIVATE);
		String jsondata = preferences.getString("jsondata", null);
		Gson gson = new Gson();
		DetailedBean detailedBean = gson.fromJson(jsondata, DetailedBean.class);
		DataBean dataBean = detailedBean.getData();
		GoodsBean goodsBean = dataBean.getGoods();
		List<AttributesBean> attributes = goodsBean.getAttributes();
		gooddetails_frag2_lv_view.setAdapter(new Gooddetails_frag2_lv_adapter(attributes, getActivity()));
		gooddetails_frag2_lv_view.setDivider(null);
		gooddetails_frag2_lv_view.setEnabled(false);
		return view;
	}

	private void infoview() {
		gooddetails_frag2_lv_view = (MyListview) view.findViewById(R.id.gooddetails_frag2_lv_view);
	}
}
