package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.CharacterAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.EBeastType;

public class BeastPS extends CharacterPS {
    EBeastType eBeastType;

    public BeastPS(int speed, int life, int shootingSpeed, BulletSpecyfication bulletSpecyfication, EBeastType eBeastType) {
        super(speed, life, shootingSpeed, bulletSpecyfication);
        this.eBeastType = eBeastType;
    }

    public EBeastType geteBeastType() {
        return eBeastType;
    }

    public void seteBeastType(EBeastType eBeastType) {
        this.eBeastType = eBeastType;
    }
}
