package com.jvanoverwalle.fantasycalendar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jvanoverwalle.fantasycalendar.models.Calendar;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Jan Van Overwalle on 27/09/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DbHelper.class.getCanonicalName() + "-debug";
    private static DbHelper _instance = null;

    public static DbHelper getInstance(Context context) {
        if (_instance == null) {
            _instance = new DbHelper(context.getApplicationContext());
        }
        return _instance;
    }

    private DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "onCreate database");
        db.execSQL(DbContract.CalendarTable.SQL_CREATE_ENTRIES);

        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LOG_TAG, "onUpgrade database");
        db.execSQL(DbContract.CalendarTable.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void flushDatabase() {
        Log.d(LOG_TAG, "Flushing database '" + DbContract.DATABASE_NAME + "'...");
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(DbContract.CalendarTable.SQL_DELETE_ENTRIES);

        db.execSQL(DbContract.CalendarTable.SQL_CREATE_ENTRIES);

        seedData(db);
    }

    private void seedData(SQLiteDatabase db) {
        Log.d(LOG_TAG, "seeding data");
        Calendar c = Calendar.loadFromJson("{\"name\":\"Elderan\",\"year_len\":364,\"events\":1,\"n_months\":12,\"months\":[\"Morning Star\",\"Sun's Dawn\",\"First Seed\",\"Rain's Hand\",\"Second Seed\",\"Mid Year\",\"Sun's Height\",\"Last Seed\",\"Hearthfire\",\"Frost Fall\",\"Sun's Dusk\",\"Evening Star\"],\"month_len\":{\"Morning Star\":30,\"Sun's Dawn\":30,\"First Seed\":31,\"Rain's Hand\":30,\"Second Seed\":30,\"Mid Year\":31,\"Sun's Height\":30,\"Last Seed\":30,\"Hearthfire\":31,\"Frost Fall\":30,\"Sun's Dusk\":30,\"Evening Star\":31},\"week_len\":7,\"weekdays\":[\"Morndas\",\"Tirdas\",\"Middas\",\"Turdas\",\"Fredas\",\"Loredas\",\"Sundas\"],\"n_moons\":2,\"moons\":[\"Masser\",\"Secunda\"],\"lunar_cyc\":{\"Masser\":12,\"Secunda\":25},\"lunar_shf\":{\"Masser\":0,\"Secunda\":0},\"year\":2026,\"month\":3,\"day_of_month\":11,\"first_day\":0,\"notes\":{\"2026-3-11\":\"Start of Campaign\"}}");

        insertCalender(db, c);

        c.loadEarth();

        insertCalender(db, c);
    }

    private ContentValues createContentValues(Calendar c) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbContract.CalendarTable.COL_NAME, c.getName());
        contentValues.put(DbContract.CalendarTable.COL_YEAR_LENGTH, c.getYearLength());
        contentValues.put(DbContract.CalendarTable.COL_EVENTS, c.hasEvents());
        contentValues.put(DbContract.CalendarTable.COL_NUM_MONTHS, c.getNumMonths());
        contentValues.put(DbContract.CalendarTable.COL_MONTHS, c.getMonthsJSON().toString()); // Convert to JSON string
        contentValues.put(DbContract.CalendarTable.COL_MONTH_LENGTHS, c.getMonthLengthsJSON().toString()); // Convert to JSON string
        contentValues.put(DbContract.CalendarTable.COL_WEEK_LENGTH, c.getWeekLength());
        contentValues.put(DbContract.CalendarTable.COL_WEEKDAYS, c.getWeekdaysJSON().toString()); // Convert to JSON string
        contentValues.put(DbContract.CalendarTable.COL_NUM_MOONS, c.getNumMoons());
        contentValues.put(DbContract.CalendarTable.COL_MOONS, c.getMoonsJSON().toString()); // Convert to JSON string
        contentValues.put(DbContract.CalendarTable.COL_LUNAR_CYCLES, c.getLunarCyclesJSON().toString()); // Convert to JSON string
        contentValues.put(DbContract.CalendarTable.COL_LUNAR_SHIFTS, c.getLunarShiftsJSON().toString()); // Convert to JSON string
        contentValues.put(DbContract.CalendarTable.COL_YEAR, c.getYear());
        contentValues.put(DbContract.CalendarTable.COL_MONTH, c.getMonth());
        contentValues.put(DbContract.CalendarTable.COL_DAY_OF_MONTH, c.getDayOfMonth());
        contentValues.put(DbContract.CalendarTable.COL_FIRST_DAY, c.getFirstDay());
        contentValues.put(DbContract.CalendarTable.COL_NOTES, c.getNotesJSON().toString()); // Convert to JSON string

        return contentValues;
    }

    private int insertCalender (SQLiteDatabase db, Calendar c) {
        Log.d(LOG_TAG, "Inserting new calendar record in db '" + DbContract.CalendarTable.TABLE + "'");

        ContentValues contentValues = createContentValues(c);

        long id = -1;
        try {
            id = db.insertOrThrow(DbContract.CalendarTable.TABLE, null, contentValues);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) id;
    }

    public int insertCalendar(Calendar c) {
        SQLiteDatabase db = this.getWritableDatabase();
        return insertCalender(db, c);
    }

    private int updateCalendar(SQLiteDatabase db, Calendar c) {
        Log.d(LOG_TAG, "Updating calendar record id '" + c.getId() + "' in db '" + DbContract.CalendarTable.TABLE + "'");

        ContentValues contentValues = createContentValues(c);

        int affectedRows = db.update(DbContract.CalendarTable.TABLE, contentValues, "_id = ?", new String[]{Integer.toString(c.getId())});

        return affectedRows;
    }

    public int updateCalendar (Calendar c) {
        SQLiteDatabase db = this.getWritableDatabase();
        return updateCalendar(db, c);
    }

    private int deleteCalendar(SQLiteDatabase db, Calendar c) {
        Log.d(LOG_TAG, "Deleting calendar record id '" + c.getId() + "' from db '" + DbContract.CalendarTable.TABLE + "'");

        int affectedRows = db.delete(DbContract.CalendarTable.TABLE, "_id = ?", new String[]{Integer.toString(c.getId())});

        return affectedRows;
    }

    public int deleteCalendar (Calendar c) {
        SQLiteDatabase db = this.getWritableDatabase();
        return deleteCalendar(db, c);
    }

    private ArrayList<Calendar> getAllCalendars(SQLiteDatabase db) {
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();

        Cursor res =  db.rawQuery( "SELECT * FROM " + DbContract.CalendarTable.TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            Calendar c = new Calendar();

            c.setId(res.getInt(res.getColumnIndex(DbContract.CalendarTable._ID)));
            c.setName(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_NAME)));
            c.setYearLength(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_YEAR_LENGTH)));
            c.setEvents(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_EVENTS)) > 0);
            c.setNumMonths(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_NUM_MONTHS)));
            c.setMonths(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_MONTHS)));
            c.setMonthLengths(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_MONTH_LENGTHS)));
            c.setWeekLength(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_WEEK_LENGTH)));
            c.setWeekdays(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_WEEKDAYS)));
            c.setNumMoons(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_NUM_MOONS)));
            c.setMoons(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_MOONS)));
            c.setLunarCycles(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_LUNAR_CYCLES)));
            c.setLunarShifts(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_LUNAR_SHIFTS)));
            c.setYear(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_YEAR)));
            c.setMonth(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_MONTH)));
            c.setDayOfMonth(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_DAY_OF_MONTH)));
            c.setFirstDay(res.getInt(res.getColumnIndex(DbContract.CalendarTable.COL_FIRST_DAY)));
            c.setNotes(res.getString(res.getColumnIndex(DbContract.CalendarTable.COL_NOTES)));

            calendars.add(c);
            res.moveToNext();

            //Log.d(TAG, "[ " + id + ", " + name + ", " + charClass + ", " + race + " ]");
        }
        res.close();
        return calendars;
    }

    public ArrayList<Calendar> getAllCalendars()  {
        SQLiteDatabase db = this.getReadableDatabase();
        return getAllCalendars(db);
    }
}
