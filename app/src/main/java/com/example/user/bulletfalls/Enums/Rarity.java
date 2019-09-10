package com.example.user.bulletfalls.Enums;

        import android.graphics.Color;

public enum Rarity {
    STARTING(Color.parseColor("#595959")),
    COMMON(Color.parseColor("#645651")),
    UNCOMMON(Color.parseColor("#6d5448")),
    RARE(Color.parseColor("#765140")),
    VERYRARE(Color.parseColor("#7d4e38")),
    LEGENDARY(Color.parseColor("#844b2f")),
    UNPRECEDENTED(Color.parseColor("#8b4726"));


    private final int color;

    Rarity(int color) {
        this.color = color;
    }

    public int getValue()
    {
        return this.color;
    }

}
