package com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;

public class GroupPowerUpBulletAbility implements GroupAbility {
    @Override
    public void boostHero(int boost, Hero hero) {
        hero.getBullet().boostPower(boost);
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
