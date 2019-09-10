package com.example.user.bulletfalls.Strategies.Abilities;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("empty")
public class Empty implements DoToCharacterStrategy {
    public Empty()
    {
    }
    @Override
    public void doToCharacter(Character character) {

        character.powerAnimation("empty");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }


}
