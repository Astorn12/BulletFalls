package com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("nothingappearaction")
public class NothingAppearAction implements AppearAction {
    @Override
    public void action(Game game) {

    }
}
