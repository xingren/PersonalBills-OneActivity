package com.icetea.personalbills;

import com.icetea.personalbills.bean.Bill;
import com.icetea.personalbills.sql.SQLOp;
import com.icetea.personalbills_oneactivity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AddBillActivity extends Activity implements OnClickListener {
	Button OKBtn;
	TextView infoTv;
	TextView dateTv;
	TextView costTv;
	TextView activityTv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_bill_activity);
		
		OKBtn = (Button) findViewById(R.id.confirm_btn);
		dateTv = (TextView)findViewById(R.id.date_text);
		costTv = (TextView) findViewById(R.id.cost_text);
		activityTv = (TextView)findViewById(R.id.activity_text);
		infoTv = (TextView)findViewById(R.id.info_text);
		
		//DatePicker
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.confirm_btn:
			
			String date,activity,info;
			float expenditure;
			
			date = dateTv.getText().toString();
			activity = activityTv.getText().toString();
			info = infoTv.getText().toString();
			
			expenditure = Float.valueOf(costTv.getText().toString());
			
			
			SQLOp.insert(new Bill(date, expenditure, activity, info));
			
			break;
		}
	}
}
