package com.jvanoverwalle.fantasycalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jvanoverwalle.fantasycalendar.database.DbHelper;
import com.jvanoverwalle.fantasycalendar.models.Calendar;

import java.util.ArrayList;

public class OverviewListFragment extends Fragment {

    public OverviewListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_overview_list_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.main_overview_list_view);

        // Retrieve all calendars from database
        ArrayList<Calendar> calendars = DbHelper.getInstance(getContext()).getAllCalendars();

        MainOverviewArrayAdapter mainOverviewAdapter = new MainOverviewArrayAdapter(getActivity(), calendars);
        listView.setAdapter(mainOverviewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ICalendarClickListener calendarClickListener = (ICalendarClickListener) getActivity();

                Calendar c = DbHelper.getInstance(getContext()).getAllCalendars().get(position);

                calendarClickListener.setCalendarContent(c);
            }
        });

        return view;
    }

}
