package com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Objects.Ability;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;

public class GroupAbilityTimeDecrease implements GroupAbility {
    @Override
    public void boostHero(int boost, Hero hero) {/**Tutaj będziemy brać wartość procentową a że jest int to będziemy zmieną boost traktować jako 1/100 czyli 1%*/
        hero.getAbilities();
        for(AbilitySpecyfication ability:hero.getAbilities().getAbilities())
        {
            ability.decreaseRenewalTime(((double)boost)/100);
        }
    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {
        TextView textView= new TextView(linearLayout.getContext());
        int bottom=1;
        textView.setText("Umiejętność grupy zmniejsza czas odnawiani umiejętności o "+boost+"%");
        SupporterBackground supporterBackground= new SupporterBackground();
        linearLayout.addView(textView);
        supporterBackground.setChildViewBackground(linearLayout);
    }

    @Override
    public String getFootnote() {
        return "%";
    }

    @Override
    public String getPrefix() {
        return "-";
    }
}
