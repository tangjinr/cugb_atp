package com.tangz.cugbatp.myFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tangz.cugbatp.R;
import com.tangz.cugbatp.activity.XXJJActivity;
import com.tangz.cugbatp.activity.XYFGActivity;

public class DayiFragment extends BaseFragment implements OnClickListener {

	private LinearLayout xxjjLL;
	private LinearLayout xyfgLL;
	private Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.main_content_tab01, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		xxjjLL = (LinearLayout) getView().findViewById(R.id.id_xxjj);
		xyfgLL = (LinearLayout) getView().findViewById(R.id.id_xyfg);
		xxjjLL.setOnClickListener(this);
		xyfgLL.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_xxjj:
			intent = new Intent(getActivity(), XXJJActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		case R.id.id_xyfg:
			intent = new Intent(getActivity(), XYFGActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		default:
			break;
		}
	}
}
