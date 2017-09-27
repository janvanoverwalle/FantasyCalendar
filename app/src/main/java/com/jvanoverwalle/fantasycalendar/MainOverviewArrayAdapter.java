package com.jvanoverwalle.fantasycalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

import java.util.List;

/**
 * Created by Junior on 27/09/2017.
 */

public class MainOverviewArrayAdapter extends ArrayAdapter<Calendar> {
    private final Context _context;
    private final List<Calendar> _calendars;

    MainOverviewArrayAdapter(Context context, List<Calendar> calendars) {
        super(context, -1, calendars);
        _context = context;
        _calendars = calendars;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.main_overview_row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.main_overview_row_name);

        textView.setText(_calendars.get(position).getName());

        return rowView;
    }
}
