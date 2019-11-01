package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage;

import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Thread.sleep;
@JsonTypeName("timeslowdownshooting")
public class TimeSlowDownShooting implements BulletDoToCharacterStrategy {

    int shootingDecrease;
    int slowingTime;

    public TimeSlowDownShooting(int shootingDecrease,int slowingTime)
    {
        this.shootingDecrease=shootingDecrease;
        this.slowingTime=slowingTime;
    }

    public TimeSlowDownShooting()
    {

    }

    @Override
    public void doToCharacter(final Character character) {

        character.slowDownShooting(shootingDecrease);
        AsyncTask asyncTask= new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    //sleep(disarmedTime);
                    sleep(slowingTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                character.speedUp(shootingDecrease);
                return null;
            }


        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new TimeSlowDownShooting(this.shootingDecrease,this.slowingTime);
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Kulka spowalnia przeciwnika o "+this.shootingDecrease+" na "+this.slowingTime/1000+"s");
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }
}
