package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("permanentstunt")
public class PermanentStunt implements BulletDoToCharacterStrategy {

    int value;
    public PermanentStunt(int value)
    {
        this.value=value;
    }
    public PermanentStunt(){}
    @Override
    public void doToCharacter(Character character) {
        character.slowDown(value);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new PermanentStunt(this.value);
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Kulka spowalnia przecinika o "+value);
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
