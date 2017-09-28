package com.jvanoverwalle.fantasycalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jvanoverwalle.fantasycalendar.database.DbHelper;
import com.jvanoverwalle.fantasycalendar.models.Calendar;

public class MainActivity extends AppCompatActivity implements ICalendarClickListener {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName() + "-debug";
    private DbHelper _dbHelper;

    public static final String SERIALIZED_CALENDAR = "serialized_calendar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _dbHelper = DbHelper.getInstance(getApplicationContext());

        //_dbHelper.flushDatabase();
    }

    @Override
    public void setCalendarContent(Calendar c) {

        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra(SERIALIZED_CALENDAR, c);
        startActivity(intent);
    }
}
