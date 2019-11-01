package com.example.user.bulletfalls.Game.Management;

abstract public class Hit {
    long time;
    int damage;

    public Hit(long time, int damage) {
        this.time = time;
        this.damage=damage;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
