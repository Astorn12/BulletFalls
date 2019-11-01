package com.example.user.bulletfalls.Profile.Collection;

public class YouAlreadyPossesThisCollectionItem extends Exception {
    @Override
    public String toString() {
        return "Weird transaction, you already have this collection item";
    }
}
