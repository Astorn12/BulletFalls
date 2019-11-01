package com.example.user.bulletfalls.GlobalUsage.Enums;

import com.example.user.bulletfalls.R;

public enum SymbolResource {
    POWER(R.drawable.powericon);
    private final int value;

    SymbolResource(int value) {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
