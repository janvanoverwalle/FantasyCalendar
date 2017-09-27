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

        Calendar c = Calendar.loadFromJson("{\"name\":\"Elderan\",\"year_len\":364,\"events\":1,\"n_months\":12,\"months\":[\"Morning Star\",\"Sun's Dawn\",\"First Seed\",\"Rain's Hand\",\"Second Seed\",\"Mid Year\",\"Sun's Height\",\"Last Seed\",\"Hearthfire\",\"Frost Fall\",\"Sun's Dusk\",\"Evening Star\"],\"month_len\":{\"Morning Star\":30,\"Sun's Dawn\":30,\"First Seed\":31,\"Rain's Hand\":30,\"Second Seed\":30,\"Mid Year\":31,\"Sun's Height\":30,\"Last Seed\":30,\"Hearthfire\":31,\"Frost Fall\":30,\"Sun's Dusk\":30,\"Evening Star\":31},\"week_len\":7,\"weekdays\":[\"Morndas\",\"Tirdas\",\"Middas\",\"Turdas\",\"Fredas\",\"Loredas\",\"Sundas\"],\"n_moons\":2,\"moons\":[\"Masser\",\"Secunda\"],\"lunar_cyc\":{\"Masser\":12,\"Secunda\":25},\"lunar_shf\":{\"Masser\":0,\"Secunda\":0},\"year\":2026,\"first_day\":0,\"notes\":{\"2026-1-1\":\"Test\"}}");

        Log.d(LOG_TAG, c.getMonthsJSON().toString());
    }

    @Override
    public void setCalendarContent(int position) {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
