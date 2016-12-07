package com.bwie.ynf.frag;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import com.alipay.sdk.app.PayTask;
import com.bwie.ynf.ProductdetailsActivity;
import com.bwie.ynf.R;
import com.bwie.ynf.bean.Goods;
import com.bwie.ynf.domain.PayResult;
import com.bwie.ynf.util.DbUtils;
import com.bwie.ynf.util.MyDatabase;
import com.bwie.ynf.util.SignUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class shopping_frag extends Fragment implements OnClickListener {
	private View view;
	private ListView shop_lv_view;
	private MyDatabase dbutil;
	private shop_lv_adapter adapter;
	private ImageView shop_shuax;
	private CheckBox checkall;
	private TextView shop_editbutton;
	private RelativeLayout rela_allprice;
	private Button shop_bayall;
	private String shop_bayall_text;
	private String shop_editbutton_text;
	// Pid 商户在支付宝的id
	public static final String PARTNER = "2088901305856832";
	// 商户收款账号
	public static final String SELLER = "8@qdbaiu.com";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuyovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHDZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaarBCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDVb2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/gX+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyXrGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPnJ+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdIrk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM604hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaDTqJxLy6AnQ+Q==";
	private static final int SDK_PAY_FLAG = 1;
	private double sum = 0.00;
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
					Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(getActivity(), "支付结果确认中", Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			default:
				break;
			}
		}

	};
	private TextView allprice;
	private List<Goods> list;
	private List<String> goodid_list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frag_shopping, container, false);
		infoview();
		// 刷新购物车数据
		shop_shuax.setOnClickListener(this);
		checkall.setOnClickListener(this);
		shop_editbutton.setOnClickListener(this);
		shop_bayall.setOnClickListener(this);
		return view;
	}

	private void infoview() {
		dbutil = new MyDatabase(getActivity());
		shop_lv_view = (ListView) view.findViewById(R.id.shop_lv_view);
		shop_shuax = (ImageView) view.findViewById(R.id.shop_shuax);
		checkall = (CheckBox) view.findViewById(R.id.checkall);
		shop_editbutton = (TextView) view.findViewById(R.id.shop_editbutton);
		rela_allprice = (RelativeLayout) view.findViewById(R.id.rela_allprice);
		shop_bayall = (Button) view.findViewById(R.id.shop_bayall);
		allprice = (TextView) view.findViewById(R.id.allprice);
	}

	@Override
	public void onStart() {
		showdata();
		super.onStart();
	}

	// 展示购物车数据数据库中的已有数据
	public void showdata() {
		// list = new ArrayList<Goods>();
		// 读取数据库
		DbUtils db = new DbUtils(getActivity());
		list = db.read();
		goodid_list = new ArrayList<String>();
		adapter = new shop_lv_adapter(getActivity(), list, goodid_list);
		shop_lv_view.setAdapter(adapter);
		shop_lv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent(getActivity(), ProductdetailsActivity.class);
				intent.putExtra("goodsid", list.get(position).getGoodid());
				getActivity().startActivity(intent);
			}
		});
		shop_lv_view.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				DbUtils db = new DbUtils(getActivity());
				db.delete(list.get(position).getGoodid());
				list.remove(position);
				adapter.notifyDataSetChanged();
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shop_shuax:
			showdata();
			break;
		case R.id.checkall:
			boolean flag = checkall.isChecked();
			double f1 = 0;
			for (int i = 0; i < adapter.getSelect().size(); i++) {
				adapter.getSelect().set(i, flag);
			}
			if (flag == true) {
				for (int i = 0; i < list.size(); i++) {
					if (i == 0) {
						sum = 0.00;
					}
					sum = sum + (list.get(i).getPrice()) * (list.get(i).getCount());
					BigDecimal b1 = new BigDecimal(Double.toString(sum));
					BigDecimal b2 = new BigDecimal(Double.toString(1));
					f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
				allprice.setText("总计:￥" + f1);
			} else if (flag == false) {
				sum = 0.00;
				allprice.setText("总计:￥" + sum);
			}
			// shop_lv_view.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			break;
		case R.id.shop_editbutton:
			shop_editbutton_text = shop_editbutton.getText().toString();
			shop_bayall_text = shop_bayall.getText().toString();
			if (shop_editbutton_text.equals("编辑")) {
				shop_editbutton.setText("完成");
				rela_allprice.setVisibility(View.GONE);
				if (shop_bayall_text.equals("结算")) {
					shop_bayall.setText("删除");
				}
			} else if (shop_editbutton_text.equals("完成")) {
				shop_editbutton.setText("编辑");
				rela_allprice.setVisibility(View.VISIBLE);
				if (shop_bayall_text.equals("删除")) {
					shop_bayall.setText("结算");
				}
			}
			break;
		case R.id.shop_bayall:
			shop_bayall_text = shop_bayall.getText().toString();
			if (shop_bayall_text.equals("删除")) {
				DbUtils db = new DbUtils(getActivity());
				for (int i = 0; i < goodid_list.size(); i++) {
					db.delete(goodid_list.get(i));
					goodid_list.remove(i);
					sum = sum - (list.get(i).getPrice()) * (list.get(i).getCount());
					adapter.notifyDataSetChanged();
				}
				allprice.setText("总计:￥" + sum);
				Toast.makeText(getActivity(), "删除成功", 0).show();
			} else if (shop_bayall_text.equals("结算")) {
				if (sum > 0) {
					// 提交订单点击事件
					if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
						new AlertDialog.Builder(getActivity()).setTitle("警告")
								.setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
								.setPositiveButton("确定", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialoginterface, int i) {
										//
										getActivity().finish();
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
							PayTask alipay = new PayTask(getActivity());
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
				} else {
					Toast.makeText(getActivity(), "请选择商品后结算", 0).show();
				}
			}

			break;
		default:
			break;
		}
	}

	// 适配器
	class shop_lv_adapter extends BaseAdapter {
		private Context context;
		private List<Goods> list;
		private LinkedList<Boolean> linkedList = new LinkedList<Boolean>();
		private ImageLoader loader;
		private List<String> goodid_list;

		public shop_lv_adapter(Context context, List<Goods> list, List<String> goodid_list) {
			super();
			this.context = context;
			this.list = list;
			this.goodid_list = goodid_list;
			// 初始化
			for (int i = 0; i < list.size(); i++) {
				getSelect().add(false);
			}
		}

		private List<Boolean> getSelect() {
			return linkedList;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder vh;
			if (convertView == null) {
				convertView = convertView.inflate(context, R.layout.shop_lv_item, null);
				vh = new ViewHolder();
				vh.shop_checkbox = (CheckBox) convertView.findViewById(R.id.shop_checkbox);
				vh.shop_goodimg = (ImageView) convertView.findViewById(R.id.shop_goodimg);
				vh.shop_goodname = (TextView) convertView.findViewById(R.id.shop_goodname);
				vh.shop_price = (TextView) convertView.findViewById(R.id.shop_price);
				vh.shop_count = (TextView) convertView.findViewById(R.id.shop_count);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			loader = ImageLoader.getInstance();
			loader.displayImage(list.get(position).getUrl(), vh.shop_goodimg);
			vh.shop_goodname.setText(list.get(position).getGoodname() + "");
			vh.shop_count.setText("数量:" + list.get(position).getCount() + "");
			vh.shop_price.setText("￥" + list.get(position).getPrice());

			vh.shop_checkbox.setChecked(linkedList.get(position));
			// 不能用onCheckChangedListner()复用的时候
			vh.shop_checkbox.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					linkedList.set(position, !linkedList.get(position));
					if (linkedList.contains(false)) {
						checkall.setChecked(false);
					} else {
						checkall.setChecked(true);
					}
					if (vh.shop_checkbox.isChecked() == true) {
						sum = sum + (list.get(position).getPrice()) * (list.get(position).getCount());
						goodid_list.add(list.get(position).getGoodid());
					} else if (vh.shop_checkbox.isChecked() == false) {
						sum = sum - (list.get(position).getPrice()) * (list.get(position).getCount());
						goodid_list.remove(list.get(position).getGoodid());
					}
					BigDecimal b1 = new BigDecimal(Double.toString(sum));
					BigDecimal b2 = new BigDecimal(Double.toString(1));
					double f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
					allprice.setText("总计:￥" + f1);
					// double
					// sum=(list.get(position).getPrice())*(list.get(position).getCount());
					// Toast.makeText(context, "点击了"+position+"的checkbox",
					// 0).show();
					// 刷新
					notifyDataSetChanged();
				}
			});
			return convertView;
		}

		class ViewHolder {
			CheckBox shop_checkbox;
			ImageView shop_goodimg;
			TextView shop_goodname, shop_price, shop_count;
		}
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
}
