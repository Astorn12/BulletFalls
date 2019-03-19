package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.GameSupporters.GameStrategy;
import com.example.user.bulletfalls.GameSupporters.GiveBountyPackage.Bounty;
import com.example.user.bulletfalls.Hero;
import com.example.user.bulletfalls.Specyfications.Characters.HeroSpecyfication;

public class GameSummary {
    private static GameSummary gameSummary= new GameSummary();
    Medium medium;
    HeroSpecyfication heroSpecyfication;
    String nameOfGame;
    Bounty bounty;
    private GameSummary(){}

    public static GameSummary getInstance()
    {
        return gameSummary;
    }

    public void setSummary(Medium medium, HeroSpecyfication hero,String nameOfGame,Bounty bounty)
    {
        this.medium=medium;
        this.nameOfGame= nameOfGame;
        this.heroSpecyfication=hero;
        this.bounty=bounty;
    }

    public HeroSpecyfication getHeroSpecyfication() {
        return heroSpecyfication;
    }

    public String getNameOfGame() {
        return nameOfGame;
    }

    public Medium getMedium()
    {
        return this.medium;
    }
    public Bounty getBounty()
    {
        return  this.bounty;
    }
}