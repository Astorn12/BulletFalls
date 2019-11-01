package com.example.user.bulletfalls.Game.Elements.Enemy;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.CharacterSizer;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.EnemyAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.EnemyPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.Game.Elements.Items.DropStrategy.Drop;
import com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage.CharacterMoveStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;

@JsonTypeName("enemy")
public class Enemy extends Character {

    int killValue;

    CharacterMoveStrategy moveStrategy;
    Drop drop;





    public Enemy(Context context, EnemySpecyfication specyfication)
    {
        super(context, specyfication);
        this.killValue= specyfication.getKillValue();
        this.moveStrategy= specyfication.getMoveStrategy();
        this.drop=specyfication.getDrop();
        construktorEking();

    }

    protected void construktorEking()
    {
        padding=10;
        this.setScaleX(-1f);
    }


    @Override
    public Enemy clone() {
        Enemy enemy = new Enemy(this.getContext(),this.getSpecyfication());
        enemy.textLife=new TextView(this.getContext());
        return enemy;
    }


    public EnemySpecyfication getSpecyfication(){
        return new EnemySpecyfication(this.name,
                new CharacterVS(this.image,CharacterSizer.getDipperCounter(this.height),this.description),
                new EnemyPS(this.speed,this.life,this.shootingSpeed,this.bulletSpecyfication,this.killValue),
                new EnemyAS(this.characterPositioning,this.attackDefenceFilter,this.appearAction,this.moveStrategy,this.drop),
                new CharacterCS(this.indyvidualHeroMarker,this.familyNames,this.kind));
    }

    public Enemy changeContext(Context context)
    {
        Enemy enemy = new Enemy(context, this.getSpecyfication());
        enemy.textLife= new TextView(context);
        return enemy;
    }

    @Override
    public List<Bullet> startShooting() {

        List<Bullet> bullets=super.startShooting();
        for(Bullet bullet:bullets){
            bullet.changeDirection();
        }
        return bullets;
    }
    @Override
    public void move(EyeOnGame eye)
    {
        uploatLifeView();

        int newY =this.moveStrategy.getQuantum(this.speed,eye, new Point((int)this.getX(),(int)this.getY()));
        //((Game)this.getContext()).moveViewElement(this,0,speed);
        /**sprawdzenie czy postać nie wyszła poza ekran*/
        if(this.getY()<0-newY)
        {
            ((Game) this.getContext()).setViewElement(this,(int)this.getX(),0);
            this.speed*=-1;
        }
        else if (getY()>frame.getHeight()-this.getHeight()-newY)
        {
            ((Game)this.getContext()).setViewElement(this,(int)this.getX(),frame.getHeight() - this.getHeight());
            this.speed*=-1;
        }
        else{
            ((Game)this.getContext()).setViewElement(this,(int)this.getX(),(int)(this.getY()+newY));
        }
    }

    @Override
    public void setStartingPoint() {

    }


    @Override
    public Point getStartingPointForBullet() {
        return new Point((int)getX(),(int)(getY()+getHeight()/2));
    }
    public Point getStartingPointForItem() {
        return new Point((int)getX(),(int)(getY()+getHeight()/2));
    }
    @Override
    public void died() {
       // controller.removeEnemy(this);
        ((Game)getContext()).removeLifeText(this.textLife);
        ((Game)getContext()).removeObject(this);
        itemDrop(((Game) getContext()).getEyeOnGame());
    }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }


    @Override
    public void setFrame(FrameLayout frame) {
        this.frame = frame;
    }

    public CharacterMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(CharacterMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void itemDrop(EyeOnGame eyeOnGame)
    {

        Currency currency= this.drop.drop();
        if(currency!=null) {
            eyeOnGame.itemDrop(currency, this.getStartingPointForItem());

        }
        }
    public Drop getDrop() {
        return drop;
    }

    public void setDrop(Drop drop) {
        this.drop = drop;
    }

}

