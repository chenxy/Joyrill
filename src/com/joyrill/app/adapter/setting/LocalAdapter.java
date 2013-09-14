package com.joyrill.app.adapter.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joyrill.app.R;

public class LocalAdapter extends BaseAdapter {

	private String[] mdata;
	private LayoutInflater mInflater;
	private int current = -1;
	public LocalAdapter(Context context, String[] data,String name) {
		this.mInflater = LayoutInflater.from(context);
		this.mdata = data;
		if(name.equalsIgnoreCase("en")){
			this.current = 0;
		}else{
			this.current =1;
		}
	}

	@Override
	public int getCount() {
		return this.mdata.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.local_item, null);
		TextView localId = (TextView) convertView.findViewById(R.id.localId);
		ImageView localImage = (ImageView) convertView.findViewById(R.id.localImage);
		localId.setText(mdata[position]);
		if(current == position){
			localImage.setBackgroundResource(R.drawable.setting_language_check);
		}
		return convertView;
	}
	public void setSelectItem(int id){
		if(current != id){
			this.current = id;
			this.notifyDataSetInvalidated();
		}
	}
}
