package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("slowdownbullet")
public class SlowDownBullet implements BulletDoToCharacterStrategy {
    int slowDownRate;

    public SlowDownBullet(int slowDownRate)
    {
        this.slowDownRate=slowDownRate;
    }
    public SlowDownBullet()
    {

    }

    @Override
    public void doToCharacter(Character character) {
        character.getBullet().slowDown(slowDownRate);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new SlowDownBullet(this.slowDownRate);
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        TextView description= new TextView(linearLayout.getContext());
        description.setText("Kulka spowalnia kulki przeciwnika o "+this.slowDownRate+" punktów");
        linearLayout.addView(description);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
    }
}
