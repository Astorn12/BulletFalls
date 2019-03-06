package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.os.AsyncTask;

import com.example.user.bulletfalls.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Thread.sleep;
@JsonTypeName("disarm")
public class Disarm implements BulletDoToCharacterStrategy {
    //blokuje umiejęctonośc strzelania przeciwnika na okreslony czas
    int disarmedTime;
    public Disarm(int time)
    {
        this.disarmedTime=time;
    }
    public Disarm(){};


    @Override
    public void doToCharacter(final Character character) {
        character.setShootAble(false);

        AsyncTask asyncTask= new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    //sleep(disarmedTime);
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                character.setShootAble(true);
                return null;
            }


        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);



    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new Disarm(this.disarmedTime);
    }

    public int getDisarmedTime() {
        return disarmedTime;
    }

    public void setDisarmedTime(int disarmedTime) {
        this.disarmedTime = disarmedTime;
    }
}
