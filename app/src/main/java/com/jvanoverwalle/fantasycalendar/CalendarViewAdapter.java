package com.jvanoverwalle.fantasycalendar;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

/**
 * Created by Junior on 27/09/2017.
 */

public class CalendarViewAdapter extends BaseAdapter {
    private static final String LOG_TAG = CalendarViewAdapter.class.getCanonicalName() + "-debug";

    private final Context _context;
    private final Calendar _calendar;
    private final int _year;
    private final int _month;
    private final int _totalCells;
    private int _startCellIndex;
    private int _endCellIndex;
    private int _daysInPreviousMonth;

    public CalendarViewAdapter(Context context, Calendar calendar, int year, int month) {
        _context = context;
        _calendar = calendar;
        _year = year;
        _month = month;
        _daysInPreviousMonth = _calendar.getMonthLengths().get(_calendar.getMonths()[_month - 1 % _calendar.getNumMonths()]);

        int weekLength = _calendar.getWeekLength();
        int daysInMonth = _calendar.getMonthLengths().get(_calendar.getMonths()[_month]);

        int daysPassedInYear = 0;
        for (int i = 0; i < _month; ++i) {
            daysPassedInYear += _calendar.getMonthLengths().get(_calendar.getMonths()[i]);
        }

        int shift = (_calendar.getYearLength() - daysPassedInYear) % weekLength;

        if (shift > 0) {
            shift = weekLength - shift;
        }

        int cellsAtEnd = (daysInMonth + shift) % weekLength;
        if (cellsAtEnd > 0) {
            cellsAtEnd = weekLength - cellsAtEnd;
        }

        _startCellIndex = shift;
        _endCellIndex = shift + daysInMonth;
        _totalCells = daysInMonth + shift + cellsAtEnd;
    }

    @Override
    public int getCount() {
        return _totalCells;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView day = new TextView(_context);
        day.setGravity(Gravity.CENTER);
        day.setPadding(0, 8, 0, 8);
        //day.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final int dayOfMonth = position - _startCellIndex + 1;

        //Log.d(LOG_TAG, _calendar.getMonths()[_month - 1 % _calendar.getNumMonths()] + ": " + daysInPreviousMonth);

        // TODO: What happens when onClick?
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //_calendar.setDayOfMonth(dayOfMonth);
            }
        });

        String text;

        if (position < _startCellIndex) {
            text = String.valueOf(_daysInPreviousMonth - _startCellIndex + position + 1);
        }
        else if (position >= _endCellIndex) {
            text = String.valueOf(position - _endCellIndex  + 1);
        }
        else {
            text = String.valueOf(dayOfMonth);
            if (_year == _calendar.getYear() && _month == _calendar.getMonth() && dayOfMonth == _calendar.getDayOfMonth()) {
                // TODO: Make these getColor(..) calls compatible with all APIs
                day.setBackgroundColor(_context.getResources().getColor(R.color.colorPrimary));
            }
            else {
                day.setBackgroundColor(_context.getResources().getColor(R.color.colorGreyLight));
            }
        }

        day.setText(text);

        return day;
    }
}
