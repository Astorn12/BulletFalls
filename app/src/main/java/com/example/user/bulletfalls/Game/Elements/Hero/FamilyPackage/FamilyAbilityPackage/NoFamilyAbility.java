package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.EmptyAction;

public class NoFamilyAbility implements FamilyAbility {

    @Override
    public Action boostGame(int o) {
        return new EmptyAction();
    }
    @Override
    public void describe(LinearLayout linearLayout, int boost) {

    }

    @Override
    public String getFootnote() {
        return " ";
    }

    @Override
    public String getPrefix() {
        return " ";
    }

    @Override
    public String getDescription(int boost) {
        return "brak akcji";
    }
}
