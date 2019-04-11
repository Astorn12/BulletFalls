package com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage;

import com.example.user.bulletfalls.Objects.Hero;

public class GroupIncreaseLifeAbility implements GroupAbility {
    @Override
    public void boostHero(int boost, Hero hero) {
        hero.boostLife(boost);
    }
}
