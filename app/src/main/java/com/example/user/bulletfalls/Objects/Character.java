package com.example.user.bulletfalls.Objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Interfaces.Observed;
import com.example.user.bulletfalls.Interfaces.Observer;
import com.example.user.bulletfalls.KlasyPomocnicze.Dimension;
import com.example.user.bulletfalls.KlasyPomocnicze.DrawableConverter;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.CharacterSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.DoToBulletStrategy;
import com.example.user.bulletfalls.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.skyline.widget.layout.RoundCornerLayout;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Character extends ViewElement implements Observed {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Hero.class, name = "hero"),
            @JsonSubTypes.Type(value = Enemy.class, name = "enemy"),
            @JsonSubTypes.Type(value = SummonedBeast.class, name = "summonedbeast"),

    })
    @JsonView(Views.Normal.class)
    int life;
    @JsonView(Views.Normal.class)
    int shootingSpeed;
    int shootingCounter;
    @JsonView(Views.Normal.class)
    int level;
    @JsonView(Views.Normal.class)
    int resistance;
     TextView textLife;
    @JsonView(Views.Normal.class)
    Bullet bullet;
    @JsonView(Views.Normal.class)
    String name;
    @JsonView(Views.Normal.class)
    Kind kind;
    @JsonView(Views.Normal.class)
    List<GroupName> groupNames;
    CharacterPositioning position;
    protected int padding=10;
    DoToBulletStrategy doToBulletStrategy;
    @JsonIgnore
    List<Observer> shootingObserves=new LinkedList<>();
    @JsonIgnore
    List<Observer> cleanList= new LinkedList<>();
    @JsonView(Views.Normal.class)
    int irrealWidth;
    @JsonView(Views.Normal.class)
    int irrealHeight;
    int miniature;
    String indyvidualHeroMarker;


    //pozwolenia
    boolean moveAble=true;
    boolean shootAble=true;
    boolean immortal=false;
    boolean immune=false;
    String story;
    Description description;

    public Character(Context context, int power, int speed, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, int resistance, Bullet bullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description) {
        super(context, power, speed, null, width, height, randeringFrequency, imageResource, frame,name);
        this.life=life;
        this.shootingSpeed=shootingSpeed;
        this.shootingCounter=0;
        this.level=level;
        this.resistance=resistance;
        textLife=new TextView(this.getContext());

        if(bullet!=null)this.bullet=bullet;
        else {//bullet= new Bullet(this.getContext(),1,20,this.getStartingPointForBullet(),50,50,20,R.drawable.blue,this.frame,this.controller);  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind}
        }
        this.name=name;
        this.kind=kind;
        this.groupNames = groupNames;
        this.position=position;
        this.doToBulletStrategy=doToBulletStrategy;
        irrealDefaultSetter();
        this.indyvidualHeroMarker=indyvidualHeroMarker;
        story="";
        this.description=description;

        DrawableConverter dc= new DrawableConverter();

        String miniatureName=getResources().getResourceName(this.getImageResources())+"miniature";
        this.miniature=dc.getDrawableByName(getContext(),miniatureName);

    }
   // public Character(Context context, int power, int speed, Point startingPoint, int width, int height, int randeringFrequency, int imageResource, FrameLayout frame, int life, int shootingSpeed, int level, int resistance, Bullet bullet, String name, Kind kind, List<GroupName> groupNames, CharacterPositioning position, DoToBulletStrategy doToBulletStrategy, String indyvidualHeroMarker, Description description) {
   //     this(context,  power,  speed,  startingPoint, width, height, randeringFrequency, imageResource,frame,  life, shootingSpeed,  level, resistance,  bullet, name, kind,  groupNames, position,doToBulletStrategy, indyvidualHeroMarker, description,R.drawable.defaultminiture);
  //  }
    public Character(Context context, CharacterSpecyfication jsonCharacter) {
            super(context,jsonCharacter);
            this.life=jsonCharacter.getLife();
            this.shootingSpeed=jsonCharacter.getShootingSpeed();
            this.level=jsonCharacter.getLevel();
            this.resistance=jsonCharacter.getResistance();
            this.groupNames =jsonCharacter.getGroupNames();
            this.name=jsonCharacter.getName();
            this.kind=jsonCharacter.getKind();
            this.doToBulletStrategy=jsonCharacter.getDoToBulletStrategy();
            construktorEking();
            this.irrealHeight=jsonCharacter.getIrrealHeight();
            this.irrealWidth=jsonCharacter.getIrrealWidth();
            this.indyvidualHeroMarker=jsonCharacter.getIndyvidualHeroMarker();
            this.story=jsonCharacter.getStory();
            this.description=jsonCharacter.getDescription();
            this.miniature=jsonCharacter.getMiniature();
            this.position=jsonCharacter.getCharacterPositioning();
            this.bullet= new Bullet(this.getContext(),jsonCharacter.getBullet());
      }
    private void irrealDefaultSetter()
        {
            this.irrealHeight=0;
            this.irrealWidth=0;
        }
    protected void construktorEking() {
           /* if(startingPoint==null&& frame!=null){
                this.startingPoint= new Point((int)((frame.getWidth()*appearX)/100-this.width/2),(int)((frame.getHeight()*appearY)/100)-this.height/2);
            }*/
            textLife=new TextView(this.getContext());

        }
        public void startingMove()
        {
            if(isMoveAble())
            move();
        }
    @Override
    protected void move() {

    }
    public Bullet startShooting() {
        if(this.shootAble) {
            return shoot();
        }
        else return null;

    }
    abstract protected Bullet shoot();
    abstract public Point getStartingPointForBullet();//#
    abstract public void died();
    protected void lifeChecking() {
        if(this.life<=0)
        {
            died();
        }
    }
    public void lostLife(int x) {
        if(resistance<x)
        this.life-=(x-this.resistance);
        lifeChecking();
    }
    public int getDamage(int damage) {
        int d=0;
        if(!immune) {
            d = damage - this.resistance;
            if(d<5)d=5;/** Rezystancja działa, ale nie może obniżyć wartośći obrażeń poniżej poziomu 5 pkt*/
            this.life -= d;
        }
            lifeChecking();

        return d;
    }
    @Override
    public void appear() {

        textLife=new TextView(this.getContext());
       textLife.setText(this.life+"");
       ((Game)getContext()).addView(this);
       setStartingPoint(this.position);
       this.setX(this.startingPoint.x);
       this.setY(this.startingPoint.y);
       ((Game)getContext()).addLifeInformation(textLife);
    }
    @Override
    public ViewElement clone() {
        return null;
    }
    public void uploatLifeView() {



        ((Game)this.getContext() ).uploadLifeView(this,textLife);


    }
    public int getLevel() {
        return level;
    }
    @JsonView(Views.Normal.class)
    public void setLevel(int level) {
        this.level = level;
    }
    public int getResistance() {
        return resistance;
    }
    @JsonView(Views.Normal.class)
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    public void boostResistance(int boost){
        if(boost>0)
        this.resistance+=boost;}
    public void suppressResistance(int suppress){this.resistance-=suppress;
    if(resistance<0)resistance=0;}
    public Bullet getBullet() {
        return bullet;
    }
    @JsonView(Views.Normal.class)
    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }
    @JsonIgnore
    public void setBullet(BulletSpecyfication bulletSpecyfication)
    {
        this.bullet= new Bullet(this.getContext(),bulletSpecyfication);
        this.bullet.setFrame(this.getFrame());
    }
    public int getLife(){return this.life;}
    @JsonView(Views.Normal.class)
    private void setLife(int life){this.life=life;}
    @SuppressLint("ResourceType")
    public void heal(int extraLife)
    {
        this.life+=extraLife;
        // Start the animation (looped playback by default).
        /*int oldId=this.getImageResources();
        ((Game) this.getContext()).changeResource(this,R.drawable.heal_animation);
        AnimationDrawable heal=(AnimationDrawable) this.getDrawable();

       heal.start();
        this.setBackgroundResource(oldId);
        checkIfAnimationDoner(heal,oldId);*/
        healAnimation();
    }
    public void healAnimation()
    {
        // Start the animation (looped playback by default).
        int oldId=this.getImageResources();
        int w= this.getLayoutParams().width;
        int h=this.getLayoutParams().height;
        //((Game) this.getContext()).changeResourceForAnimation(this,R.drawable.heal_animation,oldId);
       // ((Game) this.getContext()).changeResourceForAnimation(this,R.drawable.heal_animation,oldId);
        ((Game) this.getContext()).changeForegroundForAnimation(this,R.drawable.heal_animation);

        //AnimationDrawable heal=(AnimationDrawable)this.getDrawable();
        AnimationDrawable heal= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            heal = (AnimationDrawable)this.getForeground();
        }

        this.getLayoutParams().width=w;
        this.getLayoutParams().height=h;
        heal.start();
        //this.setBackgroundResource(oldId);
        checkIfAnimationDoner(heal,oldId);
    }
    public int getShootingSpeed(){return this.shootingSpeed;}
    @JsonView(Views.Normal.class)
    public void setShootingSpeed(int shootingSpeed){this.shootingSpeed=shootingSpeed;}
    public void speedUpShooting(int speedUp){
        if(speedUp>0)
        this.shootingSpeed+=speedUp;}
    public void slowDownShooting(int slowDown)
        {
            if(slowDown>0) {
                this.shootingSpeed -= slowDown;
                if(shootingSpeed<0)shootingSpeed=0;
            }
        }
    public String getName() {
        return name;
    }
    @JsonView(Views.Normal.class)
    public void setName(String name) {
        this.name = name;
    }
    public Kind getKind() {
        return kind;
    }
    @JsonView(Views.Normal.class)
    public void setKind(Kind kind) {
        this.kind = kind;
    }
    public List<GroupName> getGroupNames() {
        return groupNames;
    }
    @JsonView(Views.Normal.class)
    public void setGroupNames(List<GroupName> groupNames) {
        this.groupNames = groupNames;
    }
    public boolean isAlive() {
        if(this.life<=0)return false;
        else return true;
    }
    public void setStartingPoint(CharacterPositioning position) {

        this.startingPoint=countStartingPoint();
    }

    public Point countStartingPoint()
    {
        switch(position)
        {
            case LEFTTOP: return new Point(padding,0);
            case LEFTCENTER: return new Point(padding, frame.getHeight()/2-this.getHeight()/2);
            case LEFTBOTTOM: return new Point(padding, frame.getHeight()-this.getHeight());
            case LEFTRANDOM: return new Point(padding, new Random().nextInt(frame.getHeight()-this.getHeight()));
            case RIGHTTOP: return new Point(frame.getWidth()-this.getHeight()-padding,0);
            case RIGHTCENTER: return new Point(frame.getWidth()-this.getHeight()-padding, frame.getHeight()/2-this.getHeight()/2);
            case RIGHTBOTTOM: return new Point(frame.getWidth()-this.getHeight()-padding, frame.getHeight()-this.getHeight());
            case RIGHTRANDOM: return new Point(frame.getWidth()-this.width-padding, new Random().nextInt(frame.getHeight()-this.height));
            case HEROSUMMONEDBEAST: return new Point(frame.getWidth()/2-this.width, new Random().nextInt(frame.getHeight()-this.height));
        }
        return new Point(0,0);
    }
    public CharacterPositioning getPosition() {
        return position;
    }
    public void setPosition(CharacterPositioning position) {
        this.position = position;
    }
    public DoToBulletStrategy getDoToBulletStrategy() {
        return doToBulletStrategy;
    }
    public void setDoToBulletStrategy(DoToBulletStrategy doToBulletStrategy) {
        this.doToBulletStrategy = doToBulletStrategy;
    }
    public void doToBullet(Bullet bullet)
    {
        doToBulletStrategy.doToBullet(bullet);
    }
    public void cleanObserver(Observer observer)
    {
        this.cleanList.add(observer);
    }
    public void observerCleaning()
    {
        this.shootingObserves.removeAll(cleanList);
        this.cleanList.removeAll(cleanList);

    }
    public void powerAnimation()//kod do czyszczenia, działa
    {
        int sp = this.speed;
        this.speed = 0;
        int oldId = this.getImageResources();

        String animation = this.name;
        animation = animation.replaceAll("\\s+", "");
        animation = animation.toLowerCase();
        animation += "_animation";

        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;


        int h1;
        int w1;
        if (irrealWidth != 0 && irrealHeight != 0) {
            h1 = this.irrealHeight;
            w1 = this.irrealWidth;
        } else {
            h1 = this.getDrawable().getIntrinsicHeight();
            w1 = this.getDrawable().getIntrinsicWidth();
        }
        Dimension oldDimension = new Dimension(getLayoutParams().width, getLayoutParams().height);

        int id = this.getResources().getIdentifier(animation, "drawable", this.getContext().getPackageName());
        if (id != 0)
        {
        ((Game) this.getContext()).changeResourceForAnimation(this, id, oldId);

        AnimationDrawable trembley = (AnimationDrawable) this.getDrawable();

        int ha = trembley.getFrame(1).getIntrinsicHeight();
        int wa = trembley.getFrame(1).getIntrinsicWidth();

        int realwa = wa * this.getLayoutParams().width / w1;
        int realha = ha * this.getLayoutParams().height / h1;

        this.getLayoutParams().width = realwa;
        this.getLayoutParams().height = realha;

        trembley.start();
        checkIfAnimationDone(trembley, sp, oldId, oldDimension);
    }
    }
    protected AnimationDrawable CharacterAnimation(String animationName)
    {
        int sp=this.speed;
        this.speed=0;
        int oldId=this.getImageResources();
        //BitmapFactory.Options dimensions = new BitmapFactory.Options();
        //dimensions.inJustDecodeBounds = true;
        int h1;
        int w1;
        if(irrealWidth!=0&&irrealHeight!=0) {
            h1 = this.irrealHeight*6;
            w1 = this.irrealWidth*6;
        }
        else
        {
            h1=this.getDrawable().getIntrinsicHeight();
            w1=this.getDrawable().getIntrinsicWidth();
        }

        Dimension oldDimension= new Dimension(getLayoutParams().width,getLayoutParams().height);

        int id=this.getResources().getIdentifier(animationName,"drawable",this.getContext().getPackageName());
        if(id==0) {
            this.speed=sp;
            return null;
        }
        ((Game) this.getContext()).changeResourceForAnimation(this,id,oldId);
        AnimationDrawable trembley=(AnimationDrawable) getDrawable();
        int ha= trembley.getFrame(1).getIntrinsicHeight();
        int wa =  trembley.getFrame(1).getIntrinsicWidth();
        int realwa=wa*this.getLayoutParams().width/w1;
        int realha=ha*this.getLayoutParams().height/h1;

        this.getLayoutParams().width=realwa;
        this.getLayoutParams().height=realha;

      trembley.start();
       checkIfAnimationDone(trembley,sp,oldId,oldDimension);
        return trembley;
    }
    //zatrzymanie ruchu postaci w momęcie gdy wykonuje swoją super moc
    private void checkIfAnimationDone(AnimationDrawable anim, final int oldSpeed,final int id,final Dimension dimension){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        final ViewElement i=this;
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDone(a,oldSpeed,id,dimension);
                } else{
                    // Toast.makeText(getContext(), "ANIMATION DONE!", Toast.LENGTH_SHORT).show();
                    speed=oldSpeed;
                    getLayoutParams().width=dimension.getWidth();
                    getLayoutParams().height=dimension.getHeight();

                    ((Game) getContext()).changeResource(i,id);
                }
            }
        }, timeBetweenChecks);
    }
    private void checkIfAnimationDoner(AnimationDrawable anim, final int id){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        final ViewElement i=this;
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDoner(a,id);
                } else{
                    // Toast.makeText(getContext(), "ANIMATION DONE!", Toast.LENGTH_SHORT).show();
                    //((Game) getContext()).changeResource(i,id);
                    ((Game) getContext()).changeForegroundForAnimation(i,android.R.color.transparent);

                  //setBackgroundResource(android.R.color.transparent);
                }
            }
        }, timeBetweenChecks);
    }
    public void slowDown(int value)
    {if(value>0)
        this.speed-=value;
        if(this.speed<0) speed=0;
    }
    public void speedUp(int value)
    {
        if(value>0)
        this.speed+=value;
    }
    public boolean isMoveAble() {
        return moveAble;
    }
    public void setMoveAble(boolean moveAble) {
        this.moveAble = moveAble;
    }
    public boolean isShootAble() {
        return shootAble;
    }
    public void setShootAble(boolean shootAble) {
        this.shootAble = shootAble;
    }
    public void poison(int value)
    {
     this.lostLife(value);
        //poisonAnimation();
    }
    public void poisonAnimation()

    {
        int oldId=this.getImageResources();
        ((Game) this.getContext()).changeResource(this,R.drawable.poison_animation);
        AnimationDrawable poison=(AnimationDrawable) this.getDrawable();

        poison.start();
        this.setBackgroundResource(oldId);
        checkIfAnimationDoner(poison,oldId);
    }
    public AnimationDrawable superShootAnimation()
    {
      String animation=this.name;
      animation=animation.replaceAll("\\s+","");
      animation=animation.toLowerCase();
      animation+="_shoot_animation";

    return CharacterAnimation(animation);
   // return CharacterAnimation(name);
    }
    public int getIrrealWidth() {
        return irrealWidth;
    }
    public void setIrrealWidth(int irrealWidth) {
        this.irrealWidth = irrealWidth;
    }
    public int getIrrealHeight() {
        return irrealHeight;
    }
    public void setIrrealHeight(int irrealHeight) {
        this.irrealHeight = irrealHeight;
    }
    public String getIndyvidualHeroMarker() {
        return indyvidualHeroMarker;
    }
    public void setIndyvidualHeroMarker(String indyvidualHeroMarker) {
        this.indyvidualHeroMarker = indyvidualHeroMarker;
    }
    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }
    public Description getDescription() {
        return description;
    }
    public void setDescription(Description description) {
        this.description = description;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void colorBackgroundByGroup(RoundCornerLayout frameLayout)
    {
        int n=this.groupNames.size();
        for(int i=0;i<n;i++)
        {
            ImageView imageView= new ImageView(this.getContext());
            imageView.setBackgroundColor(groupNames.get(i).getValue());
            frameLayout.addView(imageView);
            imageView.setZ(0.1f);
            imageView.getLayoutParams().height=frameLayout.getHeight();
            imageView.getLayoutParams().width=frameLayout.getHeight()/n;
            imageView.setX(i*(frameLayout.getHeight()/n));
            imageView.setY(0);
        }
    }

    public boolean isFromGroup(GroupName groupName)
    {
        for(GroupName g:this.groupNames)
        {
            if(g.equals(groupName))return true;
        }

        return false;
    }

    public int getMiniature() {
        return miniature;
    }



    public abstract CharacterSpecyfication getSpecyfication();

}
