package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionTypeBI;
import com.example.user.bulletfalls.Game.ActionService.Actions.BoostHeroLife;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;

public class FamilyIncreaseLifeAbility implements FamilyAbility {
    @Override
    public Action boostGame(int boost) {
        return new BoostHeroLife(ActionTypeBI.BEGIN,boost);
    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {
        TextView textView= new TextView(linearLayout.getContext());
        textView.setText("Zwiększenie życia bohatera o "+boost);
        SupporterBackground supporterBackground= new SupporterBackground();
        linearLayout.addView(textView);

        supporterBackground.setChildViewBackground(linearLayout);
    }

    @Override
    public String getFootnote() {
        return "HP";
    }

    @Override
    public String getPrefix() {
        return "+";
    }
}
