package com.example.user.bulletfalls.Game.Elements.Weapon;


import android.content.Context;
import android.graphics.Point;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Management.ICollisionable;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;

public class Weapon extends androidx.appcompat.widget.AppCompatImageView implements ICollisionable {
    int image;
    Shape shape;



    public Weapon(Context context,int resource,Shape shape) {
        super(context);
        this.setImageResource(resource);
        this.image=resource;
        this.shape=shape;
    }

    public void checkCollison(Bullet bullet){

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public  void setPosition(Game game,Dimension d, Point middle){
        Point newPosition=callibrateToHero(d,middle);
        game.setViewElement(this,newPosition.x,newPosition.y);
    }

    protected  Point callibrateToHero(Dimension d,Point middle){
        int x=middle.x-d.getWidth()/2;
        int y=middle.y-d.getHeight()/2;
        return new Point(x,y);
    }

    public Point getMiddle(){
        int x=(int)(this.getX()+this.getLayoutParams().width/2+0.5);
        int y=(int)(this.getY()+this.getLayoutParams().height/2+0.5);
        return new Point(x,y);
    }
}
