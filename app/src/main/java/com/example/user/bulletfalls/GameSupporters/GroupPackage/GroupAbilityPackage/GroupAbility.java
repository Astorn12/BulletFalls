package com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupAbilityPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Hero;
import com.fasterxml.jackson.annotation.JsonIgnore;

public interface GroupAbility {

    void boostHero(int boost, Hero hero);

    void describe(LinearLayout linearLayout, int boost);
    @JsonIgnore
    String getFootnote();
    @JsonIgnore
    String getPrefix();
}
