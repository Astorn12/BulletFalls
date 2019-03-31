package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.user.bulletfalls.ObjectsOfGame.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Thread.sleep;

@JsonTypeName("dizzy")
public class Dizzy implements BulletDoToCharacterStrategy {
//kulki które zatrzymują przeciwnika na określony czas(stuneTime)  po udeżeniu w nich, oraz uniemożliwiają im strzelanie
    int stuneTime;//czas w mili sekundach
    public Dizzy(int time)
    {
        this.stuneTime=time;
    }//jeżeli mix działa to dizzy jest niepotrzebne, to jes tpo prostu mix z stunt i disarm, nie do końca bo brakuje klasy która zatrzymuje
public Dizzy(){}
    @Override
    public void doToCharacter(final Character character) {
        if(character.isShootAble())
        character.setShootAble(false);
        if(character.isMoveAble())
        character.setMoveAble(false);

        @SuppressLint("StaticFieldLeak") AsyncTask asyncTask= new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    //sleep(disarmedTime);
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                character.setShootAble(true);
                character.setMoveAble(true);
                return null;
            }


        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new Dizzy(this.stuneTime);
    }
}
