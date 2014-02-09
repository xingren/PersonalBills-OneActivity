package com.icetea.personalbills;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DayView extends View{

	Paint paint;
	String TAG = "DayView";
	public DayView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public DayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}
	public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		canvas.drawText("23121", 0, 20, paint);
		
		//canvas.drawRect(0,0,getWidth(),getHeight(),paint);
		 canvas.drawLine(0, 0, this.getWidth(), this.getHeight(), paint);
	}
	
	void initPaint(){
		
		paint = new Paint();
		
		paint.setColor(Color.BLUE);
		
		paint.setTextSize(40);
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("DayView", event.toString());
		return super.onTouchEvent(event);
	}
	
	
	
}
