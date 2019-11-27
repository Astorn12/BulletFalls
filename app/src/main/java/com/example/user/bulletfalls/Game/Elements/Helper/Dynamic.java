package com.example.user.bulletfalls.Game.Elements.Helper;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Management.ICollisionable;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;

public abstract class Dynamic extends androidx.appcompat.widget.AppCompatImageView implements Named, ICollisionable {

    protected String name;
    protected int image;
    protected int width;
    protected int height;
    protected int speed;

    protected FrameLayout frame;



    public Dynamic(Context context, DynamicSpecyfication specyfication)
    {
        super(context);

        this.setImageResource(specyfication.imageResource);
        this.height=specyfication.getHeight();
        this.width=(int)((float)specyfication.getHeight()*((float)getDrawable().getIntrinsicWidth()/(float)getDrawable().getIntrinsicHeight()));
        this.speed=specyfication.getSpeed();
        this.name=specyfication.name;
        this.image=specyfication.getImage();

    }



    /**ABSTRACT*/
    public abstract  void move(EyeOnGame eyeOnGame);
    public abstract void setStartingPoint();


    /**VIEW METHODS*/
    public void destroy()
    {
        ((Game)getContext()).removeObject(this);
    }

    public void appear()
    {
        ((Game)getContext()).addView(this);
    }

    public void born()
    {
        setStartingPoint();
        appear();
    }


    /**GETERS & SETTERS*/
    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed=speed;
    }



    public FrameLayout getFrame() {
        return frame;
    }

    public void setFrame(FrameLayout frame) {
        this.frame = frame;

    }

    public int getImage() {
        return this.image;
    }

    public void changeImage(int imageResource)
    {
        //this.image=imageResource;//dynamic może zmienić wygląd, ale nadal przecwoujemy informacje o orginalnym wyglądzie
        Glide.with(this.getContext())
                .load(imageResource)
                .into(this);
    }

    public Point getMiddle()
    {
        return new Point((int)(getX()+getWidth()/2),(int)(getY()+getHeight()/2));
    }
    abstract public Object clone();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimension getDimension(){
        return new Dimension(this.width,this.height);
    }


    @Override
    public abstract Shape getShape();
}


