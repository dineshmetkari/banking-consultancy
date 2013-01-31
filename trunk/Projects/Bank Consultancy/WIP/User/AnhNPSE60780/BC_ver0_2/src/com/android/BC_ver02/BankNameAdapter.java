package com.android.BC_ver02;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BankNameAdapter extends BaseAdapter {

	private LayoutInflater myInflater;
	private ArrayList<ItemListviewBank_Main> listItem;
	
	static class ViewHolder{
		TextView id,name;
	}
	
	public BankNameAdapter(Context context, ArrayList<ItemListviewBank_Main> listItem) {
		super();
		this.myInflater = LayoutInflater.from(context);
		this.listItem = listItem;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (listItem!=null)?listItem.size():0;
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
		ViewHolder holder;
		
		if(convertView == null){
			convertView = myInflater.inflate(R.layout.itemlistviewbank, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.txtBankNameItemlistviewbank);
			holder.id = (TextView) convertView.findViewById(R.id.txtIDItemlistviewbank);
			convertView.setTag(holder);// quan trong
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position < listItem.size()){
			ItemListviewBank_Main item = listItem.get(position);					
			holder.name.setText(item.name);
			holder.id.setText(item.id);			
		}
		return convertView;
	}

}
