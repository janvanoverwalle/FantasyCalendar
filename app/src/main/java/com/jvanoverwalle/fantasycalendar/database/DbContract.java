package com.jvanoverwalle.fantasycalendar.database;

import android.provider.BaseColumns;

/**
 * Created by Jan Van Overwalle on 27/09/2017.
 */

public class DbContract {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fantasy_calendar.db";
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String FLOAT_TYPE = " REAL";
    public static final String BOOL_TYPE = " BOOLEAN";
    public static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class, give it an empty constructor.
    public DbContract() {}

    // Inner class that defines the table contents
    public static abstract class CalendarTable implements BaseColumns {
        public static final String TABLE = "calendars";
        public static final String COL_NAME = "name";
        public static final String COL_YEAR_LENGTH = "year_length";
        public static final String COL_EVENTS = "events";
        public static final String COL_NUM_MONTHS = "num_months";
        public static final String COL_MONTHS = "months"; // JSON string
        public static final String COL_MONTH_LENGTHS = "month_lengths"; // JSON string
        public static final String COL_WEEK_LENGTH = "week_length";
        public static final String COL_WEEKDAYS = "weekdays"; // JSON string
        public static final String COL_NUM_MOONS = "num_moons";
        public static final String COL_MOONS = "moons"; // JSON string
        public static final String COL_LUNAR_CYCLES = "lunar_cycles"; // JSON string
        public static final String COL_LUNAR_SHIFTS = "lunar_shifts"; // JSON string
        public static final String COL_YEAR = "year";
        public static final String COL_FIRST_DAY = "first_day";
        public static final String COL_NOTES = "notes"; // JSON string

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COL_NAME + TEXT_TYPE + COMMA_SEP +
                        COL_YEAR_LENGTH + INT_TYPE + COMMA_SEP +
                        COL_EVENTS + BOOL_TYPE + COMMA_SEP +
                        COL_NUM_MONTHS + INT_TYPE + COMMA_SEP +
                        COL_MONTHS + TEXT_TYPE + COMMA_SEP +
                        COL_MONTH_LENGTHS + TEXT_TYPE + COMMA_SEP +
                        COL_WEEK_LENGTH + INT_TYPE + COMMA_SEP +
                        COL_WEEKDAYS + TEXT_TYPE + COMMA_SEP +
                        COL_NUM_MOONS + INT_TYPE + COMMA_SEP +
                        COL_MOONS + TEXT_TYPE + COMMA_SEP +
                        COL_LUNAR_CYCLES + FLOAT_TYPE + COMMA_SEP +
                        COL_LUNAR_SHIFTS + INT_TYPE + COMMA_SEP +
                        COL_YEAR + INT_TYPE + COMMA_SEP +
                        COL_FIRST_DAY + INT_TYPE + COMMA_SEP +
                        COL_NOTES + TEXT_TYPE +
                        " )";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE;
    }
}
