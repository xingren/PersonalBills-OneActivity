package com.icetea.personalbills;


import com.icetea.personalbills_oneactivity.R;

import android.R.integer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewSwitcher;

public class DayView extends View{

	private static final int HOURS_TOP_MARGIN = 0;
	private static final float HOURS_LEFT_MARGIN = 0;
	Paint paint;
	Paint mEventSelectedPaint;
	String TAG = "DayView";
	String[] mHourStrings = Utilis.mHourStrings;
	
	private Context mContext;
	
	
	
	//cell attribute
	int mHourCellCountOnePage;
	int mHourCellHight;
	int mBillShowWidth;
		
	float hourSideWidthPercent = 0.1f ;
	private float eventsCellWidthPercent;
	private int mDayHeader;
	private int mViewStartY;
	private int mViewWidth;
	private int mFirstHourCell;
	private int mViewHeight;
	private int mDaysNum;
	private int mHourTextHeight;
	
	private int HOUR_GAP;
	private int mViewStartX;
	private float mEventCellWidth;
	//colors
	private int VIEW_BG_COLOR;
	private int HOUR_TEXT_COLOR;
	private int	EVENT_TEXT_COLOR;
	private int HOUR_CELL_BG_COLOR;
	private int EVENT_CELL_BG_COLOR;
	private int EVENT_CELL_SELECT_COLOR;
	
	
	//font size
	private int HOUR_TEXT_SIZE;
	private int EVENT_TEXT_SIZE;
	
	
	
	
	
	//objects about draw
	private Paint mTextPaint;
	private Paint mCellPaint;
	private Paint mEventCellPaint;
	
	Rect rect = new Rect();
	private Paint mEventTextPaint;
	private Resources resources;
	private int mHourCellWidth;
	
	private float mLines[];
	private Paint mLinesPaint;
	private int mGridHorizontalLinesColor;
	private Context mControler;
	private ViewSwitcher mViewSitcher;
	
	public DayView(Context context,Controler controler,ViewSwitcher viewSwitcher,int daysNum){
		super(context);
		mContext = context;
		mControler = context;
		mViewSitcher = viewSwitcher;
		mDaysNum = daysNum;
		
		
		resources = mContext.getResources();
		
		initPaint();
	}
	
	public DayView(Context context) {
		super(context);
		mContext = context;
		resources = mContext.getResources();
		// TODO Auto-generated constructor stub
	}
	
