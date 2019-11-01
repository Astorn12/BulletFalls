package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SummonAction;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;

@JsonTypeName("progressamountmasssummoner")
public class ProgressAmountMassSummoner extends Summon  {
    private int bulletCounter;
    private int tickCounter;

    public int increasingSpeed;

    public ProgressAmountMassSummoner(BeastSpecyfication beastSpecyfication, int increasingSpeed) {
        super(beastSpecyfication);
        bulletCounter=0;
        tickCounter=0;
        this.increasingSpeed=increasingSpeed;
    }
    private ProgressAmountMassSummoner(){}
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {
        List<BeastSpecyfication> beastSpecyfications= new LinkedList<>();
        if(tickCounter%increasingSpeed==0) bulletCounter++;
        for(int i=0;i<bulletCounter;i++) {
            //((Hero) eyeOnGame).summon(new Beast(eyeOnGame.getContext(), choseBeast()));
            beastSpecyfications.add(choseBeast());
        }



tickCounter++;

        return new SummonAction(ActionType.INNER,beastSpecyfications);

        }

    public int getIncreasingSpeed() {
        return increasingSpeed;
    }

    public void setIncreasingSpeed(int increasingSpeed) {
        this.increasingSpeed = increasingSpeed;
    }
}
