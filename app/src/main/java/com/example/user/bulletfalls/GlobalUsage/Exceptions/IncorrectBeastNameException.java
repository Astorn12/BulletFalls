package com.example.user.bulletfalls.GlobalUsage.Exceptions;

public class IncorrectBeastNameException extends Exception {
    String incorrectBeastName;
    public IncorrectBeastNameException(String name){
        this.incorrectBeastName=name;
    }

    @Override
    public String getMessage() {
        return "Nie ma besti z taką nazwą "+ this.incorrectBeastName;
    }
}
