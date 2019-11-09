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
import android.os.Build;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;

/**Klasa podstawowa dla wszystkich kulek herosów, przeciwników i besti   Bullets and Characters*/
public class Ability extends androidx.appcompat.widget.AppCompatImageView {

    AbilitySpecyfication abilitySpecyfication;
    public Ability(Context context, AbilitySpecyfication abilitySpecyfication) {
        super(context);
        this.abilitySpecyfication = abilitySpecyfication;
        //this.setImageResource(abilitySpecyfication.getImageResources());
        Glide.with(context).load(abilitySpecyfication.getImage()).into(this);

        this.setBackgroundColor(Color.WHITE);
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


    public void deactivate()
    {
        int shadow=Color.argb(125, 0, 0, 0);
        this.setColorFilter(shadow);
        //this.setBackgroundColor(shadow);
    }

    public AbilitySpecyfication getSpecyfication(){
        return this.abilitySpecyfication;
    }

}
