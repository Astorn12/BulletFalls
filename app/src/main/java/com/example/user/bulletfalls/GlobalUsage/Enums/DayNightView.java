package com.example.user.bulletfalls.GlobalUsage.Enums;

import com.example.user.bulletfalls.R;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.util.Calendar;

public enum DayNightView {
    HEART(R.drawable.hearticonday,R.drawable.hearticonnigt);
    private final int day;
    private final int night;

    DayNightView(int day, int night) {
        this.day=day;
        this.night=night;
    }

    public int getValue()
    {
        Location location= new Location( 52.2297700,21.0117800);//współrzędne geograficzne warszawy
        SunriseSunsetCalculator calculator= new SunriseSunsetCalculator(location,"Poland/Warsaw");

        Calendar sunrise=calculator.getOfficialSunriseCalendarForDate(Calendar.getInstance());
        Calendar sunset=calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance());



        if(Calendar.getInstance().after(sunrise)&&Calendar.getInstance().before(sunset))
        {
            return day;
        }
        else return night;
    }


}
