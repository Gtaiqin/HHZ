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
			progressDialog = ProgressDialog.show(WebActivity.this, "加载", "正在加载,请稍候！", true);
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
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(webtitle);
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl(webtitle);
		// text是分享文本，所有平台都需要这个字段
		oks.setText(null);
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl(null);
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl(url);
		// 启动分享GUI
		oks.show(this);
	}
}
