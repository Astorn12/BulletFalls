package com.example.user.bulletfalls.Enums;

public enum BE {

    GRENDAARMCHAIR("Grenda Armchair"),
    DAM("Dam"),
    TIMEDAM("Time Dam"),
    STANDARD("Standard"),
    LOG("Log"),
    RED("Red"),
    DISARM("Disarm"),
    FIRSTJURNAL("First Jurnal"),
    SEONDJURNAL("Second Jurnal"),
    THIRDJURNAL("Third Jurnal"),
    WENDYAXE("Wendy Axe");

    private final String value;

    BE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
