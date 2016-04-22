package com.tangz.cugbatp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.tangz.cugbatp.MainActivity;
import com.tangz.cugbatp.R;

public class SZActivity extends Activity implements OnClickListener {

	private ImageView mIV;
	private SeekBar ldSK;
	private SeekBar ylSK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sz);
		mIV = (ImageView) findViewById(R.id.id_fh);
		ldSK = (SeekBar) findViewById(R.id.ld);
		ldinit();
		ylSK = (SeekBar) findViewById(R.id.yl);
		mIV.setOnClickListener(this);
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
		Intent intent = new Intent(SZActivity.this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	private void ldinit() {
		// ��������������ȣ�255���������
		ldSK.setMax(255);
		// ȡ�õ�ǰ����
		int normal = Settings.System.getInt(getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, 255);
		// �������󶨵�ǰ����
		ldSK.setProgress(normal);

		ldSK.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// ȡ�õ�ǰ����
				int tmpInt = seekBar.getProgress();

				// ������С��80ʱ�����ó�80����ֹ̫�ڿ������ĺ����
				if (tmpInt < 80) {
					tmpInt = 80;
				}

				// ���ݵ�ǰ���ȸı�����
				Settings.System.putInt(getContentResolver(),
						Settings.System.SCREEN_BRIGHTNESS, tmpInt);
				tmpInt = Settings.System.getInt(getContentResolver(),
						Settings.System.SCREEN_BRIGHTNESS, -1);
				WindowManager.LayoutParams wl = getWindow().getAttributes();

				float tmpFloat = (float) tmpInt / 255;
				if (tmpFloat > 0 && tmpFloat <= 1) {
					wl.screenBrightness = tmpFloat;
				}
				getWindow().setAttributes(wl);
			}
		});
	}
}
