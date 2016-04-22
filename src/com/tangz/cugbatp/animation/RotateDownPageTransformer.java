package com.tangz.cugbatp.animation;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class RotateDownPageTransformer implements PageTransformer {

	private static final float MAX_ROTATE = 20f;
	private float mRot;

	// ��1ҳ�Ƕȱ仯 0 ~ -20 �� ��2ҳ�Ƕȱ仯 20 ~ 0
	@SuppressLint("NewApi")
	@Override
	public void transformPage(View view, float position) {

		int pageWidth = view.getWidth();

		if (position < -1) {
			// [-Infinity,-1)���ɼ�
			view.setRotation(0);

		} else if (position <= 0) {
			// ��1ҳ��0 ~ -20
			mRot = position * MAX_ROTATE;
			view.setPivotX(pageWidth / 2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(mRot);

		} else if (position <= 1) {
			// ��2ҳ��20 ~ 0
			mRot = position * MAX_ROTATE;
			view.setPivotX(pageWidth / 2);
			view.setPivotY(view.getMeasuredHeight());
			view.setRotation(mRot);

		} else {
			// (1,+Infinity]���ɼ�
			view.setRotation(0);
		}

	}

}
