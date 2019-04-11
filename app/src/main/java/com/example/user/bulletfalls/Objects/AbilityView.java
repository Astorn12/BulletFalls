package com.example.user.bulletfalls.Objects;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;

public class AbilityView extends android.support.v7.widget.AppCompatImageView implements Observer,PossesAble {

    Ability ability;
    public AbilityView(Context context,Ability ability) {
        super(context);
        this.ability=ability;
        //this.setImageResource(ability.getImageResources());
        Glide.with(context).load(ability.getImageResources()).into(this);
        ability.addObserver(this);
        this.setBackgroundColor(Color.WHITE);
        checkActivation();
    }

    @Override
    public void update(int progress) {
        movingBackground(progress);
    }

    @Override
    public void announce() {
    }
    private void setMovingTransparency(int progress)//zby nie efektywna
    {
        setAlpha(255);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), ability.getImageResources());
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

        public Ability getAbility() {
            return ability;
        }

        public void setAbility(Ability ability) {
            this.ability = ability;
        }

    @Override
    public PossesStrategy getPossesStrategy() {
        return ability.getPossesStrategy();
    }

    @Override
    public void setPossesStrategy(PossesStrategy possesStrategy) {
        ability.setPossesStrategy(possesStrategy);
    }

    @Override
    public String getName() {
        return ability.getName();
    }

    @Override
    public void setPermission(Permission permission) {
        ability.setPermission(permission);
    }

    @Override
    public Permission getPermission() {
        return ability.getPermission();
    }



    public void checkActivation()
    {
        if(this.ability.isActive())
        {
            activate();
        }
        else
        deactivate();
    }
    public void activate()
    {
        final AbilityView av=this;
        //((Game)this.getContext()).clearColorFilter(this);
        ((Activity)this.getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                av.clearColorFilter();
            }
        });
    }


    public void deactivate()
    {
        int shadow=Color.argb(125, 0, 0, 0);
        this.setColorFilter(shadow);
        //this.setBackgroundColor(shadow);
    }
}