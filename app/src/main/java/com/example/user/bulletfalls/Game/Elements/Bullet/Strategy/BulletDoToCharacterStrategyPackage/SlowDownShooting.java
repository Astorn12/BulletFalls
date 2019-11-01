package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("slowdownshooting")
public class SlowDownShooting implements BulletDoToCharacterStrategy {
    int shootingDecrease;
    public SlowDownShooting(int shootingDecrease)
    {
        this.shootingDecrease=shootingDecrease;
    }
    public SlowDownShooting()
    {

    }

    @Override
    public void doToCharacter(Character character) {
        character.slowDownShooting(shootingDecrease);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new SlowDownShooting(this.shootingDecrease);
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Kulka zmiejsza szybkostrzelność przeciwnika o "+ this.shootingDecrease);
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }
}
