package com.example.user.bulletfalls.Objects;

import android.os.AsyncTask;

import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;

import static java.lang.Thread.sleep;

public class AbilitySpecyficationChangedView extends AbilitySpecyfication {
    @Override
    public void doToCharacter(Character character)
    {
        super.doToCharacter(character);

    }

    public class ChangingViewTask extends AsyncTask<Integer,Integer,Boolean> {
        @Override
        protected Boolean doInBackground(Integer... integers) {

            try {
                    sleep(getRenewalTime());
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

        }


    }

}
