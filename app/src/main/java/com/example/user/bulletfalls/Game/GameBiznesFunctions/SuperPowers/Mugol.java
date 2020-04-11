package com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.MugolClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("mugol")
public class Mugol extends MasterAbility {
    public Mugol() {

    }

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
        return new MugolClassAction();
    }

}
