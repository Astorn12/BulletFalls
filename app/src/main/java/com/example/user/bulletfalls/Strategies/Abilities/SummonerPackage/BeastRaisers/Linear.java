package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonTypeName("linear")
public class Linear implements  IBeastRaiser {
    int begining;
    int jump;/**Wartosć pojedyńczego przyrostu najczęściej będzie ustatona na 1*/
    int jumpBreak;/** Wartość określająca co ile wywołań będziemy skakać*/

    int ending;/**Ending ustationy na -1 oznacza że nie ma zatrzymania na górnej wartości*/

    int counter;


    private Linear() {
        counter=0;
    }


    public Linear(int begining, int jump, int jumpBreak, int ending) {
        this.begining = begining;
        this.jump = jump;
        this.jumpBreak = jumpBreak;
        this.ending = ending;
        counter=0;
    }

    public Linear(int jumpBreak)
    {
        this.begining=1;
        this.ending=-1;
        this.jump=1;
        counter=0;
    }


    public int getBegining() {
        return begining;
    }

    public void setBegining(int begining) {
        this.begining = begining;
    }

    public int getJump() {
        return jump;
    }

    public void setJump(int jump) {
        this.jump = jump;
    }

    public int getJumpBreak() {
        return jumpBreak;
    }

    public void setJumpBreak(int jumpBreak) {
        this.jumpBreak = jumpBreak;
    }

    public int getEnding() {
        return ending;
    }

    public void setEnding(int ending) {
        this.ending = ending;
    }

    @Override
    public int beastsGroupSize() {
        counter++;

        if(counter%jumpBreak==0 && (this.ending==-1 || this.begining<=ending)) this.begining++;


        return begining;
    }
}
