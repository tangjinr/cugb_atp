package com.tangz.cugbatp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.tangz.cugbatp.R;

public class XXJJ3Activity extends Activity implements OnClickListener {

	private ImageView mIV;
	private TextView mTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.xxjj3);
		mIV = (ImageView) findViewById(R.id.id_fh);
		mIV.setOnClickListener(this);
		mTV = (TextView) findViewById(R.id.id_top_title);
		mTV.setText("ÖªÃûÐ£ÓÑ");
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
		Intent intent = new Intent(XXJJ3Activity.this, XXJJActivity.class);
		startActivity(intent);
		this.finish();
	}
}
