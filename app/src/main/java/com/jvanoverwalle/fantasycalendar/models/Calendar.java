package com.jvanoverwalle.fantasycalendar.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jan Van Overwalle on 26/09/2017.
 */

public class Calendar {
    //region Example Json Data
    /*
    {
       "yearlen":364,
       "events":1,
       "nmonths":12,
       "months":[
          "Morning Star",
          "Sun's Dawn",
          "First Seed",
          "Rain's Hand",
          "Second Seed",
          "Mid Year",
          "Sun's Height",
          "Last Seed",
          "Hearthfire",
          "Frost Fall",
          "Sun's Dusk",
          "Evening Star"
       ],
       "monthlen":{
          "Morning Star":30,
          "Sun's Dawn":30,
          "First Seed":31,
          "Rain's Hand":30,
          "Second Seed":30,
          "Mid Year":31,
          "Sun's Height":30,
          "Last Seed":30,
          "Hearthfire":31,
          "Frost Fall":30,
          "Sun's Dusk":30,
          "Evening Star":31
       },
       "weeklen":7,
       "weekdays":[
          "Morndas",
          "Tirdas",
          "Middas",
          "Turdas",
          "Fredas",
          "Loredas",
          "Sundas"
       ],
       "nmoons":2,
       "moons":[
          "Masser",
          "Secunda"
       ],
       "lunarcyc":{
          "Masser":12,
          "Secunda":25
       },
       "lunarshf":{
          "Masser":0,
          "Secunda":0
       },
       "year":2026,
       "firstday":0,
       "notes":{
          "2026-1-1":"Test"
       }
    }
     */
    //endregion

    private String name;

    private int yearLength;
    private boolean events;

    private int numMonths;
    private String[] months;
    private Map<String, Integer> monthLengths;

    private int weekLength;
    private String[] weekDays;

    private int numMoons;
    private String[] moons;

    private Map<String, Float> lunarCycles;
    private Map<String, Integer> lunarShifts;

    private int year;
    private int firstDay;

    private Map<String, String> notes;

    public Calendar() {
        this("n/a");
    }

    public Calendar(String name) {
        this.name = name;

        this.yearLength = 0;
        this.events = false;

        this.numMonths = 0;
        this.months = new String[numMonths];
        this.monthLengths = new HashMap<String, Integer>();

        this.weekLength = 0;
        this.weekDays = new String[weekLength];

        this.numMoons = 0;
        this.moons = new String[numMoons];

        this.lunarCycles = new HashMap<String, Float>();
        this.lunarShifts = new HashMap<String, Integer>();

        this.year = 0;
        this.firstDay = 0;

        this.notes = new HashMap<String, String>();
    }

    public void loadEarth() {
        name = "Earth";

        yearLength = 365;
        events = true;

        numMonths = 12;
        months = new String[numMonths];
        months[0] = "January";
        months[1] = "February";
        months[2] = "March";
        months[3] = "April";
        months[4] = "May";
        months[5] = "June";
        months[6] = "July";
        months[7] = "August";
        months[8] = "September";
        months[9] = "October";
        months[10] = "November";
        months[11] = "December";
        monthLengths = new HashMap<String, Integer>();
        monthLengths.put("January",31);
        monthLengths.put("February",28);
        monthLengths.put("March",31);
        monthLengths.put("April",30);
        monthLengths.put("May",31);
        monthLengths.put("June",30);
        monthLengths.put("July",31);
        monthLengths.put("August",31);
        monthLengths.put("September",30);
        monthLengths.put("October",31);
        monthLengths.put("November",30);
        monthLengths.put("December",31);

        weekLength = 7;
        weekDays = new String[weekLength];
        weekDays[0] = "Sunday";
        weekDays[1] = "Monday";
        weekDays[2] = "Tuesday";
        weekDays[3] = "Wednesday";
        weekDays[4] = "Thursday";
        weekDays[5] = "Friday";
        weekDays[6] = "Saturday";

        numMoons = 1;
        moons = new String[numMoons];
        moons[0] = "Luna";

        lunarCycles = new HashMap<String, Float>();
        lunarCycles.put("Luna", 29.53f);
        lunarShifts = new HashMap<String, Integer>();
        lunarShifts.put("Luna", 20);

        year = 2017;
        firstDay = 0;

        notes = new HashMap<String, String>();
    }

