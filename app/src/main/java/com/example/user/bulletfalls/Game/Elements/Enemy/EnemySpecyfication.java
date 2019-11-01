package com.example.user.bulletfalls.Game.Elements.Enemy;

import com.example.user.bulletfalls.Game.Elements.Ability.AbilitiesBar;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.EnemyAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.EnemyPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes.IClass;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.CharacterSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.Elements.Items.DropStrategy.Drop;
import com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage.CharacterMoveStrategy;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("enemyspecyfication")
public class EnemySpecyfication extends CharacterSpecyfication {

    /**VIEW STATISTICS*/


    /**PASSIVE STATISTICS*/
    int killValue;

    /**ACTIVE STATISTICS*/
    CharacterMoveStrategy moveStrategy;
    Drop drop;

    /**COLLECTION STATISTICS*/


    public EnemySpecyfication(String name, CharacterVS characterVS, EnemyPS enemyPS, EnemyAS enemyAS, CharacterCS characterCS){
        super(name,characterVS,enemyPS,enemyAS,characterCS);
        this.killValue=enemyPS.getKillValue();

        this.moveStrategy=enemyAS.getMoveStrategy();
        this.drop=enemyAS.getDrop();
    }

    private EnemySpecyfication() { }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
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