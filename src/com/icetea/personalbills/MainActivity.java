package com.icetea.personalbills;

import net.sqlcipher.database.SQLiteDatabase;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.icetea.personalbills.sql.AccountsSQLCipherHelper;
import com.icetea.personalbills.sql.BillsSQLCipherHelper;
import com.icetea.personalbills.value.*;
import com.icetea.personalbills_oneactivity.R;
@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnMenuItemClickListener ,OnNavigationListener {

	private String BILLS_DB_URI = VariableValue.BillsDB_URI;
	private String ACCOUNTS_DB_URI = VariableValue.ACCOUNT_DB_URI;
	private ActionBar mActionBar ;
	private BillViewAdapter mActionBarMenuSpinnerAdapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SQLiteDatabase.loadLibs(this);
		BillsSQLCipherHelper billsDbHelper = new BillsSQLCipherHelper(this, BILLS_DB_URI , null, 1);
		AccountsSQLCipherHelper accountsDbHelper = new AccountsSQLCipherHelper(this, ACCOUNTS_DB_URI , null, 1);
		
		
		//initial Data
		
		initGobalValue();
		
		
		
		
		
		//initial UI
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		
		DayFragment dayFragment = new DayFragment();
		
		
		transaction.replace(R.id.fragment_id, dayFragment);
		transaction.show(dayFragment);
		
		transaction.commit();
		
		mActionBar = getActionBar();
//		mActionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.title_bg));
//		int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
//		TextView title = (TextView) findViewById(titleId);
//		title.setTextColor(this.getResources().getColor(Color.RED));
        createActionBarMenuSpinner(ConstantValue.DAY, true);
        
        mActionBarMenuSpinnerAdapter.setTime(System.currentTimeMillis());
 
        mActionBar.show();  
	}
	
	void createActionBarMenuSpinner(int viewType,boolean showDate){
		 mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		 mActionBarMenuSpinnerAdapter = new BillViewAdapter(this, viewType, showDate);
	     mActionBar.setListNavigationCallbacks(mActionBarMenuSpinnerAdapter, this);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		MenuItem add=menu.add(0,0,0,"add");  
//        MenuItem list = menu.add(0,1,0,"list"); 
//        //绑定到ActionBar    
//        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);  
//        list.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        add.setOnMenuItemClickListener(this);
//        list.setOnMenuItemClickListener(this);
		return true;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		switch(itemPosition){
		
		case ConstantValue.ACTION_BAR_SPINNER_DAY_INDEX:
			
			Toast.makeText(getApplicationContext(), "Day", Toast.LENGTH_SHORT).show();
			
			break;
		case ConstantValue.ACTION_BAR_SPINNER_WEEK_INDEX:
			
			Toast.makeText(getApplicationContext(), "WEEK", Toast.LENGTH_SHORT).show();
			
			break;
		}
		return false;
	}
	
	public void initGobalValue(){
		
		Utilis.makeHourString();
		
	}

}
