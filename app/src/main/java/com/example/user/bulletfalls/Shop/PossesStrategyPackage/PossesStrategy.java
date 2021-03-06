package com.example.user.bulletfalls.Shop.PossesStrategyPackage;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Profile.UserProfile;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=MoneyPossesStrategy.class, name = "moneypossesstrategy"),
        @JsonSubTypes.Type(value=TimePossesStrategy.class, name = "timepossesstrategy")
})
public interface PossesStrategy {
     boolean tryToPosses(UserProfile userProfile);
     void setPossesFotter(LinearLayout linearLayout, Context context);
}
