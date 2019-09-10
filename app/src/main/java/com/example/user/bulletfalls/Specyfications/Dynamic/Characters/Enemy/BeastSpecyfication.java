package com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy;

import android.content.Context;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.IResistance;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.DoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("beastspecyfication")
public class BeastSpecyfication extends CharacterSpecyfication {

    EBeastType eBeastType;

    public BeastSpecyfication(Beast beast) {
        super(beast);

        this.eBeastType = beast.geteBeastType();
    }

    private BeastSpecyfication() {

    }

    public BeastSpecyfication(String name, int imageResource, int power, int speed, int height, int life, int shootingSpeed, int level, IResistance resistance, BulletSpecyfication bulletSpecyfication, Kind kind, List<GroupName> groupNames, CharacterPositioning characterPositioning, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description, int miniature, AppearAction appearAction,EBeastType beastType) {
 super( name,  imageResource,  power,  speed,  height,  life,  shootingSpeed,  level,  resistance,  bulletSpecyfication,  kind,  groupNames,  characterPositioning,  doToBulletStrategy,  indyvidualHeroMarker,  description,  miniature,  appearAction) ;
    this.eBeastType=beastType;
        }


        public EBeastType geteBeastType() {
        return eBeastType;
    }

    public void seteBeastType(EBeastType eBeastType) {
        this.eBeastType = eBeastType;
    }

 /*   public BeastSpecyfication clone()
    {
        return new BeastSpecyfication(this.getName(),this.getImageResources(),this.getPower(),this.getSpeed(),this.getHeight(),this.getLife(),
                this.getShootingSpeed(),this.getLevel(),this.getResistance(),this.getBulletSpecyfication(),this.getKind(),this.getGroupNames(),
            this.getCharacterPositioning(),this.getDoToBulletStrategy(),this.getIndyvidualHeroMarker(),this.getDescription(),this.getMiniature(),
            this.getAppearAction(),this.geteBeastType());
    }*/



}

