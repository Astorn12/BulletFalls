package com.example.user.bulletfalls.Game.Elements.Helper;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;
import com.example.user.bulletfalls.GlobalUsage.Supporters.DrawableConverter;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.skyline.widget.layout.RoundCornerLayout;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Character extends Dynamic {
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Hero.class, name = "hero"),
            @JsonSubTypes.Type(value = Enemy.class, name = "enemy"),
            @JsonSubTypes.Type(value = Beast.class, name = "beast"),

    })

    /**VIEW STATISTICS*/
    protected Description description;


    /**PASSIVE STATISTICS*/
    protected int life;
    protected int shootingSpeed;
    protected BulletSpecyfication bulletSpecyfication;

    /**ACTIVE STATISTICS*/
    protected CharacterPositioning characterPositioning;
    protected AttackDefenceFilter attackDefenceFilter;
    protected AppearAction appearAction;

    /**COLLECTION STATISTICS*/
    protected String indyvidualHeroMarker;
    protected List<FamilyName> familyNames;
    protected List<Kind> kind;


    int irrealWidth;
    int irrealHeight;



    protected  TextView textLife;

    protected int padding=10;




    //pozwolenia
    protected boolean moveAble=true;
    protected boolean shootAble=true;
    protected boolean immortal=false;
    protected boolean immune=false;



    protected List<Integer> dressUps;

    int shootingCounter;

    public Character(Context context, CharacterSpecyfication specyfication) {
        super(context, specyfication);
        this.description=specyfication.getDescription();


        this.life=specyfication.getLife();
        this.shootingSpeed=specyfication.getShootingSpeed();
        this.bulletSpecyfication=specyfication.getBulletSpecyfication();

        this.characterPositioning=specyfication.getCharacterPositioning();
        this.attackDefenceFilter=specyfication.getAttackDefenceFilter();
        this.appearAction=specyfication.getAppearAction();

        this.indyvidualHeroMarker=specyfication.getIndyvidualHeroMarker();
        this.familyNames=specyfication.getFamilyNames();
        this.kind=specyfication.getKinds();

        textLife=new TextView(this.getContext());
        this.dressUps= new LinkedList<>();



      }

    /**ABSTRACT*/



    abstract public Point getStartingPointForBullet();
    abstract public void died();

    /**BIZNES METHODS*/
    protected List<Bullet> shoot()
    {

        List<Bullet> bulletList=new LinkedList<>();
        shootingCounter += 1;
        if (shootingCounter % shootingSpeed == 0) {

            Bullet bullet1 = new Bullet(this.getContext(),bulletSpecyfication);
            bullet1.setStartingCoordinates(getStartingPointForBullet());


            bulletList.add(bullet1);
            return bulletList;
        } else return null;
    }
    public void startingMove(EyeOnGame eyeOnGame){
            if(isMoveAble())
                move(eyeOnGame);
        }

    public int takeDemage(Bullet bullet){
            this.attackDefenceFilter.filterDefence(bullet,this);
            return bullet.collisionWithCharacterEfect(this);
        }

    public List<Bullet> startShooting() {
        List<Bullet> bullets=new LinkedList<>();
        if(this.shootAble) {
            bullets=shoot();
            if(bullets==null){
                bullets= new LinkedList<>();
                attackDefenceFilter.filterAttack(bullets,this,false);
            }
            else{
                attackDefenceFilter.filterAttack(bullets,this,true);
            }

        }
        return bullets;
    }

    protected void lifeChecking() {
        if(this.life<=0&&!immortal)
        {
            died();
        }
    }


    public int getDamage(int damage) {
        this.life -=damage;
        lifeChecking();
        return damage;
    }
    @Override
    public void appear() {
        textLife=new TextView(this.getContext());
       textLife.setText(this.life+"");
       ((Game)getContext()).addView(this);
       Point start=getStartingCoordinates(this.characterPositioning);

       this.setX(start.x);
       this.setY(start.y);
       ((Game)getContext()).addLifeInformation(textLife);
       this.appearAction.action((Game)getContext());
    }



    public void uploatLifeView() {
        ((Game)this.getContext() ).uploadLifeView(this,textLife);
    }

    public int getLife(){return this.life;}

    private void setLife(int life){this.life=life;}

    public void heal(int extraLife)
    {
        this.life+=extraLife;
        healAnimation();
    }
    public void healAnimation()
    {
        int oldId=this.getImage();
        int w= this.getLayoutParams().width;
        int h=this.getLayoutParams().height;
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





    public boolean isAlive() {
        if(this.life<=0)return false;
        else return true;
    }
    public Point getStartingCoordinates(CharacterPositioning position) {

        return countStartingCoorrdinates(position);
    }

    public Point countStartingCoorrdinates(CharacterPositioning position)
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
        return this.characterPositioning;
    }
    public void setPosition(CharacterPositioning position) {
        this.characterPositioning = position;
    }



    private String createAnimationString(String marker)
    {
        String animation = this.name;
        animation = animation.replaceAll("\\s+", "");
        animation = animation.toLowerCase();
        animation+="_"+marker;
        animation += "_animation";/**ustalanie strinaga animacji konwencja NAZWA BOHATERA+ _animation*/

        return animation;
    }
    /** w przypadku wywoływania animacji przez zdarzenia w grze, do power animation będzie kodładany marker zdarzenia
     * np na otrzymanie obrażań hero będzie reagował złapaniem się za brzuch, marker będzie wtedy może brzmień "shooted" postrzelony
     * wtedy wywołujemy animację np na tremblina tremblin_shooted_animation
     *
     * Gdy aminacja wywoływana jest przez abulitkę, abilitka ma swoją nazwę wtedy marker może przyjmować wartość nazwy abilitki
     * np znowu dla tremblina zostła wywołana abilitka z własnym markerem RandomSummon, wtedy aminacja będzie miała nazwę, tremblin_randomsummon_animation
     * */
    public void powerAnimation(String marker)
    {
        int sp = this.speed;/**zapisywanie orginalnej prędkości*/
        this.speed = 0;/**zatrzymywanie postaci*/
        int oldId = this.getImage();/**orginalny wygląd characteru*/


        String animationName=createAnimationString(marker);/**nazwa animacji w folderze drawabl*/

        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;


        int h1;
        int w1;
        /** Tutaj chodzi o jakieś te przekreśty z wielkością obrazka gdy jest animacja że on powinien być gorszej jakości
         * żeby się płynnie ładowało*/
        if (irrealWidth != 0 && irrealHeight != 0) {
            h1 = this.irrealHeight;
            w1 = this.irrealWidth;
        } else {
            h1 = this.getDrawable().getIntrinsicHeight();
            w1 = this.getDrawable().getIntrinsicWidth();
            System.out.println(this.getDrawable().getIntrinsicHeight());
        }
        Dimension oldDimension = new Dimension(getLayoutParams().width, getLayoutParams().height);
        /**sprawdzienie czy istnieje taka animacja*/
        int id = this.getResources().getIdentifier(animationName, "drawable", this.getContext().getPackageName());
        if (id != 0)
        {
        ((Game) this.getContext()).changeResourceForAnimation(this, id, oldId);

        AnimationDrawable animation = (AnimationDrawable) this.getDrawable();
            System.out.println(this.getDrawable().getIntrinsicHeight());
        int ha = animation.getFrame(1).getIntrinsicHeight();
        int wa = animation.getFrame(1).getIntrinsicWidth();
           // System.out.println("Wymiary animacji: "+ this.getLayoutParams().width +" "+ this.getLayoutParams().height);
            //System.out.println("Wymiary animacji: "+ w1+" "+ h1);

        int realwa = wa * this.getLayoutParams().width / w1;
        int realha = ha * this.getLayoutParams().height / h1;
            System.out.println(this.getDrawable().getIntrinsicHeight());
        this.getLayoutParams().width = realwa;
        this.getLayoutParams().height = realha;

        animation.start();
        checkIfAnimationDone(animation, sp, oldId, oldDimension);
            System.out.println(this.getDrawable().getIntrinsicHeight());
    }
    }
    protected AnimationDrawable CharacterAnimation(String animationName)
    {
        int sp=this.speed;
        this.speed=0;
        int oldId=this.getImage();
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
        final Dynamic i=this;
        h.postDelayed(new Runnable(){
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDone(a,oldSpeed,id,dimension);
                } else{
                    speed=oldSpeed;
                    getLayoutParams().width=dimension.getWidth();
                    getLayoutParams().height=dimension.getHeight();
                    ((Game) getContext()).rechangeImageAfterAnimation(i,id);

                }
            }
        }, timeBetweenChecks);
    }
    private void checkIfAnimationDoner(AnimationDrawable anim, final int id){
        final AnimationDrawable a = anim;
        int timeBetweenChecks = 300;
        Handler h = new Handler();
        final Dynamic i=this;
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
     this.getDamage(value);
        //poisonAnimation();
    }
    public void poisonAnimation()

    {
        int oldId=this.getImage();
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

    public Description getDescription() {
        return description;
    }
    public void setDescription(Description description) {
        this.description = description;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void colorBackgroundByGroup(RoundCornerLayout frameLayout)
    {
        int n=this.familyNames.size();
        for(int i=0;i<n;i++)
        {
            ImageView imageView= new ImageView(this.getContext());
            imageView.setBackgroundColor(familyNames.get(i).getValue());
            frameLayout.addView(imageView);
            imageView.setZ(0.1f);
            imageView.getLayoutParams().height=frameLayout.getHeight();
            imageView.getLayoutParams().width=frameLayout.getHeight()/n;
            imageView.setX(i*(frameLayout.getHeight()/n));
            imageView.setY(0);
        }
    }

    public boolean isFromGroup(FamilyName familyName)
    {if(this.familyNames !=null){
        for(FamilyName g:this.familyNames)
        {
            if(g.equals(familyName))return true;
        }}

        return false;
    }

    public int getMiniature() {
        DrawableConverter dc= new DrawableConverter();
        String miniatureName=getResources().getResourceName(this.getImage())+"miniature";
        return  dc.getDrawableByName(getContext(),miniatureName);

    }





    public void dressUp(int dress)
    {
        this.changeImage(dress);
        this.dressUps.add(dress);
    }

    public void strip(int dress)
    {
        if(this.dressUps.get(this.dressUps.size()-1)==dress)
          // if(this.getImageResources()==dress)
           {

               this.dressUps.remove(this.dressUps.indexOf(dress));
               if(this.dressUps.size()>0)
               {
               int lastSeasonDress=this.dressUps.get(this.dressUps.size()-1);
               if(this.getImage()==lastSeasonDress){
                   /**nic nie trzeba zmieniać*/
               }else {
                   this.changeImage(lastSeasonDress);/**trzeba się zastanowaić czy nie change image resource*/
               }}else/**to oznacza że tylko my zmieniliśmy dress bohatera i powinniśmy go teraz cofnąć*/
               {
                   this.changeImage(this.image);
               }
           }else{//to oznacza że ktoś zrobił nam nakładkę przemiany i po prostu nasze wygasła w trakcie
               this.dressUps.remove(this.dressUps.indexOf(dress));
           }

    }
    public TextView getTextLife()
    {
        return this.textLife;
    }

    public AppearAction getAppearAction() {
        return appearAction;
    }
    protected void setAppearAction(AppearAction appearAction) {
        this.appearAction = appearAction;
    }
    public void changeAppearAction(AppearAction appearAction)
    {
        setAppearAction(appearAction);
    }

    public void launchMultiBullets(List<Bullet> bullets)
    {
        EyeOnGame eye=((Game) this.getContext()).getEyeOnGame();

        for(Bullet b: bullets)
        {
            b.born();
            eye.addBullet(b);
        }
    }

    public AttackDefenceFilter getAttackDefenceFilter() {
        return attackDefenceFilter;
    }
    public void boostAttack(AttackFilter filter){
        this.attackDefenceFilter.boostAttack(filter);
    }
    public void boostDefence(DefenceFilter filter){
        this.attackDefenceFilter.boostDefence(filter);
    }

    private void setAttackDefenceFilter(AttackDefenceFilter attackDefenceFilter) {
        this.attackDefenceFilter = attackDefenceFilter;
    }

    public void multiplyLife(float f){
        this.life*=f;
    }

    public boolean isStandardBullet(Bullet bullet){
        if(this.bulletSpecyfication.name.equals(bullet.name)){
            return true;
        }
        else return false;
    }

    public void prepateYourBullet(Bullet bullet){
        bullet.setStartingCoordinates(this.getStartingPointForBullet());
        bullet.setFrame(((Game)this.getContext()).getController().getGameFrame());
    }


    /**GETTERS & SETTERS*/



    public int getShootingSpeed() {
        return shootingSpeed;
    }

    public void setShootingSpeed(int shootingSpeed) {
        this.shootingSpeed = shootingSpeed;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }

    public CharacterPositioning getCharacterPositioning() {
        return characterPositioning;
    }

    public void setCharacterPositioning(CharacterPositioning characterPositioning) {
        this.characterPositioning = characterPositioning;
    }

    public List<FamilyName> getFamilyNames() {
        return familyNames;
    }

    public void setFamilyNames(List<FamilyName> familyNames) {
        this.familyNames = familyNames;
    }

    public List<Kind> getKind() {
        return kind;
    }

    public void setKind(List<Kind> kind) {
        this.kind = kind;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }
}
