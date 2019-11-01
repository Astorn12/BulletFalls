package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=SummonStrategy.class, name = "summonstrategy")

})
public interface ISummonStrategy {
    List<BeastSpecyfication> getBeasts();
    void describe(LinearLayout linearLayout);
}
