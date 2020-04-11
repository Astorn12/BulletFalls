package com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamilyAbilityPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.fasterxml.jackson.annotation.JsonIgnore;

public interface FamilyAbility {

    Action boostGame(int boost);

    void describe(LinearLayout linearLayout, int boost);
    @JsonIgnore
    String getFootnote();
    @JsonIgnore
    String getPrefix();
    @JsonIgnore
    String getDescription(int boost);
}
