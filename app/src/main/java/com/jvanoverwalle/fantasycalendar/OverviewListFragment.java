package com.jvanoverwalle.fantasycalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

import java.util.ArrayList;
import java.util.List;

public class OverviewListFragment extends Fragment {

    public OverviewListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_overview_list_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.main_overview_list_view);

        List<Calendar> calendars = new ArrayList<Calendar>();
        calendars.add(new Calendar("Earth"));
        calendars.add(new Calendar("Krypton"));
        calendars.add(new Calendar("Cybertron"));

        MainOverviewArrayAdapter mainOverviewAdapter = new MainOverviewArrayAdapter(getActivity(), calendars);
        listView.setAdapter(mainOverviewAdapter);

        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Wanneer er een rij aangeklikt wordt, sturen we de positie door naar de Listener
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailListener detailListener = (DetailListener) getActivity();
                detailListener.setFieldsAndPicture(position);
            }
        });
        */

        return view;
    }

}
