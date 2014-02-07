package com.icetea.personalbills.bean;

import android.content.ContentValues;

public class Bill {
	
	public int _id;
	public String date;
	public String activity;
	public String info;
	public double cost;
	public Bill(String date,double cost,String activity,String info) {
		// TODO Auto-generated constructor stub
		this.date = date;
		this.cost = cost;
		this.activity = activity;
		this.info = info;
	}
	
	public Bill() {
		// TODO Auto-generated constructor stub
	}

	public ContentValues ToContentValue(ContentValues values){
		values.put("date", this.date);
		values.put("info", this.info);
		values.put("cost", this.cost);
		values.put("activity", this.activity);
		
		
		return values;
	}
	
}
