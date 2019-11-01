package com.example.user.bulletfalls.Game.Elements.Beast;


import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.CharacterAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BeastPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.CharacterPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.GlobalUsage.Enums.EBeastType;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.CharacterSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("beastspecyfication")
public class BeastSpecyfication extends CharacterSpecyfication {
    /**PASSIVE STATISTICS*/
    EBeastType eBeastType;

    public BeastSpecyfication(String name, CharacterVS characterVS, BeastPS beastPS, CharacterAS characterAS, CharacterCS characterCS) {
        super(name,characterVS,beastPS,characterAS,characterCS);
        this.eBeastType=beastPS.geteBeastType();
    }

    private BeastSpecyfication() {

    }

    public EBeastType geteBeastType() {
        return eBeastType;
    }

    public void seteBeastType(EBeastType eBeastType) {
        this.eBeastType = eBeastType;
    }

}
