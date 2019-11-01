package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.CharacterPS;
import com.example.user.bulletfalls.Game.Elements.Items.DropStrategy.Drop;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage.CharacterMoveStrategy;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;

public class EnemyAS extends CharacterAS {
    CharacterMoveStrategy moveStrategy;
    Drop drop;

    public EnemyAS(CharacterPositioning characterPositioning, AttackDefenceFilter attackDefenceFilter, AppearAction appearAction,
                   CharacterMoveStrategy moveStrategy, Drop drop) {
        super(characterPositioning, attackDefenceFilter, appearAction);
        this.moveStrategy = moveStrategy;
        this.drop = drop;
    }


    public CharacterMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(CharacterMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public Drop getDrop() {
        return drop;
    }

    public void setDrop(Drop drop) {
        this.drop = drop;
    }
}
