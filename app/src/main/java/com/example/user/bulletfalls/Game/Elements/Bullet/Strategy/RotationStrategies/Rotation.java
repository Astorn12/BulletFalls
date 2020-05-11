package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.RotationStrategies;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Random;
@JsonTypeName("rotation")
public class Rotation implements IRotationStrategy {

    int rotationSpeed;
    // Matrix matrix;
    @JsonIgnore
    int startingRotation;
    @JsonIgnore
    int rotationMeter;

    public Rotation(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
        Random random= new Random();
        this.startingRotation= random.nextInt(360);
    }

    private Rotation(){
        Random random= new Random();
        this.startingRotation= random.nextInt(360);
    }

    @Override
    public void rotate(Bullet bullet) {
        if(this.rotationMeter==0){
            this.rotationMeter=startingRotation;
        }

        ((Game)bullet.getContext()).rotateImage(rotationSpeed,bullet);
        addRotation(rotationSpeed);
    }

    public void addRotation(int rotation)
    {
        this.rotationMeter+=rotation;
    }

    public int getRotationMeter() {
        return rotationMeter;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
