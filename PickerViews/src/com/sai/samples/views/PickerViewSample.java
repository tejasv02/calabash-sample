package com.sai.samples.views;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class PickerViewSample extends Activity {
	
	static final int DATE_DIALOG_ID = 1;
	static final int TIME_DIALOG_ID = 2;
	private TextView dateDisplay;
	private Button pickDate;
	private int year, month, day;
	private TextView timeDisplay;
	private Button pickTime;
	private int hours, min;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        dateDisplay = (TextView)findViewById(R.id.TextView01);
        pickDate = (Button)findViewById(R.id.Button01);
        
        pickDate.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
        	
        });
        
        final Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        
        updateDate();
        
        timeDisplay = (TextView)findViewById(R.id.TextView02);
        pickTime = (Button)findViewById(R.id.Button02);
        
        pickTime.setOnClickListener( new OnClickListener () {

			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
				
			}
        	
        });
        
        hours = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        
        updateTime();
    }

	private void updateTime() {
		timeDisplay.setText(new StringBuilder().append(hours).append(':')
				.append(min));
		
	}

	private void updateDate() {
		dateDisplay.setText(new StringBuilder().append(day).append('-')
				.append(month + 1).append('-').append(year));
		
	}
	
	private DatePickerDialog.OnDateSetListener dateListener = 
		new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int yr, int monthOfYear,
					int dayOfMonth) {
				year = yr;
				month = monthOfYear;
				day = dayOfMonth;
				updateDate();
			}
	};
	
	private TimePickerDialog.OnTimeSetListener timeListener = 
		new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				hours = hourOfDay;
				min = minute;
				updateTime();
			}
		
	};
	protected Dialog onCreateDialog(int id){
		switch(id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, dateListener, year, month, day);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, timeListener, hours, min, false);
		}
		return null;
		
	}
}