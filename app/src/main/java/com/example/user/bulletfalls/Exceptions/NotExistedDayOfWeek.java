package com.example.user.bulletfalls.Exceptions;

public class NotExistedDayOfWeek extends Exception {

    int fakeDay;
    public NotExistedDayOfWeek(int fakeDay)
    {
        this.fakeDay=fakeDay;
    }

    @Override
    public String toString()
    {
        return "Try to assign number "+this.fakeDay+" as a day of the week, day of week have to be integer between (1,7)";
    }
}
