package com.example.user.bulletfalls.Strategies.Abilities;

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


}
