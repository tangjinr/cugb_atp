package com.tangz.cugbatp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.tangz.cugbatp.MainActivity;
import com.tangz.cugbatp.R;
import com.tangz.cugbatp.animation.RotateDownPageTransformer;

public class XYFGActivity extends Activity implements OnClickListener {

	private Intent intent = null;
	private ImageView mIV;
	private ImageView imageview = null;
	private ViewPager mViewPager;
	private int[] mImgIds = new int[] { R.drawable.i1, R.drawable.i2,
			R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6};
	private List<ImageView> mImages = new ArrayList<ImageView>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xyfg);
		mIV = (ImageView) findViewById(R.id.id_fh);
		mIV.setOnClickListener(this);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		// 为ViewPager添加动画效果(3.0以上)
		// mViewPager.setPageTransformer(true, new DepthPageTransformer());
		//mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
		mViewPager.setOffscreenPageLimit(3);
		mViewPager.setAdapter(new PagerAdapter() {

			@Override
			public Object instantiateItem(ViewGroup container, int position) {

				imageview = new ImageView(XYFGActivity.this);
				imageview.setImageResource(mImgIds[position]);
				imageview.setScaleType(ScaleType.CENTER_CROP);
				container.addView(imageview);
				mImages.add(imageview);
				return imageview;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mImages.get(position));
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public int getCount() {
				return mImgIds.length;
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
			goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void goBack() {
		intent = new Intent(XYFGActivity.this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}
}
