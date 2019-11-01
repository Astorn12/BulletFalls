package com.example.user.bulletfalls.Game.Elements.Bullet;

import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.RotateBulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.NoneBulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.BulletMoveStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.example.user.bulletfalls.Activities.Views;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Random;

@JsonTypeName("rotate")
public class RotateBullet extends Bullet {
    public RotateBullet(Context context, BulletSpecyfication specyfication) {
        super(context, specyfication);
    }
   /* @JsonView(Views.Normal.class)
    int rotationSpeed;
   // Matrix matrix;
    int startingRotation;
    int rotationMeter;
    public RotateBullet(String name, Context context, int power, int speed, Point startingPoint, int width, int height,  int imageResource, FrameLayout frame, boolean collisionAble, int rotationSpeed, BulletMoveStrategy bulletMoveStrategy, Shape shape, Permission perm, Rarity rarity, PossesStrategy possesStrategy) {
        super(name,context, power, speed, startingPoint, width, height,  imageResource, frame, collisionAble,bulletMoveStrategy,shape,new NoneBulletDoToCharacterStrategy(),perm,rarity,possesStrategy);
        this.rotationSpeed=rotationSpeed;
        construktorEking();
     //   this.matrix=new Matrix();
       // this.setScaleType(ImageView.ScaleType.MATRIX);

    }
    public RotateBullet(BE be, Context context, int power, int speed, Point startingPoint, int width, int height,  int imageResource, FrameLayout frame, boolean collisionAble, int rotationSpeed, BulletMoveStrategy bulletMoveStrategy, Shape shape, Permission perm, Rarity rarity, PossesStrategy possesStrategy) {
        this(be.getValue(),context, power, speed, startingPoint, width, height,  imageResource, frame, collisionAble,rotationSpeed,bulletMoveStrategy,shape,perm,rarity,possesStrategy);


    }

    public RotateBullet(Context context, RotateBulletSpecyfication jsonRotateBullet) {
        super(context, jsonRotateBullet);
        this.rotationSpeed=jsonRotateBullet.getRotationSpeed();
        construktorEking();
      //  matrix= new Matrix();
       // this.setScaleType(ImageView.ScaleType.MATRIX);
        //matrix.postRotate((float)90,getMiddle().x,getMiddle().y);

        //this.setImageMatrix(matrix);
        // ((Game)this.getContext()).setViewImageMatrix(matrix,this);
    }
    protected void construktorEking()
    {
        Random random= new Random();
        this.startingRotation= random.nextInt(360);

    }
    protected void bornEking()
    {
        ((Game)this.getContext()).rotateImage(startingRotation,this);
        setRotationMeter(startingRotation);
    }

    @Override
    public void move(EyeOnGame eyeOnGame)
    {
        ((Game)this.getContext()).setX(this,(int)(getX()+speed));
        rotate();
        if(getX()+getWidth()>frame.getWidth()||getX()<0||getY()+getHeight()>frame.getHeight()||getY()<0)
        {
            destroy();

        }
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    protected void rotate()
    {
       // matrix.postRotate((float)this.rotationSpeed,getMiddle().x,getMiddle().y);


     //   ((Game)this.getContext()).setViewImageMatrix(matrix,this);
        ((Game)this.getContext()).rotateImage(rotationSpeed,this);
        addRotation(rotationSpeed);
        //appear();
       //this.setRotation(this.rotationSpeed);






    }
    @Override
    public void destroy()
    {
        ((Game)getContext()).removeObject(this);
    }
    public RotateBulletSpecyfication getJsonBullet()
    {
        return new RotateBulletSpecyfication(this);
    }
    @Override
    public RotateBullet clone()
    {
        return new RotateBullet(this.getName(),this.getContext(),this.power,this.speed,this.getStartingPoint(),this.width,this.height,imageResources,this.frame,this.collisionAble,this.rotationSpeed,new Horizontal(),this.getShape(),this.getPermission(),Rarity.STARTING,this.getPossesStrategy());
    }

    public int getRotationMeter() {
        return rotationMeter;
    }

    public void setRotationMeter(int rotationMeter) {
        this.rotationMeter = rotationMeter%360;
    }

    public void addRotation(int rotation)
    {
       setRotationMeter(getRotationMeter()+rotation);
    }
    @Override
    public void born()
    {
       bornEking();
       appear();
    }
    @Override
    public int collisionWithCharacterEfect(Character character)
    {
        int finalDamage=this.power;
        if(rotationMeter==45) {
            finalDamage *= 10;
        }
        else if(rotationMeter>10&&rotationMeter<45) {
            finalDamage*=3;
        }
        else if((rotationMeter>45&&rotationMeter<=90)||(rotationMeter<=10&&rotationSpeed>315))
        {
            finalDamage*=(int)((float)finalDamage*1.5);

        }
        else if((rotationMeter>270&&rotationMeter<=315)||(rotationMeter>90&&rotationMeter<=120))
        {
            finalDamage=(int)((float)finalDamage*0.9);

        }
        else if(rotationMeter>120&&rotationMeter<=270)
        {
            finalDamage=(int)((float)finalDamage/2);
        }
        finalDamage+=rotationSpeed;

        character.getDamage(finalDamage);
        return finalDamage;
    }

    @Override
    public RotateBulletSpecyfication getSpecyfication()
    {
        return new RotateBulletSpecyfication(this);
    }*/
}
