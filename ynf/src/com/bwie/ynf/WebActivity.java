package com.bwie.ynf;

import com.bwie.ynf.R;
import com.bwie.ynf.R.id;
import com.bwie.ynf.R.layout;
import com.bwie.ynf.R.string;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class WebActivity extends Activity {

	private WebView mywebview;
	private ProgressDialog progressDialog;
	private ImageView web_back;
	private ImageView web_shark;
	private TextView web_title;
	private String url;
	private String webtitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_web);
		infoview();
		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		webtitle = intent.getStringExtra("webtitle");
		web_title.setText(webtitle);
		mywebview.getSettings().setJavaScriptEnabled(true);
		mywebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		mywebview.loadUrl(url);
		if (mywebview != null) {
			mywebview.setWebViewClient(new WebViewClient() {

				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					progressDialog.dismiss();
				}
			});
			initVIew(url);
		}
		web_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		web_shark.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showShare();
			}
		});
	}

	private void initVIew(String uri) {
		if (mywebview != null) {
			progressDialog = ProgressDialog.show(WebActivity.this, "����", "���ڼ���,���Ժ�", true);
			mywebview.loadUrl(uri);
		}
	}

	public void infoview() {
		mywebview = (WebView) findViewById(R.id.mywebview);
		web_back = (ImageView) findViewById(R.id.web_back);
		web_shark = (ImageView) findViewById(R.id.web_shark);
		web_title = (TextView) findViewById(R.id.web_title);
	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle(webtitle);
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl(webtitle);
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText(null);
		// ��������ͼƬ������΢����������ͼƬ��Ҫͨ����˺�����߼�д��ӿڣ�������ע�͵���������΢��
		oks.setImageUrl(null);
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		// oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl("http://sharesdk.cn");
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite(getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl(url);
		// ��������GUI
		oks.show(this);
	}
}
