package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.os.AsyncTask;

import com.example.user.bulletfalls.ObjectsOfGame.Character;
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
}
