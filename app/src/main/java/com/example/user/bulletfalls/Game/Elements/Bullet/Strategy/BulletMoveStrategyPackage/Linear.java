package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage;

import android.graphics.Point;

public class Linear implements BulletMoveStrategy {
    float a;
    float b;


    public Linear(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Point getQuantum(int speed, Point current) {
        float r=(float)speed;

        int x=(int) (r/Math.sqrt(Math.pow(a,2)+1));
        int y=(int) (this.a*x);
        System.out.println("LINEAR moove strategy quand"+ x+" "+y);
        return new Point(x,y);
        //return new Point(-speed,-20);
    }

    @Override
    public BulletMoveStrategy clone() {
        return new Linear(a,b);
    }

    @Override
    public String describe() {
        return "Kulka porusza się po prostej, ale pod kątem";
    }

}
