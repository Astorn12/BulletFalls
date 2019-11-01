package com.example.user.bulletfalls.Profile.Collection;

public class CollectionLacksException extends Exception {


    @Override
    public String toString() {
        return "Suppose not every collection item was added to user collecion";
    }
}
