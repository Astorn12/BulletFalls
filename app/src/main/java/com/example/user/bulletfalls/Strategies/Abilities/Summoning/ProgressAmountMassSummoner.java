package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("progressamountmasssummoner")
public class ProgressAmountMassSummoner extends Summon  {
    private int bulletCounter;
    private int tickCounter;

    public int increasingSpeed;

    public ProgressAmountMassSummoner(BeastSpecyfication beastSpecyfication,int increasingSpeed) {
        super(beastSpecyfication);
        bulletCounter=0;
        tickCounter=0;
        this.increasingSpeed=increasingSpeed;
    }
    private ProgressAmountMassSummoner(){}
    @Override
    public void doToCharacter(Character character) {

        if(tickCounter%increasingSpeed==0) bulletCounter++;
        for(int i=0;i<bulletCounter;i++) {
            ((Hero) character).summon(new Beast(character.getContext(), choseBeast()));
        }
tickCounter++;
        }

    public int getIncreasingSpeed() {
        return increasingSpeed;
    }

    public void setIncreasingSpeed(int increasingSpeed) {
        this.increasingSpeed = increasingSpeed;
    }
}
