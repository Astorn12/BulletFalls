package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.GameManagement.GameController;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Named;
import com.example.user.bulletfalls.KlasyPomocnicze.Dimension;
import com.example.user.bulletfalls.Specyfications.ViewElementSpecyfication;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Timer;

public abstract class ViewElement extends android.support.v7.widget.AppCompatImageView implements Named {
    protected  int power;
    protected int speed;
    protected Point startingPoint;
    Timer moveTimer;
   protected int randeringFrequency=20;
    protected FrameLayout frame;
    protected int width;
    protected int height;
    GameController controller;
    protected int imageResources;
    protected String name;
    @Override
    public AccessibilityNodeInfo createAccessibilityNodeInfo() {
        return super.createAccessibilityNodeInfo();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }
    @JsonIgnore
    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Timer getMoveTimer() {
        return moveTimer;
    }

    public void setMoveTimer(Timer moveTimer) {
        this.moveTimer = moveTimer;
    }

    public int getRanderingFrequency() {
        return randeringFrequency;
    }

    public void setRanderingFrequency(int randeringFrequency) {
        this.randeringFrequency = randeringFrequency;
    }

    public FrameLayout getFrame() {
        return frame;
    }

    public void setFrame(FrameLayout frame) {
        this.frame = frame;
    }



    public void setWidth(int width) {
        this.width = width;
    }

    public Dimension getDimension()
    {
        return new Dimension(this.width,this.height);
    }
    public void setDimension(Dimension dimension)
    {
        this.height=dimension.getHeight();
        this.width=dimension.getWidth();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameController getController() {
        return controller;
    }

    public int getImageResources() {
        return imageResources;
    }

    public void setImageResources(int imageResources) {

        //this.imageResources = imageResources;
      Glide.with(this.getContext())
              .load(imageResources)
              .into(this);
      final ViewElement ind=this;
      this.post(new Runnable() {
          @Override
          public void run() {
              ind.height = height;
              width=(int)((float)height*((float)getDrawable().getIntrinsicWidth()/(float)getDrawable().getIntrinsicHeight()));
          }
      });
    }



    public ViewElement(Context context, int power, int speed,Point startingPoint, int width, final int height, int randeringFrequency, int imageResource, FrameLayout frame, String name) {
        super(context);

        // this.moveTimer = new Timer();
        this.power = power;
        this.speed = speed;
        this.startingPoint = startingPoint;
        this.randeringFrequency = randeringFrequency;

        this.setImageResource(imageResource);
        this.imageResources=imageResource;

        this.height = height;

        this.width=(int)((float)height*((float)getDrawable().getIntrinsicWidth()/(float)getDrawable().getIntrinsicHeight()));

        this.frame = frame;
        this.name=name;


    }
    public ViewElement(Context context, ViewElementSpecyfication jsonViewElement)
    {
        super(context);
        this.imageResources=jsonViewElement.getImageResources();
        this.setImageResource(imageResources);
        this.height=jsonViewElement.getHeight();
        this.width=jsonViewElement.getWidth();
        this.power=jsonViewElement.getPower();
        this.speed=jsonViewElement.getSpeed();
        this.name=jsonViewElement.getName();

    }

    public Point getCurrentMiddle()
    {
        return new Point((int)(this.getX()+this.getWidth()/2),(int)(this.getY()+this.getHeight()/2));
    }



    abstract protected void move();

    protected void destroy()
    {
        // this.setImageDrawable(null)
        // MainActivity activity= (MainActivity)getContext();
        // activity.removeImageView(this);

        //this.setVisibility(View.GONE);
        ((Game)getContext()).removeObject(this);

    }

    public void appear()
    {
        ((Game)getContext()).addView(this);
        this.setX(this.startingPoint.x);
        this.setY(this.startingPoint.y);
    }

    public void born()
    {
        appear();
        // startMoving();
    }
    public void setController(GameController controller)
    {
        this.controller=controller;
    }

    public Point getMiddle()
    {
        return new Point((int)(getX()+getWidth()/2),(int)(getY()+getHeight()/2));
    }
    abstract public ViewElement clone();


public void setContext(Context context)
{
    Glide.with(context)
            .load(this.getResources())
            .into(this);
//    this.setContext(context.getApplicationContext());

}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidthp()
    {
        return this.width;
    }

    public int getHeightp()
    {
        return this.height;
    }

}


