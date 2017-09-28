package com.jvanoverwalle.fantasycalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

public class CalendarFragment extends Fragment {
    private static final String LOG_TAG = CalendarFragment.class.getCanonicalName() + "-debug";

    // TODO: Use a TableLayout instead?
    private GridView _gridView;
    private TextView _dateHeader;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        _gridView = (GridView) view.findViewById(R.id.calendar_grid_view);
        _gridView.setVerticalScrollBarEnabled(false);

        _dateHeader = (TextView) view.findViewById(R.id.calendar_grid_date);

        return view;
    }

    // TODO: Fix this function (a.e.: Write it properly)
    public void setContent(Calendar c, int year, int month) {
        CalendarViewAdapter calendarViewAdapter = new CalendarViewAdapter(getContext(), c, year, month);
        _gridView.setAdapter(calendarViewAdapter);

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        int colWidth = (int) (dm.widthPixels * 0.8f) / c.getWeekLength();
        _gridView.setColumnWidth(colWidth);
        //_gridView.setNumColumns(c.getWeekLength());
        //Log.d(LOG_TAG, colWidth + "");

        _dateHeader.setText(c.getMonths()[month] + " " + String.valueOf(year));
    }
}
