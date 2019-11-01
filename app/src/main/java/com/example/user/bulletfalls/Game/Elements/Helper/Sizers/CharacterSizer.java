package com.example.user.bulletfalls.Game.Elements.Helper.Sizers;

public class CharacterSizer{
    private static final float deeperHeight=120;

    public static int getHeight(float deeperComparison) {
        int n=(int)(deeperHeight*deeperComparison+0.5f);
        return n;
    }

    public static float getDipperCounter(int heigt){
        return heigt/deeperHeight;
    }
}
