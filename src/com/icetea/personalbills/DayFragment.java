package com.icetea.personalbills;

import com.icetea.personalbills_oneactivity.R;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

@SuppressLint("NewApi")
public class DayFragment extends Fragment implements ViewFactory {

	
	private static final int VIEW_ID = 1;
	DayView dayView;
	View view;
	private ViewSwitcher mViewSwitcher;
	private boolean mSelectedDay;
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//return super.onCreateView(inflater, container, savedInstanceState);
		
		
		view = inflater.inflate(R.layout.day_fragment, null);
		
		
		mViewSwitcher = (ViewSwitcher) view.findViewById(R.id.view_switcher_id);
		mViewSwitcher.setFactory(this);
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		DayView view = new DayView(getActivity(),Controler.getInstance(getActivity()),mViewSwitcher,1);
		  view.setId(VIEW_ID);
	        view.setLayoutParams(new ViewSwitcher.LayoutParams(
	                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	        //view.setSelected(mSelectedDay, false, false);
		return view;
	}
	
}
