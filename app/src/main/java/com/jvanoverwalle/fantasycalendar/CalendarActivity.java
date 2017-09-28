package com.jvanoverwalle.fantasycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

public class CalendarActivity extends AppCompatActivity implements ICalendarClickListener {
    private static final String LOG_TAG = CalendarActivity.class.getCanonicalName() + "-debug";

    private Calendar _calendar;
    private int _currentMonthIndex;
    private int _currentYearIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        Calendar c = (Calendar) intent.getSerializableExtra(MainActivity.SERIALIZED_CALENDAR);

        _calendar = c;
        _currentYearIndex = c.getYear();
        _currentMonthIndex = c.getMonth();// 0-based index
        setCalendarContent(_calendar);
    }

    @Override
    public void setCalendarContent(Calendar c) {
        CalendarFragment calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentById(R.id.calendar_fragment_container);
        calendarFragment.setContent(c, _currentYearIndex, _currentMonthIndex);
    }

    // TODO: Test whether it crashes when going to previous year
    public void onClickPreviousMonth(View view) {
        _currentMonthIndex--;

        if (_currentMonthIndex < 0) {
            _currentMonthIndex =  _calendar.getNumMonths() - 1;
            _currentYearIndex--;
        }

        setCalendarContent(_calendar);
    }

    // TODO: Crashes when going to next year
    public void onClickNextMonth(View view) {
        _currentMonthIndex++;

        if (_currentMonthIndex >= _calendar.getNumMonths()) {
            _currentMonthIndex =  0;
            _currentYearIndex++;
        }

        setCalendarContent(_calendar);
    }
}
