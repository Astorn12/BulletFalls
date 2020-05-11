package com.example.user.bulletfalls.GlobalUsage.Enums;

public enum BE {

    GRENDAARMCHAIR("Grenda Armchair"),
    DAM("Dam"),
    TIMEDAM("Time Dam"),
    STANDARD("NoneDoToBulletStrategy"),
    LOG("Log"),
    RED("Red"),
    DISARM("Disarm"),
    FIRSTJURNAL("First Jurnal"),
    SECONDJURNAL("Second Jurnal"),
    THIRDJURNAL("Third Jurnal"),
    COUNTERBULLET("Counter Bullet"),
    INCREASINGBULLET("Incresing Bullet"),
    WENDYAXE("Wendy Axe"),
    CANDYTYLIP("Candy Tulip");

    private final String value;

    BE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }

    public static  BE getEnumByString(String code){
        for(BE e : BE.values()){
            if(code.equals(e.value))
                return e;
        }
        return null;
    }
}
