package com.example.user.bulletfalls.Game.ActionService.Actions;
import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationsStrategy;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.GameAnimation;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.List;

public class AnimationAction extends Action {
    Action action;
    AnimationsStrategy strategy;
    GameAnimation animation;


    public AnimationAction(Action action, GameAnimation animation, AnimationsStrategy strategy) {
        super(action.getType());
        this.action=action;
        this.strategy=strategy;
        this.animation=animation;

    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        if (animation.willBeAnimated(eyeOnGame))//z animacją
            strategy.carryOnStrategy(eyeOnGame, action,animation);

        else this.action.doMagic(eyeOnGame);// bez animacji
    }
}
