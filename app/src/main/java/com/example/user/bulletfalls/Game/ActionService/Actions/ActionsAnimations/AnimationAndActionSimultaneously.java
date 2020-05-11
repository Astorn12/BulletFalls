package com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("animationandactionsimultaneously")
public class AnimationAndActionSimultaneously extends AnimationsStrategy {
    @Override
    public void carryOnStrategy(EyeOnGame eyeOnGame, Action action, GameAnimation animation) {
        action.doMagic(eyeOnGame);
        animation.animate(eyeOnGame);
    }
}
