package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage;

import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.CarpetDiem;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ChangeBullet;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Empty;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Heal;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ShootBooster;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.SummonStrategy;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.Summon;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.SummonFromList;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SuperShoot;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.FullCounter;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.TimeJump;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   AttackFilter.class, name = "attackfilter"),
        @JsonSubTypes.Type(value=   DefenceFilter.class, name = "defencefilter")
})
public abstract class Filter {
    boolean removable;

    public  Filter() {
      this.removable=false;
    }


    public boolean isRemovable() {
        return this.removable;
    }

    public void setRemovAble(boolean removAble) {
        this.removable = removAble;
    }
}
