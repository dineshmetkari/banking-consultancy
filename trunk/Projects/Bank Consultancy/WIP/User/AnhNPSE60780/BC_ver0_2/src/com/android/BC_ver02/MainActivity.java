package com.android.BC_ver02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button btnUpdateVersion,btnViewListBank;
	TextView txtVersion;
	
	DBHelper mDB;
    Cursor mCursor;
    
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnUpdateVersion = (Button) findViewById(R.id.btnUpdateVersion);
        txtVersion = (TextView) findViewById(R.id.txtVersion);
        btnViewListBank = (Button) findViewById(R.id.btnViewListBank);
        
        mDB = new DBHelper(this);
        mDB.open();
        
        btnViewListBank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), BC_ver0_2Activity.class);
				startActivity(intent);
			}
		});
        
        String version = getVersion();
        txtVersion.setText(version);
        
        btnUpdateVersion.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					String version = getVersion();
					if(WebServiceConnection.CheckServer()){
						if(WebServiceConnection.CheckVersion(version)){
							
						}else{					
							String linkToFile = "http://10.0.2.2:8801/FileServer/BankConsultancy1.db3";
							String fileName = "Bank1";
							String whereToPut = "data/data/com.android.BC_ver02/databases";
							txtVersion.setText("Đang tải");
							if(downloadFile(linkToFile,fileName,whereToPut)){
								version = getVersion();
								txtVersion.setText(version);
							}else{
								txtVersion.setText("Không thể tải dữ liệu mới");
							}
						}
					}else{
						txtVersion.setText("Không kết nối tới server");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
				}						
			}
		});
	}
	
	public static boolean downloadFile(String linkToFile, String fileName, String whereToPut) 
    {    	
		try
    	{
	    	URL url  = new URL(linkToFile);
	        URLConnection conexion = url.openConnection();
	        conexion.connect();
	        int lenghtOfFile = conexion.getContentLength();
	        InputStream is = url.openStream();
	        File testDirectory = new File(whereToPut);
	        //new File(Environment.getExternalStorageDirectory()+"/EorderImage");
	        
	        if(!testDirectory.exists())
	        {
	            testDirectory.mkdir();
	        }
	        
	        FileOutputStream fos = new FileOutputStream(testDirectory+"/" + fileName);
	        byte data[] = new byte[1024];
	        int count = 0;
	        long total = 0;
	        int progress = 0;
	        while ((count=is.read(data)) != -1)
	        {
	            total += count;
	            int progress_temp = (int)total*100/lenghtOfFile;
	            if(progress_temp%10 == 0 && progress != progress_temp)
	            {
	                progress = progress_temp;
	            }
	            fos.write(data, 0, count);
	        }
	        
	        Log.i("DOWNLOAD TABLE", "OK");
	        is.close();
	        fos.close();
	        
	        return true;
    	}
    	catch(Exception ex)
    	{
    		Log.i("DOWNLOAD TABLE", ex.getMessage());
    		return false;
    	}
    }

	private String getVersion(){		
		
		
		String version = "-1";
		try{
			mCursor = mDB.getVersion();
			mCursor.moveToFirst();
			while(!mCursor.isAfterLast()){
				version = mCursor.getString(0);
				mCursor.moveToNext();
			}
		}catch(Exception e){
			String error = e.getMessage();
		}finally{
			if(!version.equals("-1"))
				mCursor.close();
		}
		
		return version;
	}
}
