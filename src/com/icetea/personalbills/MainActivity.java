package com.icetea.personalbills;

import net.sqlcipher.database.SQLiteDatabase;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.Menu;

import com.icetea.personalbills.sql.AccountsSQLCipherHelper;
import com.icetea.personalbills.sql.BillsSQLCipherHelper;
import com.icetea.personalbills.value.*;
import com.icetea.personalbills_oneactivity.R;
public class MainActivity extends Activity  {

	private String BILLS_DB_URI = VariableValue.BillsDB_URI;
	private String ACCOUNTS_DB_URI = VariableValue.ACCOUNT_DB_URI;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SQLiteDatabase.loadLibs(this);
		BillsSQLCipherHelper billsDbHelper = new BillsSQLCipherHelper(this, BILLS_DB_URI , null, 1);
		AccountsSQLCipherHelper accountsDbHelper = new AccountsSQLCipherHelper(this, ACCOUNTS_DB_URI , null, 1);
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		DayFragment dayFragment = new DayFragment();
		
		
		transaction.replace(R.id.fragment_id, dayFragment);
		transaction.show(dayFragment);
		
		transaction.commit();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
