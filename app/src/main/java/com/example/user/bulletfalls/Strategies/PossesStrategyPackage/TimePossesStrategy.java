package com.example.user.bulletfalls.Strategies.PossesStrategyPackage;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Calendar;
import java.util.Date;
@JsonTypeName("timepossesstrategy")
public class TimePossesStrategy implements PossesStrategy {
    Date dateFrom;
    Date dateTo;
    public TimePossesStrategy(Date dateFrom,Date dateTo)
    {
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
    }
    //możliwosć określenia zdobycia postaci poprzez dzień tygodnia i godzinę
    @Override
    public boolean tryToPosses(UserProfile userProfile) {

        Calendar dateFromc= Calendar.getInstance();
        dateFromc.setTime(dateFrom);

        Calendar dateToc= Calendar.getInstance();
        dateToc.setTime(dateTo);

        Date now= Calendar.getInstance().getTime();
        Calendar nowc= Calendar.getInstance();
        dateFromc.setTime(now);

        int dayOfWeekFrom=dateFromc.get(Calendar.DAY_OF_WEEK);
        int dayOfWeekTo=dateToc.get(Calendar.DAY_OF_WEEK);
        int dayOfWeekN=nowc.get(Calendar.DAY_OF_WEEK);


        int hourFrom=dateFromc.get(Calendar.HOUR_OF_DAY);
        int hourTo=dateToc.get(Calendar.HOUR_OF_DAY);
        int hourN=nowc.get(Calendar.HOUR_OF_DAY);

        int minuteFrom=dateFromc.get(Calendar.MINUTE);
        int minuteTo=dateToc.get(Calendar.MINUTE);
        int minuteN=nowc.get(Calendar.MINUTE);


        if(dayOfWeekFrom!=0&&dayOfWeekTo!=0)
        {
            if(hourFrom==0||hourTo==0) {
                if (dayOfWeekN >= dayOfWeekFrom && dayOfWeekN <= dayOfWeekTo) {
                    return true;
                } else return false;
            }
            else
            {
                if (dayOfWeekN >= dayOfWeekFrom && dayOfWeekN <= dayOfWeekTo && hourN>=hourFrom && hourN<=hourTo) {
                    return true;
                } else return false;
            }
        }
        else if(hourFrom!=0&&hourTo!=0)
        {
            if (hourN>=hourFrom && hourN<=hourTo) {
                return true;
            } else return false;
        }
        return false;
    }

    @Override
    public void setPossesFotter(LinearLayout linearLayout, Context context) {

    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
