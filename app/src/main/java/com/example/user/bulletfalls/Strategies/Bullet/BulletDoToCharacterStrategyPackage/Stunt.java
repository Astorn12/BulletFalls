package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.os.AsyncTask;

import com.example.user.bulletfalls.Character;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Thread.sleep;

@JsonTypeName("stunt")
//kulka spowalnia przeciwnika na określony czas, po udeżeniu w niego
public class Stunt implements BulletDoToCharacterStrategy {
    int stuntTime;
    public Stunt(int time)
    {
        this.stuntTime=time;
    }
    public Stunt(){}
    @Override
    public void doToCharacter(final Character character) {
        character.slowDown(stuntTime);

        AsyncTask asyncTask= new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    //sleep(disarmedTime);
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                character.speedUp(stuntTime);
                return null;
            }


        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new Stunt(this.stuntTime);

        }

    public int getStuntTime() {
        return stuntTime;
    }

    public void setStuntTime(int stuntTime) {
        this.stuntTime = stuntTime;
    }
}
