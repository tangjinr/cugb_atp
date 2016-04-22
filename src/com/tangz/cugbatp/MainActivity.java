package com.tangz.cugbatp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tangz.cugbatp.activity.MZSMActivity;
import com.tangz.cugbatp.activity.SZActivity;
import com.tangz.cugbatp.activity.YJFKActivity;
import com.tangz.cugbatp.myFragment.DaSanFragment;
import com.tangz.cugbatp.myFragment.DaerFragment;
import com.tangz.cugbatp.myFragment.DayiFragment;
import com.tangz.cugbatp.slidingMenu.SlidingMenu;
import com.tangz.cugbatp.unit.Helper;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private Intent intent = null;

	// ��������
	private TextView mTitleTV;
	// �л��˵�ͼ��
	private ImageView mtmIV;
	// Tab
	private LinearLayout mDaYiLL;
	private LinearLayout mDaErLL;
	private LinearLayout mDaSanLL;
	// ImageButton
	private ImageButton mDaYiImg;
	private ImageButton mDaErImg;
	private ImageButton mDaSanImg;
	// Fragment
	private Fragment mDaYiTab;
	private Fragment mDaErTab;
	private Fragment mDaSanTab;

	private SlidingMenu mLeftMenu;
	// ������е�ÿ��
	private RelativeLayout mSZRL;
	private RelativeLayout mMZSMRL;
	private RelativeLayout mYJFKRL;
	private RelativeLayout mTCRL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
		initView();
		initEvents();
		setSelect(Helper.num);
	}

	private void initView() {
		mtmIV = (ImageView) findViewById(R.id.id_img_toggle_menu);
		mTitleTV = (TextView) findViewById(R.id.id_top_title);
		mDaYiLL = (LinearLayout) findViewById(R.id.id_ll_dayi);
		mDaErLL = (LinearLayout) findViewById(R.id.id_ll_daer);
		mDaSanLL = (LinearLayout) findViewById(R.id.id_ll_dasan);
		mDaYiImg = (ImageButton) findViewById(R.id.id_img_dayi);
		mDaErImg = (ImageButton) findViewById(R.id.id_img_daer);
		mDaSanImg = (ImageButton) findViewById(R.id.id_img_dasan);
		mSZRL = (RelativeLayout) findViewById(R.id.id_sz);
		mMZSMRL = (RelativeLayout) findViewById(R.id.id_mzsm);
		mYJFKRL = (RelativeLayout) findViewById(R.id.id_yjfk);
		mTCRL = (RelativeLayout) findViewById(R.id.id_tc);
	}

	private void initEvents() {
		mtmIV.setOnClickListener(this);
		mDaYiLL.setOnClickListener(this);
		mDaErLL.setOnClickListener(this);
		mDaSanLL.setOnClickListener(this);
		mSZRL.setOnClickListener(this);
		mMZSMRL.setOnClickListener(this);
		mYJFKRL.setOnClickListener(this);
		mTCRL.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int x = -1;
		switch (v.getId()) {
		case R.id.id_ll_dayi:
			x = 0;
			break;
		case R.id.id_ll_daer:
			x = 1;
			break;
		case R.id.id_ll_dasan:
			x = 2;
			break;
		case R.id.id_img_toggle_menu:
			toggleMenu();
			break;
		case R.id.id_sz:
			intent = new Intent(MainActivity.this, SZActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.id_mzsm:
			intent = new Intent(MainActivity.this, MZSMActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.id_yjfk:
			intent = new Intent(MainActivity.this, YJFKActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.id_tc:
			exit();
			break;
		default:
			break;
		}
		if (x != -1) {
			Helper.num = x;
			setSelect(x);
		}

	}

	// �˷�������ʵ�ִ򿪹رղ˵������簴ť��click�������Ե��ô˷���
	private void toggleMenu() {
		mLeftMenu.toggle();
	}

	// �����е�ͼƬ�л�Ϊ��ɫ
	private void resetImg() {
		mDaYiImg.setImageResource(R.drawable.dayi1);
		mDaErImg.setImageResource(R.drawable.daer1);
		mDaSanImg.setImageResource(R.drawable.dasan1);
	}

	private void setSelect(int i) {

		resetImg();
		// ����activity��fragment��ͨ������activity��getFragmentManager()ȡ������ʵ��
		FragmentManager fm = getSupportFragmentManager();
		// ��fragment�������,�Ƴ�,�滻,�Լ�ִ����������
		FragmentTransaction transaction = fm.beginTransaction();
		// ��fragmentȫ������
		hideFragment(transaction);
		
		if(Helper.once) {
			mDaErTab = new DaerFragment();
			transaction.add(R.id.id_content, mDaErTab);
			mDaSanTab = new DaSanFragment();
			transaction.add(R.id.id_content, mDaSanTab);
			mDaYiTab = new DayiFragment();
			transaction.add(R.id.id_content, mDaYiTab);
			Helper.once = false;
		}

		// ���ñ��⣬��ͼƬ��Ϊ��ɫ��������������
		switch (i) {
		case 0:
			if (mDaYiTab == null) {
				mDaYiTab = new DayiFragment();
				transaction.add(R.id.id_content, mDaYiTab);
			} else {
				transaction.show(mDaYiTab);
			}
			mTitleTV.setText(this.getString(R.string.dayi));
			mDaYiImg.setImageResource(R.drawable.dayi2);
			break;
		case 1:
			if (mDaErTab == null) {
				mDaErTab = new DaerFragment();
				transaction.add(R.id.id_content, mDaErTab);
			} else {
				transaction.show(mDaErTab);
			}
			mTitleTV.setText(this.getString(R.string.daer));
			mDaErImg.setImageResource(R.drawable.daer2);
			break;
		case 2:
			if (mDaSanTab == null) {
				mDaSanTab = new DaSanFragment();
				transaction.add(R.id.id_content, mDaSanTab);
			} else {
				transaction.show(mDaSanTab);
			}
			mTitleTV.setText(this.getString(R.string.dasan));
			mDaSanImg.setImageResource(R.drawable.dasan2);
			break;
		default:
			break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		if (mDaYiTab != null) {
			transaction.hide(mDaYiTab);
		}
		if (mDaErTab != null) {
			transaction.hide(mDaErTab);
		}
		if (mDaSanTab != null) {
			transaction.hide(mDaSanTab);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		new AlertDialog.Builder(this).setTitle("ȷ���˳���")
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				})
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.this.finish();
						System.exit(0);
					}
				}).show();
	}
}
