package com.jvanoverwalle.fantasycalendar;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarFragment extends Fragment {

    private GridView _gridView;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        _gridView = (GridView) view.findViewById(R.id.calendar_grid_view);

        return view;
    }

    // TODO: Fix this function (a.e.: Write it properly)
    public void setContentByPosition(int position) {
        // 'position' is the position in the Overview List View
        TextView t = new TextView(getContext());

        _gridView.setNumColumns(7);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //_gridView.setBackgroundColor(getResources().getColor(R.color.colorGreyLight, null));
            t.setBackgroundColor(getResources().getColor(R.color.colorGreyLight, null));
        }
        else {
            //_gridView.setBackgroundColor(getResources().getColor(R.color.colorGreyLight));
            t.setBackgroundColor(getResources().getColor(R.color.colorGreyLight));
        }

        _gridView.addView(t);
    }
}
