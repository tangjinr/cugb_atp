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
		// 进度条绑定最大亮度，255是最大亮度
		ldSK.setMax(255);
		// 取得当前亮度
		int normal = Settings.System.getInt(getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, 255);
		// 进度条绑定当前亮度
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
				// 取得当前进度
				int tmpInt = seekBar.getProgress();

				// 当进度小于80时，设置成80，防止太黑看不见的后果。
				if (tmpInt < 80) {
					tmpInt = 80;
				}

				// 根据当前进度改变亮度
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
