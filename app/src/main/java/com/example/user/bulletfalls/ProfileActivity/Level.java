package com.example.user.bulletfalls.ProfileActivity;

import android.content.Context;

import com.example.user.bulletfalls.Database.Data.LevelRepository;

public class Level {
    int number;
    int requiredXp;



    public Level(int number, int requiredXp)
    {
        this.number=number;
        this.requiredXp=requiredXp;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRequiredXp() {
        return requiredXp;
    }

    public void setRequiredXp(int requiredXp) {
        this.requiredXp = requiredXp;
    }

    public void update(Context context)
    {
        LevelRepository ld= new LevelRepository(context);
        ld.update(this);
    }

}
