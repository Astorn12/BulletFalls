package com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Hero;

public class NoGroupAbility implements GroupAbility{
    @Override
    public void boostHero(int boost, Hero hero) {

    }

    @Override
    public void describe(LinearLayout linearLayout, int boost) {

    }

    @Override
    public String getFootnote() {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }
}
