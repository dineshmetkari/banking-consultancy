package com.android.BC_ver02;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class BC_ver0_2Activity extends Activity {
    ListView listBank;
    BankNameAdapter adapter;
    ArrayList<ItemListviewBank_Main> listItem;
    
    DBHelper mDB;
    Cursor mCursor;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listbank);
        
        listBank = (ListView)findViewById(R.id.listviewBank_Main);
        mDB = new DBHelper(this);
        mDB.open();
        
//        listItem = new ArrayList<ItemListviewBank_Main>();
        listItem = getData();
        
        adapter = new BankNameAdapter(getApplicationContext(),listItem);        
        listBank.setAdapter(adapter);
               
    }
    
    private ArrayList<ItemListviewBank_Main> getData(){
		ArrayList<ItemListviewBank_Main> items = new ArrayList<ItemListviewBank_Main>();
		
		mCursor = mDB.getAllItems();
		
		try{
			mCursor.moveToFirst();
			while(!mCursor.isAfterLast()){
				String id = mCursor.getString(0);
				String name = mCursor.getString(1);				
				
				ItemListviewBank_Main item = new ItemListviewBank_Main(id,name);
				
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