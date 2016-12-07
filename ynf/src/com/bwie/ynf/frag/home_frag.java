package com.bwie.ynf.frag;

import java.util.ArrayList;
import java.util.List;

import com.bwie.ynf.MoreActivity;
import com.bwie.ynf.R;
import com.bwie.ynf.WebActivity;
import com.bwie.ynf.adapter.Home_lv_adapter;
import com.bwie.ynf.adapter.HomePagerAdapter;
import com.bwie.ynf.bean.HomeData;
import com.bwie.ynf.bean.HomeData.DataBean;
import com.bwie.ynf.bean.HomeData.DataBean.Ad1Bean;
import com.bwie.ynf.bean.HomeData.DataBean.Ad3Bean;
import com.bwie.ynf.bean.HomeData.DataBean.Ad4Bean;
import com.bwie.ynf.bean.HomeData.DataBean.Ad5Bean;
import com.bwie.ynf.bean.HomeData.DataBean.GroupBriefsBean;
import com.bwie.ynf.util.HttpUtil;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class home_frag extends Fragment implements OnClickListener {

	private ViewPager home_viewpager1;
	private ImageLoader loader;
	private View view;
	private ListView home_goods_lv_view;
	private TextView textview1;
	private TextView textview2;
	private TextView textview3;
	private TextView textview4;
	private ScrollView homescroll;
	private RadioGroup pager_radiogroup;
	private RelativeLayout home_sign_board;
	private RelativeLayout home_ntegral;
	private RelativeLayout home_conversion;
	private RelativeLayout home_check;
	private ImageView home_signimg_1, home_signimg_2, home_signimg_3, home_signimg_4, zero_lottery, newbie_vip,
			vip_equity;

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				home_viewpager1.setCurrentItem(home_viewpager1.getCurrentItem() + 1);
				pagercirculation();
			}
		}
	};
	private NetworkInfo networkInfo;
	private RelativeLayout moregood;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_home, container, false);
		// 得到首页上的控件
		info();

		// 设置scrollview滑动定位到顶部
		homescroll.smoothScrollTo(0, 20);
		// 为活动导航设置图片
		try {
			// 请求首页数据并展示
			getdatatoshow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		home_check.setOnClickListener(this);
		home_sign_board.setOnClickListener(this);
		home_ntegral.setOnClickListener(this);
		home_conversion.setOnClickListener(this);
		newbie_vip.setOnClickListener(this);
		vip_equity.setOnClickListener(this);
		moregood.setOnClickListener(this);
		// 更新viewpager页面
		pagercirculation();
		return view;
	}

	// 请求首页数据并展示
	public void getdatatoshow() throws Exception {
		new AsyncTask<String, Void, Void>() {
			private HomeData home_json;
			private List<Ad1Bean> ad1;
			private List<Ad3Bean> ad3;
			private List<Ad4Bean> ad4;
			private List<Ad5Bean> ad5;
			private List<GroupBriefsBean> groupBriefs;

			@Override
			protected Void doInBackground(String... params) {
				HttpUtil util = new HttpUtil();
				try {
					@SuppressWarnings("static-access")
					String json = util.getjson(params[0]);
					Gson gson = new Gson();

					home_json = gson.fromJson(json, HomeData.class);
					DataBean data = home_json.getData();
					ad1 = data.getAd1();
					ad3 = data.getAd3();
					ad4 = data.getAd4();
					ad5 = data.getAd5();
					groupBriefs = data.getGroupBriefs();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				// viewpager适配
				ArrayList<ImageView> listimg = new ArrayList<ImageView>();
				for (int i = 0; i < ad1.size(); i++) {
					ImageView imageView = new ImageView(getActivity());
					imageView.setScaleType(ScaleType.FIT_XY);

					loader.displayImage(ad1.get(i).getImage(), imageView);
					listimg.add(imageView);
				}
				// 给viewpager设置小圆点
				setdot(ad1);
				// 设置适配器
				home_viewpager1.setAdapter(new HomePagerAdapter(listimg));
				// 设置签到栏目
				List<ImageView> listimg2 = new ArrayList<ImageView>();
				listimg2.add(home_signimg_1);
				listimg2.add(home_signimg_2);
				listimg2.add(home_signimg_3);
				listimg2.add(home_signimg_4);
				List<TextView> textlist = new ArrayList<TextView>();
				textlist.add(textview1);
				textlist.add(textview2);
				textlist.add(textview3);
				textlist.add(textview4);
				for (int i = 0; i < 4; i++) {
					loader.displayImage(ad5.get(i).getImage(), listimg2.get(i));
					textlist.get(i).setText(ad5.get(i).getTitle());
				}
				// 设置0元抽奖图片
				loader.displayImage(ad3.get(0).getLottery_img(), zero_lottery);
				loader.displayImage(ad4.get(0).getImage(), newbie_vip);
				loader.displayImage(ad4.get(1).getImage(), vip_equity);
				// 设置专区专品
				home_goods_lv_view.setFocusable(false);
				home_goods_lv_view.setAdapter(new Home_lv_adapter(groupBriefs, getActivity()));
			};

		}.execute("http://m.yunifang.com/yunifang/mobile/home?random=59676&encode=62d458fefce9c740359873cc19b05188");
	}

	// 得到控件
	public void info() {
		homescroll = (ScrollView) view.findViewById(R.id.home_scroll);
		pager_radiogroup = (RadioGroup) view.findViewById(R.id.pager_radiogroup);
		home_viewpager1 = (ViewPager) view.findViewById(R.id.home_pager1);
		home_signimg_1 = (ImageView) view.findViewById(R.id.home_signimg_1);
		home_signimg_2 = (ImageView) view.findViewById(R.id.home_signimg_2);
		home_signimg_3 = (ImageView) view.findViewById(R.id.home_signimg_3);
		home_signimg_4 = (ImageView) view.findViewById(R.id.home_signimg_4);
		textview1 = (TextView) view.findViewById(R.id.textview1);
		textview2 = (TextView) view.findViewById(R.id.textview2);
		textview3 = (TextView) view.findViewById(R.id.textview3);
		textview4 = (TextView) view.findViewById(R.id.textview4);
		home_sign_board = (RelativeLayout) view.findViewById(R.id.home_sign_board);
		home_ntegral = (RelativeLayout) view.findViewById(R.id.home_ntegral);
		home_conversion = (RelativeLayout) view.findViewById(R.id.home_conversion);
		home_check = (RelativeLayout) view.findViewById(R.id.home_check);
		zero_lottery = (ImageView) view.findViewById(R.id.zero_lottery);
		newbie_vip = (ImageView) view.findViewById(R.id.newbie_vip);
		vip_equity = (ImageView) view.findViewById(R.id.vip_equity);
		home_goods_lv_view = (ListView) view.findViewById(R.id.home_goods_lv_view);
		moregood = (RelativeLayout) view.findViewById(R.id.moregood);
		loader = ImageLoader.getInstance();
		loader.init(ImageLoaderConfiguration.createDefault(getActivity()));
	};

	// 改变viewpager页面
	public void pagercirculation() {
		handler.sendEmptyMessageDelayed(0, 3000);
	}

	// 给viewpager设置小圆点
	private void setdot(final List<Ad1Bean> list) {
		int wrop = RadioGroup.LayoutParams.WRAP_CONTENT;
		RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(wrop, wrop);
		params.leftMargin = 5;
		for (int i = 0; i < list.size(); i++) {
			RadioButton button = new RadioButton(getActivity());
			button.setEnabled(false);
			button.setId(i);
			button.setButtonDrawable(R.drawable.dot_selector);
			if (i == 0) {
				pager_radiogroup.addView(button);
			} else {
				pager_radiogroup.addView(button, params);
			}
		}
		pager_radiogroup.check(0);
		home_viewpager1.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				pager_radiogroup.check(arg0 % list.size());
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.home_sign_board:
			intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url", "http://mobile.hmeili.com:7900/yunifang/web/help/cash");
			intent.putExtra("webtitle", "每日签到");
			startActivity(intent);
			break;
		case R.id.home_ntegral:
			intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url",
					"http://m.yunifang.com/yunifang/mobile/creditShop/loginDBShop?dbredirect=http://www.duiba.com.cn/chome/index");
			intent.putExtra("webtitle", "积分商城");
			startActivity(intent);
			break;
		case R.id.home_conversion:
			intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url", "http://m.yunifang.com/yunifang/web/exchangeCode/code");
			intent.putExtra("webtitle", "兑换专区");
			startActivity(intent);
			break;
		case R.id.home_check:
			intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url", "http://www.yunifang.com/a/fangweichaxun/wap_fwcx.html");
			intent.putExtra("webtitle", "真伪查询");
			startActivity(intent);
			break;
		case R.id.newbie_vip:
			intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url", "http://mobile.hmeili.com:7900/yunifang/web/help/cash");
			intent.putExtra("webtitle", "新人专享");
			startActivity(intent);
			break;
		case R.id.vip_equity:
			intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url", "http://mobile.hmeili.com/yunifang/web/member/gift");
			intent.putExtra("webtitle", "会员权益");
			startActivity(intent);
			break;
		case R.id.moregood:
			intent = new Intent(getActivity(), MoreActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}

	}
}
