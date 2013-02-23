package com.android.BC_ver02;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BankNameAdapter extends BaseAdapter {

	private LayoutInflater myInflater;
	private ArrayList<ItemListviewBank_Main> listItem;
	private Context context;
//	static class ViewHolder{
//		TextView id,name;
//		Button btnCal,btnInfo;
//	}
	
	public BankNameAdapter(Context context, ArrayList<ItemListviewBank_Main> listItem) {
		super();
		this.myInflater = LayoutInflater.from(context);
		this.listItem = listItem;
		this.context = context;
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
			holder.btnCal = (Button) convertView.findViewById(R.id.btnCal_Itemlistviewbank);
			holder.btnInfo = (Button) convertView.findViewById(R.id.btnInfo_Itemlistviewbank);
			
			convertView.setTag(holder);// quan trong
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position < listItem.size()){
			ItemListviewBank_Main item = listItem.get(position);					
			holder.name.setText(item.name);
			holder.id.setText(item.id);
			btnCalOnclickListener listener = new btnCalOnclickListener(this.context, item.id, CalculatorActivity.class);
			btnCalOnclickListener listener2 = new btnCalOnclickListener(this.context, item.id, InfoActivity.class);
			holder.btnCal.setOnClickListener(listener);
			holder.btnInfo.setOnClickListener(listener2);
		}
		return convertView;
	}

}
