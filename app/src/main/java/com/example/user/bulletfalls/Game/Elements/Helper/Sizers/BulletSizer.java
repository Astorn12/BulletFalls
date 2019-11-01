package com.example.user.bulletfalls.Game.Elements.Helper.Sizers;

public class BulletSizer{

    public int getHeight(BulletScale bulletScale) {
        switch(bulletScale){
            case XS:
                return 20;
            case S:
                return 50;
            case M:
                return 80;
            case L:
                return 100;
            case XL:
                return 120;
            case XXL:
                return 150;
            case XXXL:
                return 200;
            default:
                return 0;
        }
    }
}
