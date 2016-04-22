package com.tangz.cugbatp.myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tangz.cugbatp.R;
import com.tangz.cugbatp.activity.DYYActivity;
import com.tangz.cugbatp.activity.WMActivity;

public class DaerFragment extends BaseFragment implements OnClickListener {

	private LinearLayout dyyLL;
	private LinearLayout wmLL;
	private Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_content_tab02, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dyyLL = (LinearLayout) getView().findViewById(R.id.id_dyy);
		wmLL = (LinearLayout) getView().findViewById(R.id.id_wm);
		dyyLL.setOnClickListener(this);
		wmLL.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_dyy:
			intent = new Intent(getActivity(), DYYActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		case R.id.id_wm:
			intent = new Intent(getActivity(), WMActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		default:
			break;
		}
	}
}
