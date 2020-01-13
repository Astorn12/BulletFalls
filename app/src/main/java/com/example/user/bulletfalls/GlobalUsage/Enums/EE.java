package com.example.user.bulletfalls.GlobalUsage.Enums;

public enum EE {

    GIDEON("Gideon"),
    RANDOMGNOME("Random Gnome"),
    JEFF("Jeff"),
    SHMEBULOCK("Shmebulock"),
    GNOMEMONSTER("Gnome Monster"),
    RAINBOWUNICORN("Rainbow unicorn");

    private final String value;

    EE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
