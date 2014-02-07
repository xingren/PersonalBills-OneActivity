package com.icetea.personalbills.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.icetea.personalbills.bean.Bill;

import net.sqlcipher.database.SQLiteDatabase;

public class SQLOp {
	static String TAG="SQLOp";
	static public SQLiteDatabase billsDb,accountsDb;
	static int dbFieldCount = 5;
	static String[] field = {"_id","date","info","cost","activity"};
	public SQLOp(SQLiteDatabase db,SQLiteDatabase accountsDb){
			SQLOp.billsDb = db;
			SQLOp.accountsDb = accountsDb;
		}
	static public void insert(Bill bill){
		ContentValues values = new ContentValues();
		values.put("date", bill.date);
		values.put("info", bill.info);
		values.put("cost", bill.cost);
		values.put("activity", bill.activity);
		billsDb.insert("Bills", null, values);
	}
	static public void delete(Bill bill){
		billsDb.execSQL("delete from bills where _id=" + String.valueOf(bill._id));
	}
	static public void update(Bill bill){
		
		ContentValues values = new ContentValues();
		values = bill.ToContentValue(values);
		String args[] = {String.valueOf(bill._id)};
		billsDb.update("bills", values, "_id=?",args);
	}
	
	static public Bill[] query(String sql){
		
		Cursor cursor = null;
		cursor = billsDb.rawQuery(sql, null);
		
		if(cursor == null) return null;
		
		Bill bills[] = new Bill[cursor.getCount()];
		
		int count = 0;
		while(!cursor.isAfterLast()){
			
				bills[count] = getRow(cursor);
				if(bills[count] == null){
					if(count == 0) return null;
					
					Log.e(TAG,"queryLimited error");
					
					Bill[] tmpBills = new Bill[count - 1];
					System.arraycopy(bills, 0, tmpBills, 0, count-1);
					return tmpBills;
				}
		}
		return bills;
	}
	static public Bill getRow(Cursor cursor){
		
		if(cursor.isBeforeFirst() || cursor.isAfterLast())
			return null;
		
		Bill bill = new Bill();
		
		int index = cursor.getColumnIndex("date");
		if(index != -1){
			bill.date = cursor.getString(index);
		}
		
		index = cursor.getColumnIndex("info");
		if(index != -1){
			bill.info = cursor.getString(index);
		}
		index = cursor.getColumnIndex("activity");
		if(index != -1){
			bill.activity = cursor.getString(index);
		}
		index = cursor.getColumnIndex("cost");
		if(index != -1){
			bill.cost = cursor.getFloat(index);
		}
		index  =  cursor.getColumnIndex("_id");
		if(index != -1){
			bill._id = cursor.getInt(index);
		}
		return bill;
	}
	
	static public boolean checkPassword(String password){
		
		Cursor cursor;
		
		cursor = accountsDb.rawQuery("select * from Accounts where password='" + password + "'", null);
		
		if(cursor == null) return false;
		
		if(cursor.isAfterLast() || cursor.isBeforeFirst() || cursor.getCount() == 0 ||cursor.isNull(cursor.getColumnIndex("password")))
			return false;
		
		return true;
	}
	
	
	static public void createPassword(String password){
		accountsDb.execSQL("insert into Accounts values(" + password + ")");
	}
}
