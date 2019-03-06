package com.example.user.bulletfalls.Strategies.Character.Character.PossesStrategyPackage;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.ProfileActivity.UserProfile;
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
    public boolean tryToPosses(UserProfile userProfile);
    public void setPossesFotter(LinearLayout linearLayout, Context context);
}
