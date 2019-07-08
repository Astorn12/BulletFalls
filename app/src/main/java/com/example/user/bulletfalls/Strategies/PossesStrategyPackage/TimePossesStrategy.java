package com.example.user.bulletfalls.Strategies.PossesStrategyPackage;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.TimeStrategiesPackage.TimeStrategies;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("timepossesstrategy")
public class TimePossesStrategy implements PossesStrategy {
   // Date dateFrom;
    //Date dateTo;
    TimeStrategies timeStrategy;
    public TimePossesStrategy(TimeStrategies timeStrategy)
    {
        this.timeStrategy=timeStrategy;
      //  Date date= new Date();

        //this.dateFrom=dateFrom;
        //this.dateTo=dateTo;
    }
    private TimePossesStrategy()
    {

    }
    //możliwosć określenia zdobycia postaci poprzez dzień tygodnia i godzinę
    @Override
    public boolean tryToPosses(UserProfile userProfile) {

        /*Calendar dateFromc= Calendar.getInstance();
        dateFromc.setDressUpTime(dateFrom);

        Calendar dateToc= Calendar.getInstance();
        dateToc.setDressUpTime(dateTo);

        Date now= Calendar.getInstance().getDressUpTime();
        Calendar nowc= Calendar.getInstance();
        nowc.setDressUpTime(now);

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
        return false;*/

        return this.timeStrategy.isInTime();
    }

    @Override
    public void setPossesFotter(LinearLayout linearLayout, Context context) {
        TextView text= new TextView(context);
        if(this.timeStrategy.isInTime())
        {
            text.setText("Dostępna");
            text.setTextColor(Color.GREEN);
        }
        else {
            text.setText("Niedostępna");
            text.setTextColor(Color.RED);
        }
        LinearLayout isTimeGood= new LinearLayout(context);

        isTimeGood.addView(text);
        ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) text.getLayoutParams();
        params.setMargins(0,0,0,0);

        linearLayout.addView(isTimeGood);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setGravity(Gravity.CENTER);
        ViewGroup.MarginLayoutParams params2=(ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
        params2.setMargins(0,0,0,0);

        linearLayout.setGravity(Gravity.CENTER);

    }

   /* public Date getDateFrom() {
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
    }*/

    public TimeStrategies getTimeStrategy() {
        return timeStrategy;
    }

    public void setTimeStrategy(TimeStrategies timeStrategy) {
        this.timeStrategy = timeStrategy;
    }
}
