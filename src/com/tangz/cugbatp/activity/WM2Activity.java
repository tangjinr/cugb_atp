package com.tangz.cugbatp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tangz.cugbatp.R;

public class WM2Activity extends Activity implements OnClickListener {

	private ImageView mIV;
	private TextView mTV;
	private WebView mWV;
	private ProgressBar mPB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wm_item);
		mIV = (ImageView) findViewById(R.id.id_fh);
		mIV.setOnClickListener(this);
		mTV = (TextView) findViewById(R.id.id_top_title);
		mTV.setText("美团外卖");
		mPB = (ProgressBar) findViewById(R.id.id_wm_pb);
		mWV = (WebView) findViewById(R.id.id_wm_webView);
		webViewInit();
	}

	private void webViewInit() {
		mWV.loadUrl("http://waimai.meituan.com/");
		WebSettings settings = mWV.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setAllowFileAccess(true);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		mWV.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		mWV.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				// 页面下载完毕,却不代表页面渲染完毕显示出来
				// WebChromeClient中progress==100时也是一样
				if (mWV.getContentHeight() != 0) {
					// 这个时候网页才显示
				}
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
		
		mWV.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if (newProgress == 0) {
					mPB.setVisibility(View.VISIBLE);
				}
				mPB.setProgress(newProgress);
				mPB.postInvalidate();
				if (newProgress == 100) {
					mPB.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_fh:
			goBack();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mWV.canGoBack()) {
				mWV.goBack();// 返回上一页面
				return true;
			} else {
				goBack();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private void goBack() {
		Intent intent = new Intent(WM2Activity.this, WMActivity.class);
		startActivity(intent);
		this.finish();
	}
}
