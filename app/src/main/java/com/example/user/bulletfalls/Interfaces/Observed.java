package com.example.user.bulletfalls.Interfaces;

public interface Observed {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void execute();
}
