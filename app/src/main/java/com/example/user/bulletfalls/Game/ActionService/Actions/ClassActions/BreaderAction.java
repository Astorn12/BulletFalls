package com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions;

import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SummonAction;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.Arrays;

public class BreaderAction extends ClassAction {


    private BeastSpecyfication beastSpecyfication;

    public BreaderAction(int timeQuand, BeastSpecyfication beastSpecyfication) {
        super(timeQuand);
        this.beastSpecyfication = beastSpecyfication;
    }

    @Override
    protected void doClassAction(EyeOnGame eog) {
        eog.addAction(new SummonAction(ActionType.INNER, Arrays.asList(this.beastSpecyfication)));
    }
}
