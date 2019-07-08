package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.IResistance;
import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NoneBulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.DoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.MoveStrategyPackage.CharacterMoveStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Views;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@JsonTypeName("enemy")
public class Enemy extends Character {
    //int frameWidth;
   // int frameHeight;
    @JsonView(Views.Normal.class)
    int killValue;
    EnemySpecyfication enemySpecyficationSpecyfication;
    CharacterMoveStrategy moveStrategy;
    public Enemy(Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, IResistance resistance, int killValue, Bullet enemyBullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description, CharacterMoveStrategy moveStrategy, AppearAction aa) {
        super(context, power, speed, width, height,  imageResource, frame, life, shootingSpeed,level,resistance, enemyBullet,name,kind, groupNames,position, doToBulletStrategy,indyvidualHeroMarker,description,aa);

        /*if(frame!=null) {
            this.frameHeight = frame.getHeight();
            this.frameWidth = frame.getWidth();
        }*/
        this.killValue=killValue;
        construktorEking();
        this.moveStrategy=moveStrategy;
    }

    public Enemy(Context context, EnemySpecyfication jsonEnemySpecyfication)
    {
        super(context, jsonEnemySpecyfication);
        this.killValue= jsonEnemySpecyfication.getKillValue();
        this.moveStrategy= jsonEnemySpecyfication.getMoveStrategy();
        construktorEking();
    }
    @Override
    protected void construktorEking()
    {
        if(bullet ==null)
        {
            bullet = new Bullet("standard",this.getContext(),1,-10,this.getStartingPointForBullet(),50,50,R.drawable.blue,this.frame,false,new Horizontal(),Shape.CIRCLE,new NoneBulletDoToCharacterStrategy(),Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",10));  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind

        }
        padding=10;
        this.setScaleX(-1f);
    }

    @Override
    public void appear()
    {
        super.appear();



        //((Game)getContext()).addLifeInformation(textLife);
        //this.setX(frame.getWidth()-this.width);

//if(frame.getHeight()!=0) {
    //((Game)getContext()).setY(this,new Random().nextInt((int) (frame.getHeight() - this.height)));

//}
    }

    @Override
    public Enemy clone() {
Enemy enemy = new Enemy(this.getContext(),this.power,this.speed,this.startingPoint,this.width,this.height,0,this.imageResources,this.frame,this.life,this.shootingSpeed,this.level,this.resistance.clone(),this.killValue,this.bullet,this.name,this.kind,this.groupNames,this.position,this.doToBulletStrategy,"żaden",this.description,this.moveStrategy,this.appearAction);

        enemy.textLife=new TextView(this.getContext());
return enemy;
    }

    public Enemy changeContext(Context context)
    {
        Enemy enemy = new Enemy(context,this.power,this.speed,this.startingPoint,this.width,this.height,0,this.imageResources,this.frame,this.life,this.shootingSpeed,this.level,this.resistance,this.killValue,this.bullet,this.name,this.kind,this.groupNames,this.position,this.doToBulletStrategy,"żaden",this.description,this.moveStrategy,getAppearAction());
        enemy.textLife= new TextView(context);
        return enemy;
    }
    @Override
    protected void move(EyeOnGame eye)
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
    protected Bullet shoot()
    {

        shootingCounter+=1;
        if(shootingCounter%shootingSpeed==0)
        {

            Bullet enemyBullet =(Bullet)this.bullet.clone();
            enemyBullet.startingPoint=this.getStartingPointForBullet();
            enemyBullet.born();
            return enemyBullet;
        }
        else return null;




    }

    @Override
    public Point getStartingPointForBullet() {
        return new Point((int)getX(),(int)(getY()+getHeight()/2));
    }

    @Override
    public void died() {
       // controller.removeEnemy(this);
        ((Game)getContext()).removeLifeText(this.textLife);
        ((Game)getContext()).removeObject(this);
    }

   /* public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }*/

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }
    @Override
    public void setBullet(Bullet enemyBullet)
    {

        this.bullet = enemyBullet;
        if(this.bullet.getSpeed()>0)
        {
            this.bullet.setSpeed(bullet.getSpeed()*(-1));
        }
    }

    @Override
    public CharacterSpecyfication getSpecyfication() {
        return null;
    }


    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void execute() {

    }
    @Override
    public void setFrame(FrameLayout frame) {
        this.frame = frame;
        this.bullet.setFrame(frame);
    }

    public CharacterMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(CharacterMoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }
}

