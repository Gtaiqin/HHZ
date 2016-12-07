package com.bwie.ynf.frag;

import java.util.List;
import com.bwie.ynf.R;
import com.bwie.ynf.adapter.Gooddetails_frag3_lv_adapter;
import com.bwie.ynf.bean.DetailedBean;
import com.bwie.ynf.bean.DetailedBean.DataBean;
import com.bwie.ynf.bean.DetailedBean.DataBean.CommentsBean;
import com.bwie.ynf.view.MyListview;
import com.google.gson.Gson;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Detailed_comment extends Fragment {
	private View view;
	private com.bwie.ynf.view.MyListview gooddetails_frag3_lv_view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_detailed_comment, container, false);
		infoview();
		SharedPreferences preferences = getActivity().getSharedPreferences("detailed_jsondata",
				getActivity().MODE_PRIVATE);
		String jsondata = preferences.getString("jsondata", null);
		Gson gson = new Gson();
		DetailedBean detailedBean = gson.fromJson(jsondata, DetailedBean.class);
		DataBean dataBean = detailedBean.getData();
		List<CommentsBean> comments = dataBean.getComments();
		gooddetails_frag3_lv_view.setAdapter(new Gooddetails_frag3_lv_adapter(comments, getActivity()));
		return view;
	}

	private void infoview() {
		gooddetails_frag3_lv_view = (MyListview) view.findViewById(R.id.gooddetails_frag3_lv_view);
	}
}
