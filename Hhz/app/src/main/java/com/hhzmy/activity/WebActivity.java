package com.hhzmy.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.hhzmy.hhz.R;


public class WebActivity extends Activity {

    private WebView mywebview;
    private ProgressDialog progressDialog;
    private ImageView web_shark;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        infoview();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
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
    }

    private void initVIew(String uri) {
        if (mywebview != null) {
            progressDialog = ProgressDialog.show(WebActivity.this, "加载中", "正在加载，请稍后...", true);
            mywebview.loadUrl(uri);
        }
    }

    public void infoview() {
        mywebview = (WebView) findViewById(R.id.mywebview);
    }

}
