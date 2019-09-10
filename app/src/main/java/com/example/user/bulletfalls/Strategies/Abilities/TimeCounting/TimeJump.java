package com.example.user.bulletfalls.Strategies.Abilities.TimeCounting;

import android.os.AsyncTask;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Supporters.SupporterDrawableNaming;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Calendar;

import static java.lang.Thread.sleep;
@JsonTypeName("timejump")
public class TimeJump implements DoToCharacterStrategy {
    long dressUpTime;
    long lastTimedUse;
    public TimeJump(long time) {
        this();
        this.dressUpTime = time;
    }
    /**ładowanie atrybutów któe nie są ustawiane w konstruktorze dobrze zamieścić w konstruktorze domyślnym private, i użyć this() w każdym innym konstruktorze
     * będzie to przydatne gdy będziemy mieli więcej niż jeden konstruktor w klasie, dodanie nowego atrybutu u zaininjowanie pusten wartosci
     * będzie mogło się odbywać tylko w atrybucie publicznym, dzięki temu kod będziemy pisać tylko raz*/
    private TimeJump(){this.lastTimedUse=0;}
    @Override
    public void doToCharacter(Character character) {


        long reduction= dressUpTime-System.currentTimeMillis()+this.lastTimedUse;
        long jumpTime=this.dressUpTime;
        if(reduction>0)
            jumpTime-=reduction;
        int lifeToRestore=((Game) character.getContext()).getController().getMedium().timeJumpHeal(jumpTime);
        System.out.println("Life to restore: "+ lifeToRestore);
        character.heal(lifeToRestore);
        SupporterDrawableNaming sup= new SupporterDrawableNaming();
        String drawableName="young"+ character.getIndyvidualHeroMarker();
        drawableName=sup.validateDrawableString(drawableName);
        //drawableName=sup.validateDrawableString(drawableName);
        if(sup.exist(drawableName, character.getContext()))
        {
            int dress=sup.getDrawableNumber(drawableName, character.getContext());
            growYounger(dress, character);
        }
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public long getDressUpTime() {
        return dressUpTime;
    }

    public void setDressUpTime(long dressUpTime) {
        this.dressUpTime = dressUpTime;
    }
private void growYounger(final int resource,final Character character)
    {

        character.dressUp(resource);

        final long tmpDressUpTime=this.dressUpTime;

        AsyncTask asyncTask= new AsyncTask<Object,Integer,Boolean>()  {

            @Override
            protected Boolean doInBackground(Object... integers) {
                try {
                    sleep(tmpDressUpTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onProgressUpdate(Integer... progress) {

                //updateRenewalProgress(progress[0]);

            }
            @Override
            protected void onPostExecute(Boolean result) {
                character.strip(resource);
                lastTimedUse= System.currentTimeMillis();

            }



        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