	public DayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		resources = mContext.getResources();
		initPaint();
	}
	public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		
		mContext = context;
		resources = mContext.getResources();
	}
	
	public DayView(Context context,int mDaysNum){
		
		super(context);
		mContext = context;
		resources = mContext.getResources();
		this.mDaysNum = mDaysNum;
		
	}
	
	public int getmDaysNum() {
		return mDaysNum;
	}

	public void setDaysNum(int mDaysNum) {
		this.mDaysNum = mDaysNum;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		setDrawAttribute();
		setPaints();
		
		
		
		canvas.drawColor(Color.WHITE);
		//canvas.drawText("23121", 0, 40, paint);
		
		//canvas.drawRect(0,0,getWidth(),getHeight(),paint);
		// canvas.drawLine( width * hourSideWidthPercent, 0, width * hourSideWidthPercent, height, paint);
			
		int translateY = -mViewStartY + mDayHeader;
		int translateX = -mViewStartX;
		canvas.translate(translateY, translateX);
		
		
		rect.top = mFirstHourCell - translateY;
		rect.bottom = mViewHeight - translateY;
		rect.left = 0;
		rect.right = mViewWidth;
			
		canvas.save();
		
		//首先锁定一个clirect
		
		canvas.clipRect(rect);
		
		
		doDraw(canvas);
		
		canvas.restore();
		
		
		
		 //for()
	}
	
	void initPaint(){
		
		//mEventSelectedPaint = new Paint();
		
		mTextPaint = new Paint();
		
		mCellPaint = new Paint();
		
		//mEventCellPaint = new Paint();
		
		//mEventTextPaint = new Paint();
		
		mLinesPaint = new Paint();
		
		VIEW_BG_COLOR = resources.getColor(R.color.dayview_bg_color);
		HOUR_CELL_BG_COLOR = resources.getColor(R.color.dayview_hour_cell_color);
		HOUR_GAP = 1;
		HOUR_TEXT_COLOR = resources.getColor(R.color.dayview_hour_text_color);
		EVENT_CELL_BG_COLOR = resources.getColor(R.color.dayview_event_cell_color);
		EVENT_CELL_SELECT_COLOR = resources.getColor(R.color.dayview_event_select_color);
		EVENT_TEXT_COLOR = resources.getColor(R.color.dayview_event_text_color);
		
		
		
		int mGridLinesNum = 24 + 1 + mDaysNum + 1;
		mLines = new float[mGridLinesNum*4];
		mGridHorizontalLinesColor = getResources().getColor(R.color.grid_background_line_color);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("DayView", event.toString());
		return super.onTouchEvent(event);
	}
	
	private void setPaints(){
		//mEventSelectedPaint.setColor(EVENT_CELL_SELECT_COLOR);
		//mEventCellPaint.setColor(EVENT_CELL_BG_COLOR);
		
		mTextPaint.setColor(HOUR_TEXT_COLOR);
		mCellPaint.setColor(HOUR_CELL_BG_COLOR);
		mTextPaint.setTextSize(mHourTextHeight);
		
	}
	
	private void setDrawAttribute(){
		
		mViewWidth = getWidth();
		mViewHeight = getHeight();
		
		hourSideWidthPercent = 0.15f;
		
		eventsCellWidthPercent = 1 - hourSideWidthPercent;
		
		mEventCellWidth = eventsCellWidthPercent*mViewWidth/mDaysNum;
		
		mHourCellCountOnePage = 8;
		
		mHourCellHight = mViewHeight / mHourCellCountOnePage;
		
		HOUR_GAP = 1;
		
		mHourTextHeight = (int) (mHourCellHight * 0.25);
		
		mViewStartY = 0;
		mFirstHourCell = 0;
		mDayHeader = 0;
		mViewStartX = 0;
		mHourCellWidth = (int) (mViewWidth*hourSideWidthPercent);
	}
	
	
	private void doDraw(Canvas canvas){
		
		drawGridBackground(canvas);
		drawHours(canvas);
		
		for(int i = 0;i < mDaysNum;i++){
			
			
			
		}
		
	}
	
	private void drawEvents(Canvas canvas,int cellDay,int day) {
		
		
		
	}
	
	private void drawHours(Canvas canvas) {
				
		int y = HOUR_GAP + mHourTextHeight + HOURS_TOP_MARGIN;
				
		for (int i = 0 ;i < 24 ;i++){
			
			canvas.drawText(mHourStrings[i], HOURS_LEFT_MARGIN, y,mTextPaint);
			
			y += mHourCellHight;
		}
		
	}
	
	private void drawGridBackground(Canvas canvas) {
		
		Style saveStyle = mLinesPaint.getStyle();
		mLinesPaint.setAntiAlias(false);//关闭抗锯齿选项，节约性能
		
		mLinesPaint.setColor(mGridHorizontalLinesColor);
		float endX = computDayStartX(mDaysNum);				
		int linesIndex = 0;
		int deltaY = mHourCellHight + HOUR_GAP;
		float y = 0;
		float endY = HOUR_GAP + 24*(mHourCellHight + HOUR_GAP);
		float startY = 0;
		float x = 0;
		for(int i = 0;i <= 24;i++){
			mLines[linesIndex++] = HOURS_LEFT_MARGIN; 
			mLines[linesIndex++] = y;
			mLines[linesIndex++] = endX;
			mLines[linesIndex++] = y;
			y += deltaY;
		}
		
		for (int day = 0;day <= mDaysNum;day++){
			x = computDayStartX(day);
			mLines[linesIndex++] = x;
			mLines[linesIndex++] = startY;
			mLines[linesIndex++] = x;
			mLines[linesIndex++] = endY;
		}
		
		canvas.drawLines(mLines,0,linesIndex, mLinesPaint);
		
		mLinesPaint.setAntiAlias(true);
		mLinesPaint.setStyle(saveStyle);
	}
	
	private float computDayStartX(int day){
		int dayXLen = mViewWidth - mHourCellWidth;
		return day*dayXLen/mDaysNum + mHourCellWidth;
	}
}
