package com.example.user.bulletfalls.Game.Elements.Ability.ObserverInterfaces;

public interface AbilityProgressObserved {
    public void addObserver(AbilityProgressObserver abilityProgressObserver);
    public void removeObserver(AbilityProgressObserver abilityProgressObserver);
    public void execute();
}
