package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.MassDestruktorAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;

@JsonTypeName("massdestructor")
public class MassDestructor extends MasterAbility {
    public MassDestructor() {

        this.levelTable=new LevelTable( Arrays.asList(
                new LevelBoost(0,3),
                new LevelBoost(4,15),
                new LevelBoost(10,20),
                new LevelBoost(15,30),
                new LevelBoost(20,40),
                new LevelBoost(30,50),
                new LevelBoost(50,60)
        ));
    }

    @Override
    public int getImage() {
        return  R.drawable.massdestruction;
    }

    @Override
    public String getDescription() {
        return "Mass Destruktorzy to herosi gotowi do walki z dużą grupą przeciwników, co okreslony czas obraczają całe pole bitwy" +
                "magicznym osłabieniem, powodujące obrażenia wszystkim postacią." +
                "!!!Uwaga działa też na sprzymierzone Bestie i na samego Destruktora";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {
        int damage=this.getValueAccordingToLevel(eog.getGameContext());
        return new MassDestruktorAction(this.timeQuant,damage);
    }
}
