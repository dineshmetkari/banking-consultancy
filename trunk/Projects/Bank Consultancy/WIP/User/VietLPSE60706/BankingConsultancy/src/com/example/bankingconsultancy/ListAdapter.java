package com.example.bankingconsultancy;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Item> listItem;
	
	
	
	static class ViewHolder
	{
		TextView info;
		TextView content;
	}
	
	
	
	public ListAdapter(Context context, ArrayList<Item> listItem)
	{
		mInflater = LayoutInflater.from(context);
		this.listItem = listItem;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.item, null);// doi item la xong
			holder = new ViewHolder();
			holder.info = (TextView)convertView.findViewById(R.id.txtInfo);
			holder.content = (TextView)convertView.findViewById(R.id.txtContent);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (position < listItem.size())
		{
			Item item = listItem.get(position);
			holder.info.setText(item.info);
			holder.content.setText(item.content);
		}
		
		return convertView;
	}

}
