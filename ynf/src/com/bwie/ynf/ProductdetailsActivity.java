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
	// �̻��տ��˺�
	public static final String SELLER = "8@qdbaiu.com";
	// �̻�˽Կ��pkcs8��ʽ
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
				 * ͬ�����صĽ��������õ�����˽�����֤����֤�Ĺ����뿴https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) �����̻������첽֪ͨ
				 */
				String resultInfo = payResult.getResult();// ͬ��������Ҫ��֤����Ϣ

				String resultStatus = payResult.getResultStatus();
				// �ж�resultStatus Ϊ��9000�������֧���ɹ�������״̬�������ɲο��ӿ��ĵ�
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(ProductdetailsActivity.this, "֧���ɹ�", Toast.LENGTH_SHORT).show();
				} else {
					// �ж�resultStatus Ϊ��"9000"��������֧��ʧ��
					// "8000"����֧�������Ϊ֧������ԭ�����ϵͳԭ���ڵȴ�֧�����ȷ�ϣ����ս����Ƿ�ɹ��Է�����첽֪ͨΪ׼��С����״̬��
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(ProductdetailsActivity.this, "֧�����ȷ����", Toast.LENGTH_SHORT).show();

					} else {
						// ����ֵ�Ϳ����ж�Ϊ֧��ʧ�ܣ������û�����ȡ��֧��������ϵͳ���صĴ���
						Toast.makeText(ProductdetailsActivity.this, "֧��ʧ��", Toast.LENGTH_SHORT).show();

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
		// ����scrollview������λ������
		detailedscroll.smoothScrollTo(0, 20);
		// ���ü���
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
				// ����viewpager������
				goods_detailed_pager.setAdapter(new DetailedPagerAdapter(pager_imgurl_list));
				// ����СԲ��
				setdot(gallery);
				detailed_goodname.setText(goodsBean.getGoods_name());
				detailed_shop_price.setText("��" + goodsBean.getShop_price() + "");
				detailed_market_price.setText("��" + goodsBean.getMarket_price() + "");
				detailed_market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
				BigDecimal b1 = new BigDecimal(Double.toString(goodsBean.getSales_volume()));
				BigDecimal b2 = new BigDecimal(Double.toString(10000));
				double f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
				sales_volume_text.setText(f1 + "��");
				collection_number_text.setText(goodsBean.getCollect_count() + "");
				detailed_lv_view1.setFocusable(false);
				detailed_lv_view1.setAdapter(new Detailed_lv_view1_adapter(activitylist, ProductdetailsActivity.this));
				// ����ҳ��ײ� ��Ʒ���顢��Ʒ����������
				frag_gooddetails = new Detailed_gooddetails();
				frag_comment = new Detailed_comment();
				frag_goodparameter = new Detailed_goodparameter();
				// ����fragment����
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

	// ����ҳ���viewpager����СԲ��
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

	// ��ȡ�ؼ�ID
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

	// �����¼�
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
					Toast.makeText(this, "���ﳵ�޴���Ʒ�������", 0).show();
				} else {
					Toast.makeText(this, "���ʧ��", 0).show();
				}
			} else {
				db.update(goodsid, count);
				Toast.makeText(this, "���ﳵ�����и���Ʒ,��Ϊ���ٴ����", 0).show();
			}
			/*
			 * boolean addcar = db.add(new Goods(goodsid,
			 * goodsBean.getGoods_name(), goodsBean.getShop_price(), 1,
			 * goodsBean.getGoods_img())); if (addcar == true) {
			 * Toast.makeText(this, "���빺�ﳵ�ɹ�", 0).show(); } else {
			 * Toast.makeText(this, "���ʧ��", 0).show(); }
			 */
			break;
		case R.id.shop_bay:
			// �ύ��������¼�
			if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
				new AlertDialog.Builder(this).setTitle("����").setMessage("��Ҫ����PARTNER | RSA_PRIVATE| SELLER")
						.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialoginterface, int i) {
								//
								finish();
							}
						}).show();
				return;
			}

			String orderInfo = getOrderInfo("���Ե���Ʒ", "�ò�����Ʒ����ϸ����", "0.01");

			/**
			 * �ر�ע�⣬�����ǩ���߼���Ҫ���ڷ���ˣ�����˽Կй¶�ڴ����У�
			 */
			String sign = sign(orderInfo);
			try {
				/**
				 * �����sign ��URL����
				 */
				sign = URLEncoder.encode(sign, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			/**
			 * �����ķ���֧���������淶�Ķ�����Ϣ
			 */
			final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

			Runnable payRunnable = new Runnable() {

				@Override
				public void run() {
					// ����PayTask ����
					PayTask alipay = new PayTask(ProductdetailsActivity.this);
					// ����֧���ӿڣ���ȡ֧�����
					String result = alipay.pay(payInfo, true);

					Message msg = new Message();
					msg.what = SDK_PAY_FLAG;
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			};

			// �����첽����
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

	// ����
	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle("���ڡ����෻������һ���ǳ�����ı���...");
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl("http://m.yunifang.com/yunifang/web/share/goods?goods_id=" + goodsid);
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText(goodsBean.getGoods_name());
		// ��������ͼƬ������΢����������ͼƬ��Ҫͨ����˺�����߼�д��ӿڣ�������ע�͵���������΢��
		oks.setImageUrl(gallery.get(0).getNormal_url());
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		// oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl("http://m.yunifang.com/yunifang/web/share/goods?goods_id=" + goodsid);
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite(getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl("http://m.yunifang.com/yunifang/web/share/goods?goods_id=" + goodsid);

		// ��������GUI
		oks.show(this);
	}

	/**
	 * create the order info. ����������Ϣ
	 */
	private String getOrderInfo(String subject, String body, String price) {

		// ǩԼ���������ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// ǩԼ����֧�����˺�
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// �̻���վΨһ������--------------------------�õ��ύ�������ص�����
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";// -----------��

		// ��Ʒ����
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// ��Ʒ����
		orderInfo += "&body=" + "\"" + body + "\"";

		// ��Ʒ���
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// �������첽֪ͨҳ��·��-------�̻�������
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

		// ����ӿ����ƣ� �̶�ֵ
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// ֧�����ͣ� �̶�ֵ
		orderInfo += "&payment_type=\"1\"";

		// �������룬 �̶�ֵ
		orderInfo += "&_input_charset=\"utf-8\"";

		// ����δ����׵ĳ�ʱʱ��
		// Ĭ��30���ӣ�һ����ʱ���ñʽ��׾ͻ��Զ����رա�
		// ȡֵ��Χ��1m��15d��
		// m-���ӣ�h-Сʱ��d-�죬1c-���죨���۽��׺�ʱ����������0��رգ���
		// �ò�����ֵ������С���㣬��1.5h����ת��Ϊ90m��
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_tokenΪ���������Ȩ��ȡ����alipay_open_id,���ϴ˲����û���ʹ����Ȩ���˻�����֧��
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// ֧��������������󣬵�ǰҳ����ת���̻�ָ��ҳ���·�����ɿ�
		orderInfo += "&return_url=\"m.alipay.com\"";

		// �������п�֧���������ô˲���������ǩ���� �̶�ֵ ����ҪǩԼ���������п����֧��������ʹ�ã�
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * sign the order info. �Զ�����Ϣ����ǩ��
	 *
	 * @param content
	 *            ��ǩ��������Ϣ
	 */
	private String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. ��ȡǩ����ʽ
	 */
	private String getSignType() {
		return "sign_type=\"RSA\"";
	}

	/**
	 * get the out_trade_no for an order. �����̻������ţ���ֵ���̻���Ӧ����Ψһ�����Զ����ʽ�淶��
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
