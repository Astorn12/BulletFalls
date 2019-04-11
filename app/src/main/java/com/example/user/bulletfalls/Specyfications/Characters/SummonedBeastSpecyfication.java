package com.example.user.bulletfalls.Specyfications.Characters;

import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Objects.SummonedBeast;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("summonedbeastspecyfication")
public class SummonedBeastSpecyfication extends CharacterSpecyfication {

    EBeastType eBeastType;

    public SummonedBeastSpecyfication(SummonedBeast summonedBeast) {
        super(summonedBeast);

        this.eBeastType=summonedBeast.geteBeastType();
    }
    private SummonedBeastSpecyfication()
    {

    }

    public EBeastType geteBeastType() {
        return eBeastType;
    }

    public void seteBeastType(EBeastType eBeastType) {
        this.eBeastType = eBeastType;
    }
}
