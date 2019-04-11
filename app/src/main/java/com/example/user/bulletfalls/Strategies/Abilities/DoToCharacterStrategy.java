package com.example.user.bulletfalls.Strategies.Abilities;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Strategies.Abilities.Summoning.Summon;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   Heal.class, name = "heal"),
        @JsonSubTypes.Type(value=   ChangeBullet.class, name = "changebullet"),
        @JsonSubTypes.Type(value=   SuperShoot.class, name = "supershoot"),
        @JsonSubTypes.Type(value=   Empty.class, name = "empty"),
        @JsonSubTypes.Type(value=   CarpetDiem.class, name = "carpeddiem"),
        @JsonSubTypes.Type(value=   Summon.class, name = "summon")

})
public interface DoToCharacterStrategy {

    public void doToCharacter(Character character);


}
