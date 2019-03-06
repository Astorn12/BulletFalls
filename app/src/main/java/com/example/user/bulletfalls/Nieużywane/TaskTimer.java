package com.example.user.bulletfalls.Nieużywane;

import android.os.AsyncTask;
import android.util.Log;

import static java.lang.Thread.sleep;

public class TaskTimer extends AsyncTask<Integer,Integer,Boolean> {
    int renewalTime;
    public TaskTimer(int renewalTime) {
        this.renewalTime=renewalTime;
    }


    @Override
    protected Boolean doInBackground(Integer... integers) {
        //for(int i=0;i<100;i++)
        // {
        try {
            //for(int i=0;i<100;i++) {
                //sleep(renewalTime/100);
                sleep(renewalTime);
                //publishProgress(i);
                //Log.d("AsyncTask",i+"");

            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // }
        return null;
    }
    @Override
    protected void onProgressUpdate(Integer... progress) {

        //updateRenewalProgress(progress[0]);

    }
    @Override
    protected void onPostExecute(Boolean result) {
        //setReady(true);
    }


}

