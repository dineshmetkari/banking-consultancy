package com.android.BC_ver02;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper 
{	
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{

		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) 
		{
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) 
		{		
			//neu co nhiu bang thi tao nhiu bang
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{		
			
		}
	}
	
				
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDB;
	//thay doi de tao bang
	private static final String DATABASE_CREATE = "create table items (_name text, image text not null);";
	private static final String DATABASE_NAME = "BankConsultancy1";
	
	private final Context mContext;
	
	
	public DBHelper(Context ctx)
	{
		this.mContext = ctx;
	}
	
	public DBHelper open()
	{
		mDbHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, 2);
		mDB = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		mDbHelper.close();
	}
	
	
	//query insert,update,delete
	public long createItem(String name, String image)
	{
		/*ContentValues inititalValues = new ContentValues();
		inititalValues.put("_name", name);
		inititalValues.put("image", image);
		return mDB.insert("items", null, inititalValues);//tham so: ten table,null,doi tuong(1row)
		//return mDB.rawQuery("insert into items values('abcDFDF','adasdads')",null);*/
		return 0;//them vao
	}
	// tra ve cursor chi toi tung dong		
	public Cursor getAllItems()
	{
		return mDB.rawQuery("Select * from Bank",null);
	}
	//delete 
	public boolean deleteItem(String name)
	{		
		//mDB.update(table, values, whereClause, whereArgs)
		/*return mDB.delete("items","_name" + "= '" + name + "'", null) > 0;//tham so: ten table,where,thay dau ?(vi du value(?,?,?))*/
		return true;//them vao
	}
	
	public Cursor getBankName(String bankID)
	{
		return mDB.rawQuery("Select * from Bank where BankID="+bankID,null);
	}
	
	public Cursor getBankInterest(String bankID)
	{
		return mDB.rawQuery("Select * from BankInterest where BankID="+bankID,null);
	}
	
	public Cursor getInterest(String InterestID)
	{
		return mDB.rawQuery("Select * from Interest where InterestID="+InterestID,null);
	}
	
	public Cursor getIntTypeAndRate(String BankID)
	{
		String sql = String.format("select bi.InterestRate, i.InterestTime from BankInterest bi, Interest i WHERE bi.BankID=%s AND bi.InterestID=i.InterestID", BankID);
		return mDB.rawQuery(sql,null);
	}
}
