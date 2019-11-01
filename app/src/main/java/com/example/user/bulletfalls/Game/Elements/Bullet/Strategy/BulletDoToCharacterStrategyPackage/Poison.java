package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage;

import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Thread.sleep;
//kulka która po uderzeniu we wroga zaraża go trucizną
// truciznę ukreśla moc toksyczności -toxicRate, jest to ilość odebranego życia z każdą iteracją
//szybkość działania trucizny -iterationTime, jest to czas kolejnych działań trucizny
//czas utrzymywanai się trucizny w organiźmie
//szybkość rozpoczęcia działania trucizny
@JsonTypeName("poison")
public class Poison implements BulletDoToCharacterStrategy {
    int iterationTime;
    int overalTime;
    int toxicRate;
    int startingTime;

    public Poison(int iterationTime,int overalTime,int toxicRate,int startingTime)
    {
        this.iterationTime=iterationTime;
        this.overalTime=overalTime;
        this.toxicRate=toxicRate;
        this.startingTime=startingTime;
    }
    public Poison() { }
    @Override
    public void doToCharacter(final Character character) {


       AsyncTask<Integer,Integer,Integer> asyncTask=new AsyncTask<Integer,Integer,Integer>() {
            @Override
            protected Integer doInBackground(Integer... params) {
                try {
                    sleep(startingTime);
                    int number=overalTime/iterationTime;
                    for(int i=0;i<number;i++) {
                        sleep(iterationTime);
                       // character.poison(toxicRate);
                        publishProgress(toxicRate);
                        // Log.d("AsyncTask",i+"");

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
           @Override
           protected void onProgressUpdate(Integer... progress) {
                character.poison(progress[0]);
           }
        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new Poison(this.iterationTime,this.overalTime,this.toxicRate,this.startingTime);
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Kulka zatruwa przeciwnika na "+overalTime+" o "+ this.toxicRate+" punktów co "+this.iterationTime/1000+"s");
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }

    public int getIterationTime() {
        return iterationTime;
    }

    public void setIterationTime(int iterationTime) {
        this.iterationTime = iterationTime;
    }

    public int getOveralTime() {
        return overalTime;
    }

    public void setOveralTime(int overalTime) {
        this.overalTime = overalTime;
    }

    public int getToxicRate() {
        return toxicRate;
    }

    public void setToxicRate(int toxicRate) {
        this.toxicRate = toxicRate;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }
}
