package com.example.user.bulletfalls.GlobalUsage.Exceptions;

public class SuchEnemyDoesntExist extends Exception {

    String notExistedClass;

    public SuchEnemyDoesntExist(String notExistedClass) {
        this.notExistedClass = notExistedClass;
    }

    @Override
    public String toString()
    {
        return "Such class "+ this.notExistedClass + "doesn't exist";
    }
}
