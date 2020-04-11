package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionTypeBI;
import com.example.user.bulletfalls.Game.ActionService.Actions.BoostHeroLife;

public class FamilyHealAbility implements FamilyAbility {
    @Override
    public Action boostGame(int boost) {
        return new BoostHeroLife(ActionTypeBI.BEGIN,boost);
    }



    @Override
    public void describe(LinearLayout linearLayout, int boost) {

    }

    @Override
    public String getFootnote() {
        return "HP";
    }

    @Override
    public String getPrefix() {
        return "+";
    }

    @Override
    public String getDescription(int boost) {
        return null;
    }
}
