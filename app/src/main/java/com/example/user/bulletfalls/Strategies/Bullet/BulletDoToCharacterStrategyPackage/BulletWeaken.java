package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers.Linear;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;
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
        character.getBullet().weaken(fall);
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
