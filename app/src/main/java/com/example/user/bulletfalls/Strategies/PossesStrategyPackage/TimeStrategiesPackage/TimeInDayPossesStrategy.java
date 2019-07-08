package com.example.user.bulletfalls.Strategies.PossesStrategyPackage.TimeStrategiesPackage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Calendar;
import java.util.Date;
@JsonTypeName("timeindaypossesstrategy")
public class TimeInDayPossesStrategy implements TimeStrategies{

    int minFrom;
    int hFrom;
    int minTo;
    int hTo;

    public TimeInDayPossesStrategy( int hFrom,int minFrom, int hTo, int minTo) {
        this.minFrom = minFrom;
        this.hFrom = hFrom;
        this.minTo = minTo;
        this.hTo = hTo;

        /**Tutaj powinna być jeszcze walidacja danych*/

    }

    private TimeInDayPossesStrategy() {
    }
    @JsonIgnore
    @Override
    public boolean isInTime() {

        Date now= Calendar.getInstance().getTime();
        Calendar nowc= Calendar.getInstance();
        nowc.setTime(now);

        int minNow=nowc.get(Calendar.MINUTE);
        int hNow=nowc.get(Calendar.HOUR_OF_DAY);

        if(hTo>hFrom)
        {
            if(hNow>hFrom && hNow<hTo)
            {
                    return true;
            }else if(hNow>hFrom&&hNow==hTo){
                if(minNow<=minTo) return true;
                else return false;

            }else if(hNow<hTo&&hNow==hFrom){
                if(minFrom>minNow)return true;
                else return false;

            }else return false;

        }else if(hTo==hFrom){
            if(hNow==hTo&&minNow>=minFrom&&minNow<=minFrom)
            {
                return true;
            }
            else return false;
        }

        else
        {
            return false;
        }
    }

    @Override
    public double howSoon() {
        return 0;
    }

    public int getMinFrom() {
        return minFrom;
    }

    public void setMinFrom(int minFrom) {
        this.minFrom = minFrom;
    }

    public int gethFrom() {
        return hFrom;
    }

    public void sethFrom(int hFrom) {
        this.hFrom = hFrom;
    }

    public int getMinTo() {
        return minTo;
    }

    public void setMinTo(int minTo) {
        this.minTo = minTo;
    }

    public int gethTo() {
        return hTo;
    }

    public void sethTo(int hTo) {
        this.hTo = hTo;
    }
}
