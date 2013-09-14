package com.joyrill.app.adapter.music;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.joyrill.app.R;
import com.joyrill.app.model.Music;

public class MusicItemAdapter extends BaseAdapter {

	private List<Music> mdata;
	private LayoutInflater mInflater;
	private int currentPlaying = -1;

	public MusicItemAdapter(Context context, List<Music> data) {
		this.mInflater = LayoutInflater.from(context);
		this.mdata = data;
	}

	@Override
	public int getCount() {
		return this.mdata.size();
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
		MusicViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.music_item, null);
			holder = new MusicViewHolder();
			holder.id = (TextView) convertView.findViewById(R.id.musicId);
			holder.name = (TextView) convertView.findViewById(R.id.musicName);
			holder.author = (TextView) convertView
					.findViewById(R.id.musicAuthor);
			convertView.setTag(holder);
		} else {
			holder = (MusicViewHolder) convertView.getTag();
		}

		Music music = this.mdata.get(position);
		holder.id.setText(music.getId() + "");
		holder.name.setText(music.getName());
		// holder.author.setText(music.getAuthor());
		if (this.currentPlaying == position) {
			convertView.setBackgroundColor(Color.GRAY);
		} else {
			convertView.setBackgroundColor(Color.TRANSPARENT);
		}
		return convertView;
	}

	public void setSelectItem(int position) {
		this.currentPlaying = position;
		this.notifyDataSetInvalidated();
	}

}
