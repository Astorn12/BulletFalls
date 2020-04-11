package com.example.user.bulletfalls.Game.Management;

import android.content.Context;
import android.graphics.Point;

import com.example.user.bulletfalls.Game.ActionService.Action;

import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.DynamicSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.DynamicPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.DynamicVS;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Items.Item;
import com.example.user.bulletfalls.Profile.Currency;

import java.util.List;

public class EyeOnGame {
    private GameController gameController;

    public EyeOnGame(GameController gameController) {
        this.gameController = gameController;
    }


    public Point getHeroPosition()
    {
        return  new Point((int)this.gameController.getHero().getX(),(int)this.gameController.getHero().getY());
    }


    public List<EnemySpecyfication> getReadOnlyCurrentlyEnemyList() {
        return gameController.getReadOnlyCurrentEnemyList();
    }

    public List<Enemy> getEnemyList() {
        return gameController.getEnemyList();
    }

     public void mutate(List<EnemySpecyfication> toMutate, List<EnemySpecyfication> mutationOutcome) {
        this.gameController.mutate(toMutate,mutationOutcome);
     }



     /**Metody nie zautoryzowane jako bezpieczne*/
     public void addBullet(Bullet bullet)
     {
         this.gameController.addBullet(bullet);
     }


    public void removeItem(Item item) {
         this.gameController.removeItem(item);
    }

    public void itemDrop(Currency currency,Point point) {

         Item item= new Item(this.gameController.game.getContext(),new DynamicSpecyfication("",new DynamicVS(currency.getResource(),40),
                 new DynamicPS(10)),point,currency);

         this.gameController.itemsController.registerItem(item);
         this.gameController.getMedium().registerItem(currency);
         item.born();
    }

    public void addAction(Action action){
         gameController.actionMedium.addAction(action);
    }


    public Hero getHero() {
         return this.gameController.getHero();
    }

    /**Niebezpieczne użycie medium dużo zmienia, użycie takiego trybu oznacza dużą dozę zaufania*/
    public Medium getMedium(){
        return  this.getMedium();
    }

    public Context getGameContext(){
         return this.gameController.getGameFrame().getContext();
    }

}
