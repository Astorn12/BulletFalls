package com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationListeners.OnAnimationEndListener;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("animationbeforeaction")
public class AnimationBeforeAction extends AnimationsStrategy {
    @Override
    public void carryOnStrategy(EyeOnGame eyeOnGame, Action action, GameAnimation animation) {
        animation.animate(eyeOnGame).setOnAnimationEndListener(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd() {
                action.doMagic(eyeOnGame);
            }
        });

    }
}
