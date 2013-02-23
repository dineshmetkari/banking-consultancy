package com.android.BC_ver02;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class InfoActivity extends Activity {
	
	private ListView lvInfo;
	private BankInfoAdapter adapter;
	
	private String id;
	private ArrayList<String> info;
	
	DBHelper mDB;
    Cursor mCursor;

	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);		
        
		lvInfo = (ListView) findViewById(R.id.lvInfo);
		
		mDB = new DBHelper(this);
        mDB.open();
        
                
        id = getIntent().getStringExtra("id");
        info = getBankInfo(id);
        
        adapter = new BankInfoAdapter(getApplicationContext(),info);        
        lvInfo.setAdapter(adapter);
	}
	
	private ArrayList<String> getBankInfo(String id) {

		mCursor = mDB.getBankInfo(id);
		ArrayList<String> list = new ArrayList<String>();
		try {
			if(mCursor.moveToFirst()){				
				list.add("Tên rút gọn");
				String s = mCursor.getString(1);
				list.add(s);
				list.add("Tên đầy đủ");
				s = mCursor.getString(2);
				list.add(s);
				list.add("Địa chỉ");
				s = mCursor.getString(3);
				list.add(s);
				list.add("Số điện thoại");
				s = mCursor.getString(4);
				list.add(s);
				list.add("Số Fax");
				s = mCursor.getString(5);
				list.add(s);
				list.add("Website");
				s = mCursor.getString(6);
				list.add(s);
				list.add("Giới thiệu chung");
				s = mCursor.getString(7);
				list.add(s);
				return list;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mCursor.close();
		}

		return null;
	}
}
