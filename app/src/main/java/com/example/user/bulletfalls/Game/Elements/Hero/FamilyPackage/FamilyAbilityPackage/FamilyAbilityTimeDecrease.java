package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilityTimeDecreas;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;

public class FamilyAbilityTimeDecrease implements FamilyAbility {
    @Override
    public Action boostGame(int boost) {/**Tutaj będziemy brać wartość procentową a że jest int to będziemy zmieną boost traktować jako 1/100 czyli 1%*/
        return new AbilityTimeDecreas(ActionType.BEGIN,boost);
    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {
        TextView textView= new TextView(linearLayout.getContext());
        textView.setText("Zmniejsza czas odnawiani umiejętności o "+boost+"%");
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

    @Override
    public String getDescription(int boost) {
        return "odnowienie umiejętności -"+boost+"%";
    }
}
