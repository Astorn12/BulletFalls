package com.example.user.bulletfalls.Enums;

public enum AE {
    CARPEDIEM("Carpediem"),
    ABILITY("Ability"),
    NOTHING("Nothing"),
    SUMMONLOG("Summon Log"),
    ARMCHAIRTHROW("Armchair Throw"),
    FIRSTJURNAL("firstjurnal"),
    SECONDJURNAL("secondjurnal"),
    THIRDJURNAL("thirdjurnal"),
    FIRSTSUMMON("firstsummon");


    private final String value;

    AE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }



}
