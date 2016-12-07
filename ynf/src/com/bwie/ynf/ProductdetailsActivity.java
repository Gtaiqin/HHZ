package com.bwie.ynf;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.alipay.sdk.app.PayTask;
import com.bwie.ynf.R;
import com.bwie.ynf.adapter.DetailedPagerAdapter;
import com.bwie.ynf.adapter.Detailed_lv_view1_adapter;
import com.bwie.ynf.bean.DetailedBean;
import com.bwie.ynf.bean.DetailedBean.DataBean;
import com.bwie.ynf.bean.DetailedBean.DataBean.ActivityBean;
import com.bwie.ynf.bean.DetailedBean.DataBean.GoodsBean;
import com.bwie.ynf.bean.DetailedBean.DataBean.GoodsBean.GalleryBean;
import com.bwie.ynf.bean.Goods;
import com.bwie.ynf.domain.PayResult;
import com.bwie.ynf.frag.Detailed_comment;
import com.bwie.ynf.frag.Detailed_gooddetails;
import com.bwie.ynf.frag.Detailed_goodparameter;
import com.bwie.ynf.util.MyDatabase;
import com.bwie.ynf.util.DbUtils;
import com.bwie.ynf.util.HttpUtil;
import com.bwie.ynf.util.SignUtils;
import com.bwie.ynf.view.ChildViewPager;
import com.bwie.ynf.view.MyListview;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import android.widget.ImageView.ScaleType;

public class ProductdetailsActivity extends FragmentActivity implements OnClickListener {

