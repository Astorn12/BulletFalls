package com.example.user.bulletfalls.Enums;

public enum AE {
    CARPEDIEM("Carpediem"),
    ABILITY("AbilitySpecyfication"),
    NOTHING("Nothing"),
    SUMMONLOG("Summon Log"),
    ARMCHAIRTHROW("Armchair Throw"),
    FIRSTJURNAL("firstjurnal"),
    SECONDJURNAL("secondjurnal"),
    THIRDJURNAL("thirdjurnal"),
    FIRSTSUMMON("firstsummon"),
    THREEDINOSAURS("threedinozaurs"),
    TIMEMACHINE("timemachine"),
    FULLCOUNTER("fullcounter"),
    DINOSUMMON("dinosummon");

    private final String value;

    AE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
