package com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy;


import com.example.user.bulletfalls.Game.Activities.Game;
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
