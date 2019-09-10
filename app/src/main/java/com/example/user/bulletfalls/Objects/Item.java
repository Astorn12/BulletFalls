package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.ProfileActivity.Currency;

public class Item extends Dynamic {
    Currency currency;

    boolean showed;
    int xCounter;
    Point centerPoint;
    static final int r=150;
    public Item(Context context, int speed, Point startingPoint, FrameLayout frame,Currency currency) {
        super(context, 0, speed, startingPoint, 80, 80, currency.getResource(), frame, currency.getName());
        this.currency=currency;
        this.xCounter=0;
        this.showed=true;
    }


    public void move() {

      /*  if(xCounter<15)
        {
            ((Game) this.getContext()).moveViewElement(this, 0, -speed);
        }*/
         if(this.centerPoint==null || Math.pow(r,2)-Math.pow(this.getX()-1-centerPoint.x,2)>=0)
        {
            if(this.centerPoint==null) this.centerPoint=new Point((int)getX(),(int)getY());

            double y=Math.sqrt(Math.pow(r,2)-Math.pow(this.getX()-speed-centerPoint.x,2))+centerPoint.y;
            ((Game) this.getContext()).setViewElement(this, (int)this.getX()-speed,(int)(y+0.5));
        }
        else
        {
            //((Game) this.getContext()).removeObject(this);
           // ((Game) this.getContext()).getEyeOnGame().removeItem(this);
            this.showed=false;
            System.out.println("Item zakończył swój pokaz");
        }
        xCounter++;
    }

    public boolean isShowed() {
        return showed;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    protected void move(EyeOnGame eyeOnGame) {

    }

    @Override
    public Dynamic clone() {
        return null;
    }
}
