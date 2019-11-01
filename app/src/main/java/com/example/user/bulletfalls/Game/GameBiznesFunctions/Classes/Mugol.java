package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("mugol")
public class Mugol implements IClass {
    @Override
    public int getImage() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public ClassAction action(EyeOnGame eog) {
        return null;
    }


}
