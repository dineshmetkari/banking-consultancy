package com.android.BC_ver02;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BankInfoAdapter extends BaseAdapter {

	private static final int TYPE_ITEM = 0;
	private static final int TYPE_HEADER = 1;
	
	private LayoutInflater myInflater;
	private ArrayList<String> listItem;

	public BankInfoAdapter(Context context, ArrayList<String> listItem) {
		super();
		this.myInflater = LayoutInflater.from(context);
		this.listItem = listItem;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return position%2==0?TYPE_HEADER:TYPE_ITEM;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (listItem != null) ? listItem.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub		
		BankInfoHolder holder;
		
		if (convertView == null) {
			convertView = myInflater.inflate(R.layout.itemlistviewinfo, null);
			holder = new BankInfoHolder();
			holder.tvInfo = (TextView) convertView
					.findViewById(R.id.txtInfo_itemlistviewinfo);
			
			int type = getItemViewType(position);
			switch(type){
				case TYPE_HEADER:
					holder.tvInfo.setBackgroundColor(Color.GRAY);
					break;
				case TYPE_ITEM:
					holder.tvInfo.setBackgroundColor(Color.BLACK);
					break;
			}
			
			convertView.setTag(holder);// quan trong
		} else {
			holder = (BankInfoHolder) convertView.getTag();
		}

		if (position < listItem.size()) {
			String info = listItem.get(position);
			holder.tvInfo.setText(info);			
		}
		return convertView;
	}

}
