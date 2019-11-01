package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View;

import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.CharacterSizer;

public class CharacterVS extends DynamicVS {
    Description description;


    public CharacterVS(int imageResource, float heigt, Description description) {
        super(imageResource, CharacterSizer.getHeight(heigt));
        this.description = description;

    }


    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }
}
