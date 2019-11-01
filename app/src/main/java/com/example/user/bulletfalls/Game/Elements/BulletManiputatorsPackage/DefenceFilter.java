package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   AttackFilter.class, name = "attackfilter"),
        @JsonSubTypes.Type(value=   DefenceFilter.class, name = "defencefilter")
})
@JsonTypeName("defencefilter")
public abstract class DefenceFilter extends Filter {
    public DefenceFilter() {
        super();
    }

    public abstract void filter(Bullet bullet, Character character);

}
