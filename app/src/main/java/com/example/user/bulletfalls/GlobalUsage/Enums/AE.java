package com.example.user.bulletfalls.GlobalUsage.Enums;

public enum AE {
    CARPEDIEM("CarpediemAction"),
    ABILITY("AbilitySpecyfication"),
    NOTHING("Nothing"),
    SUMMONLOG("ISummonStrategy Log"),
    ARMCHAIRTHROW("Armchair Throw"),
    FIRSTJURNAL("Jurnal I"),
    SECONDJURNAL("Jurnal II"),
    THIRDJURNAL("Jurnal III"),
    FIRSTSUMMON("firstsummon"),
    THREEDINOSAURS("threedinozaurs"),
    TIMEMACHINE("timemachine"),
    FULLCOUNTER("fullcounter"),
    DINOSUMMON("dinosummon"),
    INCREASESHOOTING("increaseshooting"),
    PSTEST("PSTEST"),
    MULTIBEAVERSATTACK("Multi Beavers Attack"),
    PROGRESS("Progress"),
    HAMSTERBALL("Hamster Ball");

    private final String value;

    AE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
