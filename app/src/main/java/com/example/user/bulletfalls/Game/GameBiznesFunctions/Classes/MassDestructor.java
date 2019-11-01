package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("massdestructor")
public class MassDestructor  implements IClass {
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
        return null;
    }


}
