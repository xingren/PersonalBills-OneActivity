package com.icetea.personalbills;

import java.util.WeakHashMap;

import com.icetea.personalbills.bean.EventInfo;

import android.content.Context;


public class Controler {
	
	public interface EventHandler{
		
		public void handleMessage();
		
		
	}
	
	private Context mContext;
	private static Controler mControler = null;
	
	private WeakHashMap<Integer, EventHandler> handlers;
	
	private Controler(Context context){
		mContext = context;
	}
	
	public static Controler getInstance(Context context){
		if(mControler == null)
			mControler = new Controler(context);
		return mControler;
	}
	
	
	public void sendEvent(EventInfo event){
		
		switch(event.viewType){
			
		}
		
		
		
	}
	
	
}
