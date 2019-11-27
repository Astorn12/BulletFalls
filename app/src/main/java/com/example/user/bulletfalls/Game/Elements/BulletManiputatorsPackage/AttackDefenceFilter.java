package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

import java.util.LinkedList;
import java.util.List;

public class AttackDefenceFilter {
    List<DefenceFilter> defence;
    List<AttackFilter> attack;


    public AttackDefenceFilter() {
       defence= new LinkedList<>();
       attack= new LinkedList<>();
    }
    public void boostAttack(AttackFilter filter){
        this.attack.add(filter);
    }

    public void boostDefence(DefenceFilter filter){
        this.defence.add(filter);
    }


    public void filterAttack(List<Bullet> bullets,Character character,boolean isShootingTime){

        List<AttackFilter> garbage= new LinkedList<>();
        for(int i=0;i<attack.size();i++){
            AttackFilter filter=attack.get(i);
            filter.filter(bullets,character,isShootingTime);
            if (filter.isRemovable()) {
                garbage.add(filter);
            }
        }
        for(int i=0;i<garbage.size();i++){
            attack.remove(garbage.get(i));
        }
        garbage.clear();



    }
    public void filterDefence(Bullet bullet, Character character){
        List<DefenceFilter> garbage= new LinkedList<>();
        for(int i=0;i<defence.size();i++){
            DefenceFilter filter=defence.get(i);
            filter.filter(bullet,character);
            if(filter.isRemovable()){
                garbage.add(filter);
            }
        }

        for(int i=0;i<garbage.size();i++){
            defence.remove(garbage.get(i));
        }
        garbage.clear();
    }

    public List<DefenceFilter> getDefence() {
        return defence;
    }

    public void setDefence(List<DefenceFilter> defence) {
        this.defence = defence;
    }

    public List<AttackFilter> getAttack() {
        return attack;
    }

    public void setAttack(List<AttackFilter> attack) {
        this.attack = attack;
    }
}
