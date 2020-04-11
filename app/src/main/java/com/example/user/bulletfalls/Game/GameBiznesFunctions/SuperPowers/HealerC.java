package com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.Healer;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;

@JsonTypeName("healer2")
public class HealerC extends MasterAbility {



    public HealerC() {

        this.levelTable=new LevelTable( Arrays.asList(
                new LevelBoost(0,10),
                new LevelBoost(4,30),
                new LevelBoost(10,60),
                new LevelBoost(15,90),
                new LevelBoost(20,91),
                new LevelBoost(30,92),
                new LevelBoost(50,95)
        ));
    }

    @Override
    public int getImage() {
        return R.drawable.healler;
    }

    @Override
    public String getDescription() {
        return "Healerzy posiadają moc uzdrawiania, co pewien czas niwelują skutki otrzymanych obrażeń.";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {

        return new Healer(timeQuant,this.getValueAccordingToLevel(eog.getGameContext()));
    }
}
