package com.example.user.bulletfalls.Enums;

public enum SBE {
    GOMPERS("Gompers");
    private final String value;

    SBE(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}
