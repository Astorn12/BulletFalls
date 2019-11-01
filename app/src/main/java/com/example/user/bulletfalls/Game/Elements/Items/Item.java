package com.example.user.bulletfalls.Game.Elements.Items;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Elements.Helper.DynamicSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Storage.Data.CurrencyEnum;

public class Item extends Dynamic {
    Currency currency;

    boolean showed;
    int xCounter;
    Point centerPoint;
    static final int r=150;



    public Item(Context context, DynamicSpecyfication dynamic, Point startingPoint, Currency currency){
        super(context,dynamic);
        this.currency=currency;
        this.xCounter=0;
        this.showed=true;

        this.setX(startingPoint.x);
        this.setY(startingPoint.y);
    }

    /*public Item(Context context, int speed, Point startingPoint, FrameLayout frame,Currency currency) {
        super(context, 0, speed, startingPoint, 80, 80, currency.getResource(), frame, currency.getName());
        this.currency=currency;
        this.xCounter=0;
        this.showed=true;
    }*/

    @Override
    public void move(EyeOnGame eyeOnGame) {


         if(this.centerPoint==null || Math.pow(r,2)-Math.pow(this.getX()-1-centerPoint.x,2)>=0)
        {
            if(this.centerPoint==null) this.centerPoint=new Point((int)getX(),(int)getY());

            double y=Math.sqrt(Math.pow(r,2)-Math.pow(this.getX()-speed-centerPoint.x,2))+centerPoint.y;
            ((Game) this.getContext()).setViewElement(this, (int)this.getX()-speed,(int)(y+0.5));
        }
        else
        {
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
    public void setStartingPoint() {

    }

    @Override
    public Dynamic clone() {
        return null;
    }
}
