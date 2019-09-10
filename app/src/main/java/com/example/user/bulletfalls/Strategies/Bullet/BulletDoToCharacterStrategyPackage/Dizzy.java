package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;
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

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Kulka zatrzymuje przeciwnika i blokuje mu strzelanie na "+this.stuneTime/1000+"s");
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }
}
