package com.example.user.bulletfalls.Profile.Collection;

import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;

import java.util.jar.Attributes;

public class CollectionItem {
    String item;
    Boolean possesiontPointer;

    public CollectionItem(String item, Boolean possesiontPointer) {
        this.item = item;
        this.possesiontPointer = possesiontPointer;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item){
        this.item = item;
    }

    public Boolean getPossesiontPointer() {
        return possesiontPointer;
    }

    public void setPossesiontPointer(Boolean possesiontPointer) {
        this.possesiontPointer = possesiontPointer;
    }
}
