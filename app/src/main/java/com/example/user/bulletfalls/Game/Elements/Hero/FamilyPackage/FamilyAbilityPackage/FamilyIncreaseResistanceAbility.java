package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.BoostResistance;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters.Resistance;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;

public class FamilyIncreaseResistanceAbility implements FamilyAbility {

    @Override
    public Action boostGame(int boost) {
        return new BoostResistance(ActionType.BEGIN,convertToValue(boost),convertToBottom(boost));
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

    @Override
    public String getDescription(int boost) {
        return "rezystancja -"+boost+" do "+convertToBottom(boost);
    }

    private int convertToValue(int boost){
        return boost;
    }

    private int convertToBottom(int boost){
        return boost/3;
    }





}
