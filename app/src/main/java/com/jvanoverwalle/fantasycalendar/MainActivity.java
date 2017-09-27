package com.jvanoverwalle.fantasycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

public class MainActivity extends AppCompatActivity implements ICalendarClickListener {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName() + "-debug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setCalendarContent(int position) {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
