package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.MoneyBountyMultiplication;
import com.google.android.material.textfield.TextInputEditText;

public class MoneyCollector implements FamilyAbility {
    @Override
    public Action boostGame(int boost) {
        return new MoneyBountyMultiplication(ActionType.END,boost);
    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {
        TextView textView= new TextView(linearLayout.getContext());
        textView.setText("Wartość wygranego złota z walk jest zwiększona o "+boost+"%");
        linearLayout.addView(textView);
    }

    @Override
    public String getFootnote() {
        return "%";
    }

    @Override
    public String getPrefix() {
        return "+";
    }

    @Override
    public String getDescription(int boost) {
        return "Gold *"+boost;
    }
}
