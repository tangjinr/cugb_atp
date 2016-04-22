package com.tangz.cugbatp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tangz.cugbatp.MainActivity;
import com.tangz.cugbatp.R;

public class DYYActivity extends Activity implements OnClickListener {

	private Intent intent = null;
	private ImageView mIV;
	private ListView mListView;
	private SimpleAdapter mSimpleAdapter;
	private List<Map<String, Object>> mDatalist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dyy);
		mIV = (ImageView) findViewById(R.id.id_fh);
		mIV.setOnClickListener(this);

		mListView = (ListView) findViewById(R.id.id_dyyLV);
		// ͨ�׵�˵��һ������������һ��
		// 1���½�������
		// 2����������������Դ
		// context:������
		// data�� ����Դ List<? extends Map<String, ?>>
		// һ��Map����ɵ�List����,ÿһ��Map��ӦListView�е�һ��
		// ÿһ��Map����-ֵ�ԣ��еļ��������from����ָ���ļ�
		// resource���б��ÿһ��Ĳ����ļ�ID
		// from��Map�еļ�ֵ��
		// to����������ͼ�е�ID��R.layout.item�пؼ���ID������from�ɶ�Ӧ��ϵ
		mDatalist = new ArrayList<Map<String, Object>>();
		mSimpleAdapter = new myAdapter(this, getData(),
				R.layout.content_list_item, new String[] { "img", "name", "address" },
				new int[] { R.id.id_dyy_img, R.id.id_name, R.id.id_address }) {
		};
		// 3��(ListView)����������
		mListView.setAdapter(mSimpleAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					intent = new Intent(DYYActivity.this, DYY1Activity.class);
					startActivity(intent);
					((myAdapter) mSimpleAdapter).getActivity().finish();
					break;
				case 1:
					intent = new Intent(DYYActivity.this, DYY2Activity.class);
					startActivity(intent);
					((myAdapter) mSimpleAdapter).getActivity().finish();
					break;
				case 2:
					intent = new Intent(DYYActivity.this, DYY3Activity.class);
					startActivity(intent);
					((myAdapter) mSimpleAdapter).getActivity().finish();
					break;
				default:
					break;
				}
			}
		});
	}

	private List<Map<String, Object>> getData() {
		for (int i = 0; i <= 2; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			switch (i) {
			case 0:
				map.put("img", R.drawable.dyy1);
				map.put("name", this.getString(R.string.name1));
				map.put("address", this.getString(R.string.address1));
				break;
			case 1:
				map.put("img", R.drawable.dyy2);
				map.put("name", this.getString(R.string.name2));
				map.put("address", this.getString(R.string.address2));
				break;
			case 2:
				map.put("img", R.drawable.dyy3);
				map.put("name", this.getString(R.string.name3));
				map.put("address", this.getString(R.string.address3));
				break;
			default:
				break;
			}
			mDatalist.add(map);
		}
		return mDatalist;
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
		intent = new Intent(DYYActivity.this, MainActivity.class);
		startActivity(intent);
		this.finish();
	}

	private class myAdapter extends SimpleAdapter {

		private DYYActivity context;
		public myAdapter(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			this.context = (DYYActivity) context;
		}
		
		public DYYActivity getActivity() {
			return context;
		}
	}

}
