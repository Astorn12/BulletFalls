package com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.LinkedList;
import java.util.List;

public class SummonAction extends Action {
    List<BeastSpecyfication> beastSpecyfications;

    public SummonAction(ActionType type, List<BeastSpecyfication> beastSpecyfications) {
        super(type);
        this.beastSpecyfications = beastSpecyfications;
    }
    public SummonAction(ActionType type, BeastSpecyfication beastSpecyfication) {
        super(type);

        this.beastSpecyfications= new LinkedList<>();
        this.beastSpecyfications.add(beastSpecyfication);
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        for(BeastSpecyfication bs:beastSpecyfications)
        {
             eyeOnGame.getHero().summon(new Beast(eyeOnGame.getHero().getContext(), bs));
        }

    }
}
