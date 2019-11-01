package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class EmptyAction extends Action {
    public EmptyAction() {
        super(ActionType.INNER);//to trzeba tylko usunąć kiedyś tam
            }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {

    }
}
