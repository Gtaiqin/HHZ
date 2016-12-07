package com.bwie.ynf.frag;

import java.util.ArrayList;
import java.util.List;
import com.bwie.ynf.LoginActivity;
import com.bwie.ynf.MapActivity;
import com.bwie.ynf.QRcodeActivity;
import com.bwie.ynf.R;
import com.bwie.ynf.SettingsActivity;
import com.bwie.ynf.WebActivity;
import com.bwie.ynf.adapter.User_lv_adapter;
import com.bwie.ynf.bean.user_lv_column;
import com.nostra13.universalimageloader.core.ImageLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class user_frag extends Fragment implements OnClickListener {
	private ScrollView user_scroll_view;
	private View view;
	private ListView user_lv_view;
	private List<user_lv_column> list;
	private user_lv_column u1, u2, u3, u4, u5, u6, u7;
	private Button signorlogin;
	private TextView username;
	private ImageView user_icon;
	private ImageView user_setting;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// �õ�fragment��ͼ
		view = inflater.inflate(R.layout.frag_user, container, false);
		// �õ��ؼ�
		info();
		// ����Ĭ��scrollview�����ö�
		user_scroll_view.smoothScrollTo(0, 20);
		user_setting.setOnClickListener(this);
		signorlogin.setOnClickListener(this);
		// listview����
		getlvadapter();
		return view;
	}

	// �õ��ؼ�
	public void info() {
		user_lv_view = (ListView) view.findViewById(R.id.user_lv_view);
		user_scroll_view = (ScrollView) view.findViewById(R.id.user_scroll_view);
		signorlogin = (Button) view.findViewById(R.id.signorlogin);
		username = (TextView) view.findViewById(R.id.username);
		user_icon = (ImageView) view.findViewById(R.id.user_icon);
		user_setting = (ImageView) view.findViewById(R.id.user_setting);
	};

	// listview����
	public void getlvadapter() {
		list = new ArrayList<user_lv_column>();
		u1 = new user_lv_column(R.drawable.my_order_icon, "�ҵ�λ��");
		u2 = new user_lv_column(R.drawable.my_vip_icon, "�ҵĶ�ά��");
		u3 = new user_lv_column(R.drawable.my_invite, "��������");
		u4 = new user_lv_column(R.drawable.my_coupon_icon, "�ҵ��ֽ�ȯ");
		u5 = new user_lv_column(R.drawable.my_lottery_icon, "�ҵĳ齱��");
		u6 = new user_lv_column(R.drawable.personal_center_contact_service_icon, "��ϵ�ͷ�");
		u7 = new user_lv_column(R.drawable.my_collection_icon, "���ղص���Ʒ");
		list.add(u1);
		list.add(u2);
		list.add(u3);
		list.add(u4);
		list.add(u5);
		list.add(u6);
		list.add(u7);
		user_lv_view.setAdapter(new User_lv_adapter(list, getActivity()));
		user_lv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if (position == 0) {
					Intent intent = new Intent(getActivity(), MapActivity.class);
					startActivity(intent);
				} /*else if (position == 1) {
					Intent intent = new Intent(getActivity(), QRcodeActivity.class);
					startActivity(intent);
				} */else if (position == 5) {
					// ��intent��������绰
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13021215516"));
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signorlogin:
			String signorlogintext = signorlogin.getText().toString();
			if (signorlogintext.equals("��½")) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
			} else if (signorlogintext.equals("ǩ��")) {
				Intent intent = new Intent(getActivity(), WebActivity.class);
				intent.putExtra("url", "http://mobile.hmeili.com:7900/yunifang/web/help/cash");
				intent.putExtra("webtitle", "ÿ��ǩ��");
				startActivity(intent);
			}
			break;

		case R.id.user_setting:
			Intent intent = new Intent(getActivity(), SettingsActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void onStart() {
		SharedPreferences preferences = getActivity().getSharedPreferences("User_data", getActivity().MODE_PRIVATE);
		String user_icon_url = preferences.getString("user_icon_url", null);
		String user_d3username = preferences.getString("user_d3username", null);
		if (user_icon_url != null || user_d3username != null) {
			signorlogin.setText("ǩ��");
			ImageLoader loader = ImageLoader.getInstance();
			loader.displayImage(user_icon_url, user_icon);
			username.setText(user_d3username);
		} else {
			signorlogin.setText("��½");
			username.setText("");
			user_icon.setImageResource(R.drawable.user_icon_default);
		}
		super.onStart();
	}

}
