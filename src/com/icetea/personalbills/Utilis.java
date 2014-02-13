package com.icetea.personalbills;

public class Utilis {

	//static public String formatDa
	public static String[] mHourStrings = new String[24];
		
	public static void makeHourString(){
		
		
		for(int i = 0 ;i < 24 ;i++){
			mHourStrings[i] = String.format("%02d", i);  
			
		}
		
	}
	
}
