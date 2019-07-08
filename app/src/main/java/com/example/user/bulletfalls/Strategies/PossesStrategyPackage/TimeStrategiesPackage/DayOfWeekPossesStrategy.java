package com.example.user.bulletfalls.Strategies.PossesStrategyPackage.TimeStrategiesPackage;

import com.example.user.bulletfalls.Exceptions.NotExistedDayOfWeek;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Calendar;
import java.util.Date;
@JsonTypeName("dayofweekpossesstrategy")
public class DayOfWeekPossesStrategy implements TimeStrategies {

    int dayOfTheWeek;


    public DayOfWeekPossesStrategy(int dayOfTheWeek) {


        if(dayOfTheWeek<8 &&dayOfTheWeek>0)
        {
            this.dayOfTheWeek = dayOfTheWeek;
        }

        else try {
            throw new NotExistedDayOfWeek(dayOfTheWeek);
        } catch (NotExistedDayOfWeek notExistedDayOfWeek) {
            notExistedDayOfWeek.printStackTrace();
            //this.dayOfTheWeek=1 ;//ustawienie poniedziałku w przypadku złego podania daty
        }

    }

    public DayOfWeekPossesStrategy() {
    }

    @JsonIgnore
    @Override
    public boolean isInTime() {

        Date now= Calendar.getInstance().getTime();
        Calendar nowc= Calendar.getInstance();
        nowc.setTime(now);



        if(this.dayOfTheWeek==nowc.get(Calendar.DAY_OF_WEEK)-1)
        {
            return true;
        }
        return false;
    }

    @Override
    public double howSoon() {
        Date now= Calendar.getInstance().getTime();
        Calendar nowc= Calendar.getInstance();
        nowc.setTime(now);
        int nowwww=nowc.get(Calendar.DAY_OF_WEEK);

        int distance=(this.getDayOfTheWeek()- (nowc.get(Calendar.DAY_OF_WEEK)-1)+7)%7;
        return (7-distance)/7;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }
}
