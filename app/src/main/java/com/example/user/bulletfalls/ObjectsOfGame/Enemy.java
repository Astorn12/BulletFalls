package com.example.user.bulletfalls.ObjectsOfGame;

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
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NothingDoToCharacter;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.DoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Views;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

import java.util.List;
import java.util.Random;
@JsonTypeName("enemy")
public class Enemy extends Character {
    int frameWidth;
    int frameHeight;
    @JsonView(Views.Normal.class)
    int killValue;
    EnemySpecyfication enemySpecyfication;
    public Enemy(Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, int resistance, int killValue, Bullet enemyBullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description) {
        super(context, power, speed, startingPoint, width, height, randeringFrequency, imageResource, frame, life, shootingSpeed,level,resistance,enemyBullet,name,kind, groupNames,position, doToBulletStrategy,indyvidualHeroMarker,description);

        if(frame!=null) {
            this.frameHeight = frame.getHeight();
            this.frameWidth = frame.getWidth();
        }
        this.killValue=killValue;
        construktorEking();
    }

    public Enemy(Context context, EnemySpecyfication jsonEnemy)
    {
        super(context,jsonEnemy,jsonEnemy.getName());
        this.killValue=jsonEnemy.getKillValue();
        construktorEking();
    }
    @Override
    protected void construktorEking()
    {
        if(bullet==null)
        {
            bullet= new Bullet("standard",this.getContext(),1,-10,this.getStartingPointForBullet(),50,50,20,R.drawable.blue,this.frame,false,new Horizontal(),Shape.CIRCLE,new NothingDoToCharacter(),Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",10));  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind

        }
        padding=10;
        this.setScaleX(-1f);
    }

    @Override
    public void appear()
    {
        textLife=new TextView(this.getContext());//dodać#######################

        textLife.setText(this.life+"");//dodać###############

        ((Game)getContext()).addView(this);



        ((Game)getContext()).addLifeInformation(textLife);
        this.setX(frame.getWidth()-this.width);
        //this.setX(frame.getWidth()-this.width);
if(frame.getHeight()!=0) {
    ((Game)getContext()).setY(this,new Random().nextInt((int) (frame.getHeight() - this.height)));
    //this.setY(new Random().nextInt((int) (frame.getHeight() - this.height)));
}//this.setY(new Random((int)(frame.getHeight()-100)).nextInt());
       // System.out.println("początek"+this.getX()+" "+this.getY());
        //System.out.println("ramka"+this.=);
       //setX(0);
        //setY(new Random(200).nextInt());
      //  this.getLayoutParams().height=height;
     //   this.getLayoutParams().width=width;
    }

    @Override
    public Enemy clone() {
Enemy enemy= new Enemy(this.getContext(),this.power,this.speed,this.startingPoint,this.width,this.height,0,this.imageResources,this.frame,this.life,this.shootingSpeed,this.level,this.resistance,this.killValue,this.bullet,this.name,this.kind,this.groupNames,this.position,this.doToBulletStrategy,"żaden",this.description);

        enemy.textLife=new TextView(this.getContext());
return enemy;
    }

    public Enemy changeContext(Context context)
    {
        Enemy enemy= new Enemy(context,this.power,this.speed,this.startingPoint,this.width,this.height,0,this.imageResources,this.frame,this.life,this.shootingSpeed,this.level,this.resistance,this.killValue,this.bullet,this.name,this.kind,this.groupNames,this.position,this.doToBulletStrategy,"żaden",this.description);
        enemy.textLife= new TextView(context);
        return enemy;
    }

    protected void move()
    {
        uploatLifeView();//dodać################################

            //this.setY(this.getY() + speed);
        ((Game)this.getContext()).moveViewElement(this,0,speed);
        //((Game)this.getContext()).setX(this,(int)(getX()+speed));
        if(this.getY()<0-speed)
        {
            //setY(0);
            ((Game) this.getContext()).setViewElement(this,(int)this.getX(),0);
            this.speed*=-1;
        }
        else if (getY()>frame.getHeight()-this.getHeight()-speed)
        {
           // setY(frame.getHeight() - this.getHeight());

           // int i=(int)this.getY();
            ((Game)this.getContext()).setViewElement(this,(int)this.getX(),frame.getHeight() - this.getHeight());
            this.speed*=-1;


        }



    }
    @Override
    protected Bullet shoot()
    {

        shootingCounter+=1;
        if(shootingCounter%shootingSpeed==0)
        {

            Bullet enemyBullet=(Bullet)this.bullet.clone();
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

    public int getFrameWidth() {
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
    }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }
    @Override
    public void setBullet(Bullet enemyBullet)
    {

        this.bullet=enemyBullet;
        if(this.bullet.getSpeed()>0)
        {
            this.bullet.setSpeed(bullet.getSpeed()*(-1));
        }
    }

    @Override
    public String getJsonString() {
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
}

