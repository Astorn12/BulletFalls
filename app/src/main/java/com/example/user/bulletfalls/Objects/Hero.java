package com.example.user.bulletfalls.Objects;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.HE;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameBiznesFunctions.Classes.IClass;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.IResistance;
import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.Interfaces.PossesAble;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.HeroSpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NoneBulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.DoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@JsonTypeName("hero")
public class Hero extends Character implements PossesAble,Summoner {


    boolean touchFlag;
    BarAbilities abilities;
    Permission permission;
    int numberOfAbilities;
    PossesStrategy possesStrategy;
    int tier;
    List<Beast> beastsWaitingToSummon;
    IClass iClass;

    public Hero(Context context, int power, int speed, Point startingPoint, int width, int height, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, IResistance resistance, Bullet heroBullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, BarAbilities abilities, String indyvidualHeroMarker, Permission perm, Description description, PossesStrategy possesStrategy, int tier, AppearAction aa,IClass iClass) {
        super(context, power, speed,  width, height, imageResource, frame, life, shootingSpeed,level,resistance, heroBullet,name,kind, groupNames,position,doToBulletStrategy,indyvidualHeroMarker,description,aa);
        this.touchFlag=false;
        //this.shootingTimer= new Timer();
        // this.gameController= gameController;
        if(position!=null)
        {

            setPosition(position);

        }
        this.abilities=abilities;
        this.permission=perm;
        this.numberOfAbilities=3;//defaultowa liczba abilitek dla botatera
        this.possesStrategy=possesStrategy;
        this.tier=tier;
        this.beastsWaitingToSummon= new LinkedList<>();
        this.iClass=iClass;
    }

    public Hero(Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, IResistance resistance, Bullet heroBullet, HE he, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, BarAbilities abilities, String indyvidualHeroMarker, Permission perm, Description description, PossesStrategy possesStrategy, int tier,AppearAction aa,IClass iClass)
        {
            this(context,power,speed,startingPoint,width,height,imageResource,frame,life,shootingSpeed,level,resistance, heroBullet,he.getValue(),kind, groupNames,position,doToBulletStrategy,abilities,indyvidualHeroMarker,perm,description,possesStrategy,tier,aa,iClass );
        }
    public Hero(Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, IResistance resistance, Bullet heroBullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, BarAbilities abilities, String indyvidualHeroMarker, Permission perm, int numberOfAbilities, Description description, PossesStrategy possesStrategy,AppearAction aa,IClass iClass) {
        this(context,power,speed,startingPoint,width,height,imageResource,frame,life,shootingSpeed,level,resistance, heroBullet,name,kind, groupNames,position,doToBulletStrategy,abilities,indyvidualHeroMarker,perm,description,possesStrategy,1,aa,iClass);
        this.numberOfAbilities=numberOfAbilities;
    }
    private Hero()
    {
        super(null,null);

    }
    public Hero(Context context, HeroSpecyfication jsHeroSpecyfication)
    {
        super(context, jsHeroSpecyfication);
       // this.bullet.setItserf(this);
        //this.bullet=new BulletSpecyfication(this.getContext(),jsHeroSpecyfication.getBulletSpecyfication());
        this.bullet = jsHeroSpecyfication.getBulletSpecyfication().getConvertedBullet(this.getContext());

        if(jsHeroSpecyfication.getCharacterPositioning()!=null)
        {
            setPosition(jsHeroSpecyfication.getCharacterPositioning());
        }
        constructorEking();
    this.abilities= jsHeroSpecyfication.getAbilities();
    this.permission= jsHeroSpecyfication.getPermission();
    this.numberOfAbilities= jsHeroSpecyfication.getNumberOfAbilities();
    this.possesStrategy= jsHeroSpecyfication.getPossesStrategy();
    this.tier= jsHeroSpecyfication.getTier();
    this.beastsWaitingToSummon= new LinkedList<>();
    }
    public BarAbilities getAbilities() {
        return abilities;
    }

