package com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy;

import android.os.AsyncTask;

import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Storage.Sets.EnemySet;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;
@JsonTypeName("gnomeappearaction")
public class GnomeAppearAction implements AppearAction {

    Game game;
    @Override
    public void action(Game game) {
        this.game=game;
        if(gnomePowerChecking()) preparationForMutation();

    }

    private boolean gnomePowerChecking()
    {
        EyeOnGame eog=game.getEyeOnGame();
        int counter=0;
        for(EnemySpecyfication e:eog.getReadOnlyCurrentlyEnemyList())
        {

            if(e.isFromGroup(FamilyName.Gnomes))
            {
                counter++;
            }
        }
        if(counter>=3) return true;
        else return false;

    }

    private void preparationForMutation()
    {
        AsyncTask asyncTask= new AsyncTask<Object,Integer,Boolean>()  {

            @Override
            protected Boolean doInBackground(Object... integers) {
                try {
                    sleep(2000);
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
                if(gnomePowerChecking()) mutate();

            }



        };
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void mutate()//pełna magiczna interrakcja w gre
    {
        EyeOnGame eog=game.getEyeOnGame();

        List<EnemySpecyfication> gnomes= new LinkedList<>();
        for(EnemySpecyfication es: eog.getReadOnlyCurrentlyEnemyList())
        {
            if(es.isFromGroup(FamilyName.Gnomes))
            {
                gnomes.add(es);
            }
        }

        Enemy megaGnome=new Enemy(this.game,EnemySet.getInstance().getEnemy("Gnome Monster"));
        List<EnemySpecyfication> mutation= new LinkedList<>();
        mutation.add(megaGnome.getSpecyfication());
        eog.mutate(gnomes,mutation);
    }
}
