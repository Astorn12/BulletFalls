package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Strategies.Bounty.Bounty;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;

public class GameSummary {
    private static GameSummary gameSummary= new GameSummary();
    Medium medium;
    HeroSpecyfication heroSpecyficationSpecyfication;
    String nameOfGame;
    Bounty bounty;
    private GameSummary(){}

    public static GameSummary getInstance()
    {
        return gameSummary;
    }

    public void setSummary(Medium medium, HeroSpecyfication heroSpecyfication, String nameOfGame, Bounty bounty)
    {
        this.medium=medium;
        this.nameOfGame= nameOfGame;
        this.heroSpecyficationSpecyfication = heroSpecyfication;
        this.bounty=bounty;
    }

    public HeroSpecyfication getHeroSpecyficationSpecyfication() {
        return heroSpecyficationSpecyfication;
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