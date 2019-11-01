package com.example.user.bulletfalls.Shop.PossesStrategyPackage.TimeStrategiesPackage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=DayOfWeekPossesStrategy.class, name = "dayofweekpossesstrategy"),
        @JsonSubTypes.Type(value=TimeInDayPossesStrategy.class, name = "timeindaypossesstrategy")
})
public interface TimeStrategies {

    boolean isInTime();
    double howSoon(); // number from 0 to 1 which describe how soon is to posses this staff

}
