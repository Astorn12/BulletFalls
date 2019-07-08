package com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy;

import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("beastspecyfication")
public class BeastSpecyfication extends CharacterSpecyfication {

    EBeastType eBeastType;

    public BeastSpecyfication(Beast beast) {
        super(beast);

        this.eBeastType = beast.geteBeastType();
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

