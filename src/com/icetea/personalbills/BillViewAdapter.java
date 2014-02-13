package com.icetea.personalbills;

import java.util.Formatter;
import java.util.Locale;

import com.icetea.personalbills.value.ConstantValue;
import com.icetea.personalbills_oneactivity.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BillViewAdapter extends BaseAdapter {

	private Context mContext;
	private int mCurrentMainView;
	private String[] mButtonNames;
	private LayoutInflater mInflater;
	private StringBuilder mStringBuilder;
	private Formatter mFormatter;
	private Handler mMidnightHandler;
	private boolean mShowDate;
	private String mTimeZone;
	private int mTodayJulianDay;

	private Runnable mTimeUpdateRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			refresh(mContext);
		}
	};
	private long mMillisTime;

	public BillViewAdapter(Context context, int viewType, boolean showDate) {
		super();

		mMidnightHandler = new Handler();
		mCurrentMainView = viewType;
		mContext = context;
		mShowDate = showDate;

		// Initialize
		mButtonNames = context.getResources().getStringArray(
				R.array.actionbar_buttons_list);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mStringBuilder = new StringBuilder(50);
		mFormatter = new Formatter(mStringBuilder, Locale.getDefault());

		// Sets time specific variables and starts a thread for midnight updates
		if (showDate) {
			refresh(context);
		}
	}

	private void refresh(Context context) {
		// TODO Auto-generated method stub
		long now = System.currentTimeMillis();
		mTimeZone = Time.getCurrentTimezone();
		Time time = new Time(mTimeZone);
		time.set(now);
		mTodayJulianDay = Time.getJulianDay(now, time.gmtoff);
		notifyDataSetChanged();
		setMidnightHanlder();

	}

	private void setMidnightHanlder() {
		// TODO Auto-generated method stub
		long now = System.currentTimeMillis();
		Time time = new Time(mTimeZone);
		time.set(now);
		long delayMillis = (24 * 3600 - time.hour * 3600 - time.minute * 60
				- time.second + 1) * 1000;

		mMidnightHandler.postDelayed(mTimeUpdateRunnable, delayMillis);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mButtonNames.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position < mButtonNames.length) {
			return mButtonNames[position];
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if (convertView == null
				|| ((Integer) convertView.getTag()).intValue() != R.layout.actionbar_pulldown_item) {
			view = mInflater.inflate(R.layout.actionbar_pulldown_item, parent,false);
			view.setTag(new Integer(R.layout.actionbar_pulldown_item));
		} else {
			view = convertView;
		}

		TextView weekDay = (TextView) view.findViewById(R.id.day_id);
		TextView date = (TextView) view.findViewById(R.id.date_id);

		switch (mCurrentMainView) {

		case ConstantValue.DAY:

			weekDay.setText(buildWeekday());
			date.setText(buildFullDay());
			break;
		
		}

		return view;
	}

	public void setTime(long time) {
		mMillisTime = time;
		notifyDataSetChanged();
	}

	private String buildWeekday() {

		String dayOfWeek = null;
		Time time = new Time(mTimeZone);
		time.set(mMillisTime);
		mStringBuilder.setLength(0);
		int julianDay = Time.getJulianDay(mMillisTime, time.gmtoff);

		if (julianDay == mTodayJulianDay) {

			dayOfWeek = mContext.getString(
					R.string.aganda_today,
					DateUtils.formatDateRange(mContext, mFormatter,
							mMillisTime, mMillisTime,
							DateUtils.FORMAT_SHOW_WEEKDAY).toString());

		} else if (julianDay == mTodayJulianDay - 1) {
			dayOfWeek = mContext.getString(
					R.string.aganda_yesterday,
					DateUtils.formatDateRange(mContext, mFormatter,
							mMillisTime, mMillisTime,
							DateUtils.FORMAT_SHOW_WEEKDAY).toString());
		} else if (julianDay == mTodayJulianDay + 1) {
			dayOfWeek = mContext.getString(
					R.string.aganda_tomorrow,
					DateUtils.formatDateRange(mContext, mFormatter,
							mMillisTime, mMillisTime,
							DateUtils.FORMAT_SHOW_WEEKDAY).toString());
		} else {
			dayOfWeek = DateUtils.formatDateRange(mContext, mFormatter,
					mMillisTime, mMillisTime, DateUtils.FORMAT_SHOW_WEEKDAY)
					.toString();
		}

		return dayOfWeek;
	}

	private String buildFullDay() {
		String fullDay;
		mStringBuilder.setLength(0);
		fullDay = DateUtils.formatDateRange(mContext, mFormatter, mMillisTime,
				mMillisTime, DateUtils.FORMAT_SHOW_YEAR).toString();

		return fullDay;

	}
}