    public void setAbilities(BarAbilities abilities) {
        this.abilities = abilities;
    }
    private void constructorEking()
    {
        this.touchFlag=false;
        if(this.bullet ==null)
        {
            this.bullet = new Bullet("standard",this.getContext(),this.power,20,this.getStartingPointForBullet(),50,50,R.drawable.blue,this.frame,false,new Horizontal(),Shape.CIRCLE,new NoneBulletDoToCharacterStrategy(),Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",40));  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind
        }
        this.textLife= new TextView(this.getContext());

    }
    public void lifeChecking()
        {
            if(this.life<=0&&!immortal)
            {
                this.died();
            }
        }

    @Override
    public CharacterSpecyfication getSpecyfication() {
        return new HeroSpecyfication(this);
    }

    public void died()
        {  // this.moveTimer.cancel();
            //this.shootingTimer.cancel();
          //  controller.stop();
        }

        @Override
        protected Bullet shoot()
            {


                shootingCounter += 1;
                if (shootingCounter % shootingSpeed == 0) {

                    Bullet bullet1 = this.bullet.clone();
                    bullet1.startingPoint = this.getStartingPointForBullet();
                    execute();
                    bullet1.born();
                    return bullet1;
                } else return null;
            }
        @Override
        public void born()
        {
             appear();

        }
    @Override
    public Dynamic clone() {
        return null;
    }
    @Override
        protected void move(EyeOnGame eyeOnGame)
        {

                //tu powinniśmy jeszcze sprawdzać gdzie będzie postać po następnym przeskoku aby zapobie wychodzeniu postaci za ekran
                if (this.touchFlag) {
                    if (this.getY() - speed > frame.getY()) {

                        // this.setY(getY() - 20);
                        ((Game) this.getContext()).moveViewElement(this, 0, -speed);
                    } else {
                        ((Game) this.getContext()).setViewElement(this, 0, 0);
                    }
                } else {
                    if (this.getY() + this.getHeight() + speed < frame.getHeight()) {

                        //this.setY(getY() + 20);
                        ((Game) this.getContext()).moveViewElement(this, 0, speed);
                    } else {
                        ((Game) this.getContext()).setViewElement(this, (int) this.getX(), frame.getHeight() - this.getHeight());
                    }
                }
                uploatLifeView();

        }
        public void comeDown()
        {
            this.touchFlag=false;
        }
        public void flyUp()
        {
            this.touchFlag=true;
        }
    public Point getStartingPointForBullet()//#
    {
        return new Point((int)(getX()+getWidth()),(int)(getY()+getHeight()/2));
    }
    public boolean isTouchFlag() {
        return touchFlag;
    }
    public void setTouchFlag(boolean touchFlag) {
        this.touchFlag = touchFlag;
    }

    @JsonIgnore
    public AbilitySpecyfication getFirstAbility()
    {
        return this.abilities.getFirstAbility();
    }
    @JsonIgnore
    public AbilitySpecyfication getSecondAbility()
    {
        return this.abilities.getSecondAbility();
    }
    @JsonIgnore
    public AbilitySpecyfication getThirdAbility()
    {
        return this.abilities.getThirdAbility();
    }

    @Override
    public void addObserver(Observer observer) {
            this.shootingObserves.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(this.shootingObserves.contains(observer))
        {
            this.shootingObserves.remove(observer);
        }
    }

    @Override
    public void execute() {
        Iterator<Observer> iterator=shootingObserves.iterator();
        while(iterator.hasNext())
        {
            Observer shootingObserver=iterator.next();
            shootingObserver.announce();

        }
        observerCleaning();
      /*  for(Observer shootingObserver: shootingObserves)
        {
            shootingObserver.announce();
        }*/

    }

//metody specjalne, obchodzące zwyczajne zachowanie systemu
    @Override
    public void setContext(Context context)
    {
        this.getBullet().setContext(context);
    }
    public Permission getPermission() {
        return permission;
    }
    public void setPermission(Permission permision) {
        this.permission = permision;
    }
    public Hero changeContext(Context context)
    {
        Hero hero = new Hero(context,this.power,this.speed,this.startingPoint,this.width,this.height,this.imageResources,this.frame,this.life,this.shootingSpeed,this.level,this.resistance,this.bullet,this.name,this.kind,this.groupNames,this.position,this.doToBulletStrategy,this.abilities,this.indyvidualHeroMarker,this.permission,this.description,this.possesStrategy,this.getTier(),getAppearAction(),this.iClass);
        hero.setIrrealWidth(this.getIrrealWidth());
        hero.setIrrealHeight(this.getIrrealHeight());
        return hero;
    }
    public int getNumberOfAbilities() {
        return numberOfAbilities;
    }
    public void setNumberOfAbilities(int numberOfAbilities) {
        this.numberOfAbilities = numberOfAbilities;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public boolean isPermitted()
    {
        if(permission.equals(Permission.YES)||permission.equals(Permission.FORALL))
        {
        return true;
        }

        return false;


    }

    public void boostLife(int extrLife)
    {
        if(extrLife>0)
        this.life+=extrLife;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }


    @Override
    public void summon(Beast beast) {

        this.beastsWaitingToSummon.add(beast);
    }

    public void Summon(FrameLayout frame,List<Beast> beasts)
    {
        for(Beast beast: this.beastsWaitingToSummon) {
            beast.setFrame(frame);
            beast.born();
            beasts.add(beast);
        }
        this.beastsWaitingToSummon.clear();
    }

    public List<Beast> getBeastsWaitingToSummon() {
        return beastsWaitingToSummon;
    }

    public void setBeastsWaitingToSummon(List<Beast> beastsWaitingToSummon) {
        this.beastsWaitingToSummon = beastsWaitingToSummon;
    }

    public IClass getiClass() {
        return iClass;
    }

    public void setiClass(IClass iClass) {
        this.iClass = iClass;
    }
}
