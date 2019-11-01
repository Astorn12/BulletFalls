package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters.PowerUpBullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   PowerUpBullet.class, name = "powerupbullet")
})
@JsonTypeName("attackfilter")
public abstract class AttackFilter extends Filter {
    public AttackFilter() {
        super();
    }
    public abstract void filter(List<Bullet> bullets, Character character,boolean isShootingTime);
}
