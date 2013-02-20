package com.example.bankingconsultancy;

import java.util.ArrayList;


import com.example.bankingconsultancy.*;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	ListView listDetail;
	ListAdapter adapter;
	ArrayList<Item> listItem;
	
	DBHelper mDB;
    Cursor mCursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.listDetail = (ListView)findViewById(R.id.listDetail);
		mDB = new DBHelper(this);
		mDB.open();
		
		this.listItem = new ArrayList<Item>();
		this.listItem = getData();
		
		adapter = new ListAdapter(getApplicationContext(),listItem);        
	    listDetail.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	private ArrayList<Item> getData(){
		ArrayList<Item> items = new ArrayList<Item>();
		
		mCursor = mDB.getAllItems();
		
		try{
			mCursor.moveToFirst();
			while(!mCursor.isAfterLast()){
				String id = mCursor.getString(0);
				String shortName = mCursor.getString(1);	
				String fullName = mCursor.getString(2);
				String address = mCursor.getString(3);
				String phone = mCursor.getString(4);
				String fax = mCursor.getString(5);
				String homePage = mCursor.getString(6);
				String description = mCursor.getString(7);
				
				Item item = new Item("ID",id);	
				items.add(item);
				item = new Item("Short Name",shortName);
				items.add(item);
				item = new Item("Full Name",fullName);
				items.add(item);
				item = new Item("Address",address);
				items.add(item);
				item = new Item("Phone",phone);
				items.add(item);
				item = new Item("FAX",fax);
				items.add(item);
				item = new Item("Homepage",homePage);
				items.add(item);
				item = new Item("Description",description);
				items.add(item);
				mCursor.moveToNext();
			}
		}catch(Exception e){
		
		}finally{
			mCursor.close();
		}
		
		return items;
	}

}
