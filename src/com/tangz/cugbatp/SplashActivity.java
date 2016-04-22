package com.tangz.cugbatp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashActivity extends Activity implements AnimationListener {

	private LinearLayout mSplashLL;
	private Animation animation;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		// 去掉标题和全屏，这里在设置了activity主题android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mSplashLL = (LinearLayout) findViewById(R.id.id_splash);
		animation = AnimationUtils.loadAnimation(this, R.anim.logo_normal);
		// animation = AnimationUtils.loadAnimation(this, R.anim.logo_alpha);
		// animation = AnimationUtils.loadAnimation(this, R.anim.logo_scale);
		// animation = AnimationUtils.loadAnimation(this, R.anim.logo_translate);
		// animation = AnimationUtils.loadAnimation(this, R.anim.logo_rotate);

		animation.setAnimationListener(this);
		mSplashLL.setAnimation(animation);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		animation = null;
		this.finish();
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
	}
}