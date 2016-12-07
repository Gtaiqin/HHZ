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
	// Pid �̻���֧������id
	public static final String PARTNER = "2088901305856832";
	// �̻��տ��˺�
	public static final String SELLER = "8@qdbaiu.com";
	// �̻�˽Կ��pkcs8��ʽ
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
				 * ͬ�����صĽ��������õ�����˽�����֤����֤�Ĺ����뿴https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) �����̻������첽֪ͨ
				 */
				String resultInfo = payResult.getResult();// ͬ��������Ҫ��֤����Ϣ

				String resultStatus = payResult.getResultStatus();
				// �ж�resultStatus Ϊ��9000�������֧���ɹ�������״̬�������ɲο��ӿ��ĵ�
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(getActivity(), "֧���ɹ�", Toast.LENGTH_SHORT).show();
				} else {
					// �ж�resultStatus Ϊ��"9000"��������֧��ʧ��
					// "8000"����֧�������Ϊ֧������ԭ�����ϵͳԭ���ڵȴ�֧�����ȷ�ϣ����ս����Ƿ�ɹ��Է�����첽֪ͨΪ׼��С����״̬��
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(getActivity(), "֧�����ȷ����", Toast.LENGTH_SHORT).show();

					} else {
						// ����ֵ�Ϳ����ж�Ϊ֧��ʧ�ܣ������û�����ȡ��֧��������ϵͳ���صĴ���
						Toast.makeText(getActivity(), "֧��ʧ��", Toast.LENGTH_SHORT).show();

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
		// ˢ�¹��ﳵ����
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

	// չʾ���ﳵ�������ݿ��е���������
	public void showdata() {
		// list = new ArrayList<Goods>();
		// ��ȡ���ݿ�
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
				allprice.setText("�ܼ�:��" + f1);
			} else if (flag == false) {
				sum = 0.00;
				allprice.setText("�ܼ�:��" + sum);
			}
			// shop_lv_view.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			break;
		case R.id.shop_editbutton:
			shop_editbutton_text = shop_editbutton.getText().toString();
			shop_bayall_text = shop_bayall.getText().toString();
			if (shop_editbutton_text.equals("�༭")) {
				shop_editbutton.setText("���");
				rela_allprice.setVisibility(View.GONE);
				if (shop_bayall_text.equals("����")) {
					shop_bayall.setText("ɾ��");
				}
			} else if (shop_editbutton_text.equals("���")) {
				shop_editbutton.setText("�༭");
				rela_allprice.setVisibility(View.VISIBLE);
				if (shop_bayall_text.equals("ɾ��")) {
					shop_bayall.setText("����");
				}
			}
			break;
		case R.id.shop_bayall:
			shop_bayall_text = shop_bayall.getText().toString();
			if (shop_bayall_text.equals("ɾ��")) {
				DbUtils db = new DbUtils(getActivity());
				for (int i = 0; i < goodid_list.size(); i++) {
					db.delete(goodid_list.get(i));
					goodid_list.remove(i);
					sum = sum - (list.get(i).getPrice()) * (list.get(i).getCount());
					adapter.notifyDataSetChanged();
				}
				allprice.setText("�ܼ�:��" + sum);
				Toast.makeText(getActivity(), "ɾ���ɹ�", 0).show();
			} else if (shop_bayall_text.equals("����")) {
				if (sum > 0) {
					// �ύ��������¼�
					if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
						new AlertDialog.Builder(getActivity()).setTitle("����")
								.setMessage("��Ҫ����PARTNER | RSA_PRIVATE| SELLER")
								.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialoginterface, int i) {
										//
										getActivity().finish();
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
							PayTask alipay = new PayTask(getActivity());
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
				} else {
					Toast.makeText(getActivity(), "��ѡ����Ʒ�����", 0).show();
				}
			}

			break;
		default:
			break;
		}
	}

	// ������
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
			// ��ʼ��
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
			vh.shop_count.setText("����:" + list.get(position).getCount() + "");
			vh.shop_price.setText("��" + list.get(position).getPrice());

			vh.shop_checkbox.setChecked(linkedList.get(position));
			// ������onCheckChangedListner()���õ�ʱ��
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
					allprice.setText("�ܼ�:��" + f1);
					// double
					// sum=(list.get(position).getPrice())*(list.get(position).getCount());
					// Toast.makeText(context, "�����"+position+"��checkbox",
					// 0).show();
					// ˢ��
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
}
