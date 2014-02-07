package com.icetea.personalbills.sql;

import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase.CursorFactory;  
import net.sqlcipher.database.SQLiteOpenHelper; 

public class BillsSQLCipherHelper extends SQLiteOpenHelper {

	public BillsSQLCipherHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(net.sqlcipher.database.SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists Bills(_id int primary key,data varchar(20),cost double,activity varchar(100),info varchar(2048))");
	}

	@Override
	public void onUpgrade(net.sqlcipher.database.SQLiteDatabase arg0, int arg1,
			int arg2) {
		// TODO Auto-generated method stub
		
	}


	
}
