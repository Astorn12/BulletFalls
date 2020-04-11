package com.example.user.bulletfalls.Game.Elements.Ability;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Ability.ObserverInterfaces.AbilityProgressObserved;
import com.example.user.bulletfalls.Game.Elements.Ability.ObserverInterfaces.AbilityProgressObserver;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

/**Klasa podstawowa dla wszystkich kulek herosów, przeciwników i besti   Bullets and Characters*/
public class Ability extends androidx.appcompat.widget.AppCompatImageView {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);

    @JsonIgnore
    int renewalUpdateProgress;

    boolean ready;
    AbilityRestorTask task;

    AbilitySpecyfication abilitySpecyfication;


    public Ability(Context context, AbilitySpecyfication abilitySpecyfication) {
        super(context);
        this.abilitySpecyfication = abilitySpecyfication;
        //this.setImageResource(abilitySpecyfication.getImageResources());
        Glide.with(context).load(abilitySpecyfication.getImage()).into(this);
        this.ready=true;
        this.setBackgroundColor(Color.WHITE);
        this.renewalUpdateProgress=100;

        checkActivation();
    }


    private void setMovingTransparency(int progress)//zby nie efektywna
    {
        setAlpha(255);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), abilitySpecyfication.getImage());
        bitmap=bitmap.copy( Bitmap.Config.ARGB_8888 , true);

        int relativeX= (int) (bitmap.getHeight()*((float)progress/100));
        for(int i=0;i<relativeX;i++)
        {
            for(int j=0;j<bitmap.getWidth();j++)
            {
                int tmp=bitmap.getPixel(i,j);

                bitmap.setPixel(i,j,Color.TRANSPARENT);
            }
        }
       this.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
    }
    private void movingBackground(int progress)// nie działą
    {
        int x=(int)((float)this.getHeight()*(float)((100-(float)progress)/100));
        if(x==0)x=1;
        Bitmap original = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(original);
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        Paint background= new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawBitmap(original,0,0,background);
        int relativX= (int) (original.getHeight()*((float)progress/100));
        canvas.drawRect(0,0,original.getWidth(),original.getHeight()-relativX,paint);

        Drawable d = new BitmapDrawable(original);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.setBackground(d);
        }
    }

        public AbilitySpecyfication getAbilitySpecyfication() {
            return abilitySpecyfication;
        }

        public void setAbilitySpecyfication(AbilitySpecyfication abilitySpecyfication) {
            this.abilitySpecyfication = abilitySpecyfication;
        }



    public void checkActivation()
    {
        if(this.abilitySpecyfication.isActive())
        {
            activate();
        }
        else
        deactivate();
    }
    public void activate()
    {
        final Ability av=this;
        //((Game)this.getContext()).clearColorFilter(this);
        ((Activity)this.getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                av.clearColorFilter();
            }
        });
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void deactivate()
    {
        int shadow=Color.argb(125, 0, 0, 0);
        this.setColorFilter(shadow);
        //this.setBackgroundColor(shadow);
    }
    public void cancelThread() {
        if(task!=null)
            this.task.cancel(true);

    }

    public AbilitySpecyfication getSpecyfication(){
        return this.abilitySpecyfication;
    }

    public void addAction(EyeOnGame eyeOnGame) {
        if(this.ready) {
            this.getAbilitySpecyfication().addAction(eyeOnGame);

            if (abilitySpecyfication.getRenewalTime() > 0) {
                this.setReady(false);
                AbilityRestorTask art=new AbilityRestorTask();
                //this.task=art;
                art.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }

    }

    public void setRenewalUpdateProgress(int renewalUpdateProgress) {
        if(renewalUpdateProgress>=0&&renewalUpdateProgress<=100) {
            this.renewalUpdateProgress = renewalUpdateProgress;
        }
    }

    public void updateRenewalProgress(int alfa)
    {
        setRenewalUpdateProgress(alfa);
        update(this.renewalUpdateProgress);
        // executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void update(int progress) {

        movingBackground(progress);

    }








    public class AbilityRestorTask extends AsyncTask<Integer,Integer,Boolean> {
        @Override
        protected Boolean doInBackground(Integer... integers) {
            //for(int i=0;i<100;i++)
            // {
            try {
                for(int i=0;i<100;i++) {
                    sleep(abilitySpecyfication.getRenewalTime()/100);
                    publishProgress(i);
                    Log.d("AsyncTask",i+"");

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {

            updateRenewalProgress(progress[0]);

        }
        @Override
        protected void onPostExecute(Boolean result) {
            setReady(true);
        }


    }
}