	private ImageView back;
	private String goodsid;
	private ImageLoader loader;
	private ChildViewPager goods_detailed_pager;
	private RadioGroup goods_detailed_radiogroup;
	private TextView detailed_goodname;
	@SuppressWarnings("unused")
	private CheckBox rating_radiobutton;
	@SuppressWarnings("unused")
	private TextView rating_text;
	private TextView detailed_shop_price;
	private TextView detailed_market_price;
	@SuppressWarnings("unused")
	private ImageView voucher_img;
	private TextView collection_number_text;
	private TextView sales_volume_text;
	private com.bwie.ynf.view.MyListview detailed_lv_view1;
	private ScrollView detailedscroll;
	private Button gooddetails;
	private Button goodparameter;
	private Button comment;
	private Detailed_gooddetails frag_gooddetails;
	private Detailed_comment frag_comment;
	private Detailed_goodparameter frag_goodparameter;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private Bundle bundle;
	private String goods_desc;
	private ImageView share;
	private String jsondata;
	private DetailedBean detailedBean;
	private DataBean dataBean;
	private GoodsBean goodsBean;
	private List<GalleryBean> gallery;
	private List<ActivityBean> activitylist;
	private RelativeLayout add_shopcar;
	private MyDatabase dbutil;
	public static final String PARTNER = "2088901305856832";
	// 商户收款账号
	public static final String SELLER = "8@qdbaiu.com";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuyovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHDZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaarBCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDVb2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/gX+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyXrGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPnJ+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdIrk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM604hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaDTqJxLy6AnQ+Q==";
	private static final int SDK_PAY_FLAG = 1;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息

				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(ProductdetailsActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(ProductdetailsActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(ProductdetailsActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			default:
				break;
			}
		}

	};
	private RelativeLayout shop_bay;
	private boolean fal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_productdetails);
		info();
		fal = true;
		// 设置scrollview滑动定位到顶部
		detailedscroll.smoothScrollTo(0, 20);
		// 设置监听
		back.setOnClickListener(this);
		gooddetails.setOnClickListener(this);
		goodparameter.setOnClickListener(this);
		comment.setOnClickListener(this);
		share.setOnClickListener(this);
		add_shopcar.setOnClickListener(this);
		shop_bay.setOnClickListener(this);
		Intent intent = getIntent();
		goodsid = intent.getStringExtra("goodsid");
		getdetaileddata();
	}

	public void getdetaileddata() {
		new AsyncTask<String, Void, Void>() {

			@Override
			protected Void doInBackground(String... params) {
				try {
					jsondata = HttpUtil.getjson(params[0]);
					Gson gson = new Gson();
					detailedBean = gson.fromJson(jsondata, DetailedBean.class);
					dataBean = detailedBean.getData();
					activitylist = dataBean.getActivity();
					goodsBean = dataBean.getGoods();
					gallery = goodsBean.getGallery();
					goods_desc = goodsBean.getGoods_desc();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Void result) {
				List<ImageView> pager_imgurl_list = new ArrayList<ImageView>();
				for (int i = 0; i < gallery.size(); i++) {
					ImageView imageView = new ImageView(ProductdetailsActivity.this);
					imageView.setScaleType(ScaleType.FIT_XY);

					loader.displayImage(gallery.get(i).getNormal_url(), imageView);
					pager_imgurl_list.add(imageView);
				}
				// 设置viewpager适配器
				goods_detailed_pager.setAdapter(new DetailedPagerAdapter(pager_imgurl_list));
				// 设置小圆点
				setdot(gallery);
				detailed_goodname.setText(goodsBean.getGoods_name());
				detailed_shop_price.setText("￥" + goodsBean.getShop_price() + "");
				detailed_market_price.setText("￥" + goodsBean.getMarket_price() + "");
				detailed_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
				BigDecimal b1 = new BigDecimal(Double.toString(goodsBean.getSales_volume()));
				BigDecimal b2 = new BigDecimal(Double.toString(10000));
				double f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
				sales_volume_text.setText(f1 + "万");
				collection_number_text.setText(goodsBean.getCollect_count() + "");
				detailed_lv_view1.setFocusable(false);
				detailed_lv_view1.setAdapter(new Detailed_lv_view1_adapter(activitylist, ProductdetailsActivity.this));
				// 详情页面底部 产品详情、产品参数、评论
				frag_gooddetails = new Detailed_gooddetails();
				frag_comment = new Detailed_comment();
				frag_goodparameter = new Detailed_goodparameter();
				// 设置fragment容器
				manager = getSupportFragmentManager();
				transaction = manager.beginTransaction();
				SharedPreferences.Editor editor = getSharedPreferences("detailed_jsondata", MODE_PRIVATE).edit();
				editor.putString("goods_desc", goods_desc);
				editor.putString("jsondata", jsondata);
				editor.commit();
				FragmentTransaction trnTransaction = getSupportFragmentManager().beginTransaction();
				trnTransaction.add(R.id.detailed_frame, frag_gooddetails, "gooddetails");
				trnTransaction.add(R.id.detailed_frame, frag_goodparameter, "goodparameter");
				trnTransaction.add(R.id.detailed_frame, frag_comment, "comment");
				switchTag("gooddetails");
				trnTransaction.commit();
			};
		}.execute(
				"http://m.yunifang.com/yunifang/mobile/goods/detail?random=6716&encode=b02382bd9e457e06e09b68a6a4f26eb4&id="
						+ goodsid);
	}

	// 详情页面的viewpager设置小圆点
	private void setdot(final List<GalleryBean> gallery) {
		int wrop = RadioGroup.LayoutParams.WRAP_CONTENT;
		RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(wrop, wrop);
		params.leftMargin = 5;
		for (int i = 0; i < gallery.size(); i++) {
			RadioButton button = new RadioButton(this);
			button.setEnabled(false);
			button.setId(i);
			button.setButtonDrawable(R.drawable.dot_selector);
			if (i == 0) {
				goods_detailed_radiogroup.addView(button);
			} else {
				goods_detailed_radiogroup.addView(button, params);
			}
		}
		goods_detailed_radiogroup.check(0);
		goods_detailed_pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				goods_detailed_radiogroup.check(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	// 获取控件ID
	public void info() {
		back = (ImageView) findViewById(R.id.back);
		goods_detailed_pager = (com.bwie.ynf.view.ChildViewPager) findViewById(R.id.goods_detailed_pager);
		goods_detailed_radiogroup = (RadioGroup) findViewById(R.id.goods_detailed_radiogroup);
		detailed_goodname = (TextView) findViewById(R.id.detailed_goodname);
		rating_radiobutton = (CheckBox) findViewById(R.id.rating_radiobutton);
		rating_text = (TextView) findViewById(R.id.rating_text);
		detailed_shop_price = (TextView) findViewById(R.id.detailed_shop_price);
		detailed_market_price = (TextView) findViewById(R.id.detailed_market_price);
		voucher_img = (ImageView) findViewById(R.id.Voucher_img);
		collection_number_text = (TextView) findViewById(R.id.Collection_number_text);
		sales_volume_text = (TextView) findViewById(R.id.Sales_volume_text);
		detailed_lv_view1 = (MyListview) findViewById(R.id.detailed_lv_view1);
		detailedscroll = (ScrollView) findViewById(R.id.detailedscroll);
		gooddetails = (Button) findViewById(R.id.gooddetails);
		goodparameter = (Button) findViewById(R.id.goodparameter);
		comment = (Button) findViewById(R.id.comment);
		share = (ImageView) findViewById(R.id.share);
		add_shopcar = (RelativeLayout) findViewById(R.id.add_shopcar);
		dbutil = new MyDatabase(this);
		loader = ImageLoader.getInstance();
		loader.init(ImageLoaderConfiguration.createDefault(this));
		shop_bay = (RelativeLayout) findViewById(R.id.shop_bay);
	}

	// 监听事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.gooddetails:
			switchTag("gooddetails");
			goodparameter.setTextColor(Color.parseColor("#969696"));
			gooddetails.setTextColor(Color.parseColor("#fc6b87"));
			comment.setTextColor(Color.parseColor("#969696"));
			break;
		case R.id.goodparameter:
			switchTag("goodparameter");
			goodparameter.setTextColor(Color.parseColor("#fc6b87"));
			gooddetails.setTextColor(Color.parseColor("#969696"));
			comment.setTextColor(Color.parseColor("#969696"));
			break;
		case R.id.comment:
			switchTag("comment");
			goodparameter.setTextColor(Color.parseColor("#969696"));
			gooddetails.setTextColor(Color.parseColor("#969696"));
			comment.setTextColor(Color.parseColor("#fc6b87"));
			break;
		case R.id.share:
			showShare();
			break;
		case R.id.add_shopcar:
			DbUtils db = new DbUtils(this);
			int count = db.idread(goodsid);
			if (count == 0) {
				boolean addcar = db.add(new Goods(goodsid, goodsBean.getGoods_name(), goodsBean.getShop_price(), 1,
						goodsBean.getGoods_img()));
				if (addcar == true) {
					Toast.makeText(this, "购物车无此商品，已添加", 0).show();
				} else {
					Toast.makeText(this, "添加失败", 0).show();
				}
			} else {
				db.update(goodsid, count);
				Toast.makeText(this, "购物车中已有该商品,已为您再次添加", 0).show();
			}
			/*
			 * boolean addcar = db.add(new Goods(goodsid,
			 * goodsBean.getGoods_name(), goodsBean.getShop_price(), 1,
			 * goodsBean.getGoods_img())); if (addcar == true) {
			 * Toast.makeText(this, "加入购物车成功", 0).show(); } else {
			 * Toast.makeText(this, "添加失败", 0).show(); }
			 */
			break;
		case R.id.shop_bay:
			// 提交订单点击事件
			if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
				new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialoginterface, int i) {
								//
								finish();
							}
						}).show();
				return;
			}

			String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

			/**
			 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
			 */
			String sign = sign(orderInfo);
			try {
				/**
				 * 仅需对sign 做URL编码
				 */
				sign = URLEncoder.encode(sign, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			/**
			 * 完整的符合支付宝参数规范的订单信息
			 */
			final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

			Runnable payRunnable = new Runnable() {

				@Override
				public void run() {
					// 构造PayTask 对象
					PayTask alipay = new PayTask(ProductdetailsActivity.this);
					// 调用支付接口，获取支付结果
					String result = alipay.pay(payInfo, true);

					Message msg = new Message();
					msg.what = SDK_PAY_FLAG;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			};

			// 必须异步调用
			Thread payThread = new Thread(payRunnable);
			payThread.start();
		default:
			break;
		}
	}

	private void switchTag(String str) {
		FragmentTransaction transaction = manager.beginTransaction();
		if (str.equals("gooddetails")) {
			transaction.show(frag_gooddetails);
			transaction.hide(frag_comment);
			transaction.hide(frag_goodparameter);
		} else if (str.equals("goodparameter")) {
			transaction.show(frag_goodparameter);
			transaction.hide(frag_comment);
			transaction.hide(frag_gooddetails);
		} else if (str.equals("comment")) {
			transaction.show(frag_comment);
			transaction.hide(frag_gooddetails);
			transaction.hide(frag_goodparameter);
		}
		transaction.commit();
	}

	// 分享
	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("我在【御泥坊】看到一件非常不错的宝贝...");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://m.yunifang.com/yunifang/web/share/goods?goods_id=" + goodsid);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(goodsBean.getGoods_name());
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(gallery.get(0).getNormal_url());
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://m.yunifang.com/yunifang/web/share/goods?goods_id=" + goodsid);
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://m.yunifang.com/yunifang/web/share/goods?goods_id=" + goodsid);

		// 启动分享GUI
		oks.show(this);
	}

	/**
	 * create the order info. 创建订单信息
	 */
	private String getOrderInfo(String subject, String body, String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号--------------------------拿到提交订单返回的数据
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";// -----------传

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径-------商户服务器
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 *
	 * @param content
	 *            待签名订单信息
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 */
	private String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	@Override
	protected void onDestroy() {
		fal = false;
		finish();
		super.onDestroy();
	}
}
