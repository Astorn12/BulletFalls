package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bulletweaken")
public class BulletWeaken implements BulletDoToCharacterStrategy {
    int fall;

    public BulletWeaken(){}
    public BulletWeaken(int fall){
        this.fall=fall;
    }

    @Override
    public void doToCharacter(Character character) {
        character.getBulletSpecyfication().decreasePower(fall);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new BulletWeaken(this.fall);
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
            TextView description= new TextView(linearLayout.getContext());
            description.setText("Zmiejsza siłę kulek przeciwnika");
            linearLayout.addView(description);
            SupporterBackground supporterBackground= new SupporterBackground();
            supporterBackground.setTextViewBackground(description);
    }
}