    //region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearLength() {
        return yearLength;
    }

    public void setYearLength(int yearLength) {
        this.yearLength = yearLength;
    }

    public boolean hasEvents() {
        return events;
    }

    public void setEvents(boolean events) {
        this.events = events;
    }

    public int getNumMonths() {
        return numMonths;
    }

    public void setNumMonths(int numMonths) {
        this.numMonths = numMonths;
    }

    public String[] getMonths() {
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }

    public Map<String, Integer> getMonthLengths() {
        return monthLengths;
    }

    public void setMonthLengths(Map<String, Integer> monthLengths) { this.monthLengths = monthLengths;  }

    public int getWeekLength() {
        return weekLength;
    }

    public void setWeekLength(int weekLength) {
        this.weekLength = weekLength;
    }

    public String[] getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String[] weekDays) {
        this.weekDays = weekDays;
    }

    public int getNumMoons() {
        return numMoons;
    }

    public void setNumMoons(int numMoons) {
        this.numMoons = numMoons;
    }

    public String[] getMoons() {
        return moons;
    }

    public void setMoons(String[] moons) {
        this.moons = moons;
    }

    public Map<String, Float> getLunarCycles() {
        return lunarCycles;
    }

    public void setLunarCycles(Map<String, Float> lunarCycles) {
        this.lunarCycles = lunarCycles;
    }

    public Map<String, Integer> getLunarShifts() {
        return lunarShifts;
    }

    public void setLunarShifts(Map<String, Integer> lunarShifts) {
        this.lunarShifts = lunarShifts;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(int firstDay) {
        this.firstDay = firstDay;
    }

    public Map<String, String> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, String> notes) {
        this.notes = notes;
    }
    //endregion

    @Override
    public String toString() {
        return name;
    }

    public static Calendar loadFromJson(String jsonData) {
        Calendar c = new Calendar();

        try {
            JSONObject jsonObject = new JSONObject(jsonData);

            c.setYearLength(jsonObject.getInt("year_len"));
            c.setEvents(jsonObject.getInt("events") == 1);

            c.setNumMonths(jsonObject.getInt("n_months"));
            JSONArray jsonMonths = jsonObject.getJSONArray("months");
            JSONObject jsonMonthLengths = jsonObject.getJSONObject("month_len");

            String[] months = new String[c.getNumMonths()];
            Map<String, Integer> monthLengths = new HashMap<String, Integer>();

            for (int i = 0; i < jsonMonths.length(); ++i) {
                months[i] = jsonMonths.get(i).toString();
                int monthLength = jsonMonthLengths.getInt(months[i]);
                monthLengths.put(months[i], monthLength);
            }
            c.setMonths(months);
            c.setMonthLengths(monthLengths);

            c.setWeekLength(jsonObject.getInt("week_len"));
            JSONArray jsonWeeks = jsonObject.getJSONArray("weekdays");

            String[] weeks = new String[c.getWeekLength()];

            for (int i = 0; i < jsonWeeks.length(); ++i) {
                weeks[i] = jsonWeeks.get(i).toString();
            }
            c.setWeekDays(weeks);

            c.setNumMoons(jsonObject.getInt("n_moons"));
            JSONArray jsonMoons = jsonObject.getJSONArray("moons");
            JSONObject jsonLunarCycles = jsonObject.getJSONObject("lunar_cyc");
            JSONObject jsonLunarShifts = jsonObject.getJSONObject("lunar_shf");

            String[] moons = new String[c.getNumMoons()];
            Map<String, Float> lunarCycles = new HashMap<String, Float>();
            Map<String, Integer> lunarShifts = new HashMap<String, Integer>();

            for (int i = 0; i < jsonMoons.length(); ++i) {
                moons[i] = jsonMoons.get(i).toString();

                float lunarCycle = (float) jsonLunarCycles.getDouble(moons[i]);
                int lunarShift = jsonLunarShifts.getInt(moons[i]);

                lunarCycles.put(moons[i], lunarCycle);
                lunarShifts.put(moons[i], lunarShift);

            }
            c.setMoons(moons);
            c.setLunarCycles(lunarCycles);
            c.setLunarShifts(lunarShifts);

            c.setYear(jsonObject.getInt("year"));
            c.setFirstDay(jsonObject.getInt("first_day"));

            JSONObject jsonNotes = jsonObject.getJSONObject("notes");

            Map<String, String> notes = new HashMap<String, String>();

            Iterator<?> keys = jsonNotes.keys();
            while (keys.hasNext()) {
                String key = (String)keys.next();
                String value = jsonNotes.getString(key);

                notes.put(key, value);
            }

            c.setNotes(notes);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return c;
    }
}
