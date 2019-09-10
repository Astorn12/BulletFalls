package com.example.user.bulletfalls.Strategies.Abilities;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.PositioningSupporters.MultiBulletPositioner;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("shootbooster")
public class ShootBooster implements DoToCharacterStrategy {

    BulletSpecyfication bulletSpecyfication;
    private int licznik;
    public ShootBooster(BulletSpecyfication bullet)
    {
        this.bulletSpecyfication=bullet;
        this.licznik=1;
    }
    private ShootBooster(){
        licznik=1;
    }

    @Override
    public void doToCharacter(Character character) {

        MultiBulletPositioner m= new MultiBulletPositioner();
        List<Bullet> bullets=m.steadilyVerticalPositioning(new Bullet(character.getContext(),bulletSpecyfication),licznik,character);
        character.launchMultiBullets(bullets);
        licznik++;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }
}
