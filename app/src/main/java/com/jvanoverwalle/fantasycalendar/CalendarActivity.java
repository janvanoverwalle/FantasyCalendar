package com.jvanoverwalle.fantasycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalendarActivity extends AppCompatActivity implements ICalendarClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if (position != -1) {
            setCalendarContent(position);
        }
    }

    @Override
    public void setCalendarContent(int position) {
        CalendarFragment calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentById(R.id.calendar_fragment_container);
        calendarFragment.setContentByPosition(position);
    }
}
