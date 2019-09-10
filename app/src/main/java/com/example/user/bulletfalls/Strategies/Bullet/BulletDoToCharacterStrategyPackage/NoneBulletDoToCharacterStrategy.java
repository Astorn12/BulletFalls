package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("nothingtodocharacter")
public class NoneBulletDoToCharacterStrategy implements BulletDoToCharacterStrategy {
    public NoneBulletDoToCharacterStrategy(){}
    @Override
    public void doToCharacter(Character character) {

    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new NoneBulletDoToCharacterStrategy();
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Brak działania");
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }
}
