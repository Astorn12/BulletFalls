package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting;

import android.os.AsyncTask;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.TimeJumpAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.EmptyAction;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SupporterDrawableNaming;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static java.lang.Thread.sleep;
@JsonTypeName("timejump")
public class TimeJump implements StartAction {
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
    public Action prepareAction(EyeOnGame eyeOnGame) {


        long reduction= dressUpTime-System.currentTimeMillis()+this.lastTimedUse;
        long jumpTime=this.dressUpTime;
        if(reduction>0)
            jumpTime-=reduction;
        int lifeToRestore=((Game) eyeOnGame.getHero().getContext()).getController().getMedium().timeJumpHeal(jumpTime);
        System.out.println("Life to restore: "+ lifeToRestore);
        eyeOnGame.getHero().heal(lifeToRestore);
        SupporterDrawableNaming sup= new SupporterDrawableNaming();
        String drawableName="young"+ eyeOnGame.getHero().getIndyvidualHeroMarker();
        drawableName=sup.validateDrawableString(drawableName);
        //drawableName=sup.validateDrawableString(drawableName);
        if(sup.exist(drawableName, eyeOnGame.getHero().getContext()))
        {
            //int dress=sup.getDrawableNumber(drawableName, eyeOnGame.getContext());
            return new TimeJumpAction(ActionType.INNER,this.dressUpTime,this.lastTimedUse);
            //growYounger(dress, eyeOnGame);
        }
        return new EmptyAction();
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
