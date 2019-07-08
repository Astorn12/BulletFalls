package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy;


import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.Stot;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=GnomeAppearAction.class, name = "gnomeappearaction"),
        @JsonSubTypes.Type(value=NothingAppearAction.class, name = "nothingappearactio"),
})
//akcja wywoływana przy pojwieniu się characteru w grze, zarówno dla hero, enemy jak i beasts
public interface AppearAction {
    void action(Game game);
}
