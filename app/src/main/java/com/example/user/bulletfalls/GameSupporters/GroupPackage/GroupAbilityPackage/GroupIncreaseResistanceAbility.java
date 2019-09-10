package com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;

public class GroupIncreaseResistanceAbility  implements GroupAbility{
    @Override
    public void boostHero(int boost, Hero hero) {
        hero.boostResistance(boost);
    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {
        TextView textView= new TextView(linearLayout.getContext());
        int bottom=1;
        textView.setText("Zwiększenie rezystancje bohatera o "+boost+ " i dolną granicę o "+bottom);
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
