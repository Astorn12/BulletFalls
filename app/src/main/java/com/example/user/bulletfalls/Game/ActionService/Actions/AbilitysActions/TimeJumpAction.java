package com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions;

import android.os.AsyncTask;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import static java.lang.Thread.sleep;

public class TimeJumpAction extends Action {
    long dressUpTime;
    long lastTimedUse;

    public TimeJumpAction(ActionType type, long dressUpTime, long lastTimedUse) {
        super(type);
        this.dressUpTime = dressUpTime;
        this.lastTimedUse = lastTimedUse;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {

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
