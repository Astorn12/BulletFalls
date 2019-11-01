package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.EmptyAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("empty")
public class Empty implements StartAction {
    public Empty()
    {
    }
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

        //eyeOnGame.powerAnimation("empty");
        return new EmptyAction();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }


}
