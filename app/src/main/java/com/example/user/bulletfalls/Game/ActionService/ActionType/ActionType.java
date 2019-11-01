package com.example.user.bulletfalls.Game.ActionService.ActionType;

import com.example.user.bulletfalls.Game.ActionService.Action;

import org.apache.commons.lang3.NotImplementedException;

public enum ActionType  {
    BEGIN,INNER,END;

    public static ActionType convert(ActionTypeBE be) {
        switch(be){
            case BEGIN: return ActionType.BEGIN;
            case END: return ActionType.END;
        }
        throw new NotImplementedException("Konwersja ActionTypeBE->ActionType nie powiodła się");
    }
    public static ActionType convert(ActionTypeBI bi) {
        switch(bi){
            case BEGIN: return ActionType.BEGIN;
            case INNER: return ActionType.INNER;
        }
        throw new NotImplementedException("Konwersja ActionTypeBI->ActionType nie powiodła się");
    }
}
