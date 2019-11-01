package com.example.user.bulletfalls.Profile.Collection;

import android.support.v4.util.Pair;

import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GameItemsCollection extends LinkedList<CollectionItem> {

    Class type;

    public GameItemsCollection(Class type) {
        this.type=type;
    }
    public Class getType(){
        return this.type;
    }

    boolean doYouOwnIt(PossesAble ae) throws CollectionLacksException {
        for(CollectionItem item: this){
            if(item.getItem().equals(ae.getName())){
                System.out.println("Outcome: "+ item.getItem() +" = / !="+ae.getName());
                return item.getPossesiontPointer();
            }
        }
        return true;
       // throw new CollectionLacksException();
    }

    void successfulTransaction(PossesAble possesAble) throws YouAlreadyPossesThisCollectionItem, CollectionLacksException, NotAppropriatePossesAbleClassError {
        boolean isCollectionFull = false;
        if(possesAble.getClass().equals(this.getType())) {

            for (CollectionItem ability : this) {
                if (ability.getItem().equals(possesAble.getName())) {
                    if (ability.getPossesiontPointer()) {
                        throw new YouAlreadyPossesThisCollectionItem();
                    } else {
                        ability.setPossesiontPointer(true);
                        isCollectionFull = true;
                        break;
                    }
                }
            }
        }else throw new NotAppropriatePossesAbleClassError();

        if(isCollectionFull==false) throw  new CollectionLacksException();
    }
    public void add (CollectionItem... params){
        for(CollectionItem item:params){
            this.add(item);
        }
    }
}
