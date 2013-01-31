package com.android.BC_ver02;

import interest.Interest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	TextView txtBankName,txtIntRate;
	Spinner spIntType;
	Button btnCal;
	EditText txtMoneyIn, txtMoneyOut;
		
	Map<String,String> listMap;
	String id;
	
	DBHelper mDB;
    Cursor mCursor;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator);		
        
		txtBankName = (TextView)findViewById(R.id.txtBankName_Cal);
		txtIntRate = (TextView)findViewById(R.id.txtIntRate_Cal);
		spIntType = (Spinner)findViewById(R.id.spIntType_Cal);
		btnCal = (Button)findViewById(R.id.btnCal_Cal);
		txtMoneyIn = (EditText)findViewById(R.id.txtMoneyIn_Cal);
		txtMoneyOut = (EditText)findViewById(R.id.txtMoneyOut_Cal);
		
		mDB = new DBHelper(this);
        mDB.open();
		
		id = getIntent().getStringExtra("id");
		txtBankName.setText(getBankName(id));
		
		addItemToSpin();
		
		btnCal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Interest cal = new Interest();
				try{
					String s = spIntType.getSelectedItem().toString();
					s = s.substring(0, s.indexOf(' '));
					int month = Integer.parseInt(s);
					s = txtIntRate.getText().toString();
					s = s.substring(0,s.indexOf("%"));
					double rate = Double.parseDouble(s)/100;
					double money = Double.parseDouble(txtMoneyIn.getText().toString());
					double res = cal.InterestCalculate2(month, rate, money);
					txtMoneyOut.setText(""+res);
				}catch(Exception ex){
					ex.printStackTrace();
				}												
			}
		});
	}
				
	private void addItemToSpin(){
		listMap = getInterestType();
		List<String> list = new ArrayList<String>();
		Set s = listMap.entrySet();
		Iterator it = s.iterator();
		while(it.hasNext()){
			Map.Entry m = (Map.Entry) it.next();
			String key = (String) m.getKey();
			list.add(key);
		}		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spIntType.setAdapter(dataAdapter);
		
		spIntType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view1,
					int position, long id) {
				// TODO Auto-generated method stub
				String key = parent.getItemAtPosition(position).toString();
				txtIntRate.setText(listMap.get(key));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private Map<String,String> getInterestType(){
		Map<String,String> items = new HashMap<String,String>();						
		try{
			mCursor = mDB.getIntTypeAndRate(id);
			mCursor.moveToFirst();
			while(!mCursor.isAfterLast()){
				String rate = mCursor.getString(0);
				String time = mCursor.getString(1);				
												
				items.put(time+" months", rate+"%");
				mCursor.moveToNext();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			mCursor.close();
		}
		
		return items;
	}

	private String getBankName(String id){						
        
		mCursor = mDB.getBankName(id);
		String fullName = "";
		try{
			mCursor.moveToFirst();
			while(!mCursor.isAfterLast()){
				fullName = mCursor.getString(2);											
				mCursor.moveToNext();
			}
		}catch(Exception e){
		
		}finally{
			mCursor.close();
		}
		
		return fullName;
	}
}
