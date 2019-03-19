package com.example.user.bulletfalls.GameSupporters;

import android.content.Context;

import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemysChooser;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.BountyAssigner;

public class GameStrategy {

    protected static GameStrategy gameStrategy = new GameStrategy();
    EnemysChooser enemysChooser;
    int background;
    String nameOfTheGame;
    BountyAssigner bountyAssigner;

    protected GameStrategy(){}

    public static GameStrategy getInstance()
    {
        return gameStrategy;
    }

    public void setStrategies(EnemysChooser enemysChooser, int background ,String nameOfTheGame,BountyAssigner bountyAssigner)
    {
        this.enemysChooser=enemysChooser;
        this.background=background;
        this.nameOfTheGame=nameOfTheGame;
        this.bountyAssigner=bountyAssigner;
    }

    public EnemysChooser getEnemysChooser(Context context) {
        enemysChooser.putContext(context);
        return enemysChooser;
    }

    public int getBackground() {
        return background;
    }

    public BountyAssigner getBountyAssigner() {
        return bountyAssigner;
    }
}
