package com.example.user.bulletfalls.Game.Management;

import android.graphics.Point;

import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;

public interface ICollisionable {
    float getX();
    float getY();
    int getWidth();
    int getHeight();
    Shape getShape();
    Point getMiddle();
}
