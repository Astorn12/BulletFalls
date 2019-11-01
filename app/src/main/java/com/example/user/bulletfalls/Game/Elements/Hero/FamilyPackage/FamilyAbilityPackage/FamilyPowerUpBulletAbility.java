package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionTypeBI;
import com.example.user.bulletfalls.Game.ActionService.Actions.PowerUpBullet;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;

public class FamilyPowerUpBulletAbility implements FamilyAbility {
    @Override
    public Action boostGame(int boost) {
        return new PowerUpBullet(ActionTypeBI.BEGIN,boost);

    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {
        TextView textView= new TextView(linearLayout.getContext());
        textView.setText("Zwiększa siłę kulek wystrzeliwanych przez postać o "+boost);
        SupporterBackground supporterBackground= new SupporterBackground();
        linearLayout.addView(textView);

        supporterBackground.setChildViewBackground(linearLayout);
    }

    @Override
    public String getFootnote() {
        return "p";
    }

    @Override
    public String getPrefix() {
        return "+";
    }
}
