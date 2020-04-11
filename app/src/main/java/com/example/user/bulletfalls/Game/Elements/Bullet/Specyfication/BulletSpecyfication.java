package com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Helper.RarityIndicator;
import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.BulletScale;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.BulletAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.BulletCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BulletPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.DynamicVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.Game.Elements.Helper.Named;
import com.example.user.bulletfalls.Game.Elements.Helper.DynamicSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.BulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.BulletMoveStrategy;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.PossesAble;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RotateBulletSpecyfication.class, name = "rotatebulletspecyfication"),
})
@JsonTypeName("bulletspecyfication")
public class BulletSpecyfication extends DynamicSpecyfication implements Named, RarityIndicator,PossesAble {

    /**VIEW STATISTICS*/

    /**PASSIVE STATISTICS*/
    boolean collisionAble;
    Shape shape;
    int power;
    BulletScale bulletScale;

    /**ACTIVE STATISTICS*/
    BulletMoveStrategy bulletMoveStrategy;
    BulletDoToCharacterStrategy bulletDoToCharacterStrategy;

    /**COLLECTION STATISTICS*/
    Rarity rarity;
    PossesStrategy possesStrategy;

    public BulletSpecyfication(BE bulletName, DynamicVS dynamicVS, BulletPS bulletPS, BulletAS bulletAS, BulletCS bulletCS) {
        super(bulletName.getValue(), dynamicVS, bulletPS);

        this.collisionAble=bulletPS.isCollisionAble();
        this.shape=bulletPS.getShape();
        this.power=bulletPS.getPower();
        this.bulletScale=bulletPS.getBulletScale();

        this.bulletMoveStrategy=bulletAS.getBulletMoveStrategy();
        this.bulletDoToCharacterStrategy=bulletAS.getBulletDoToCharacterStrategy();

        this.rarity=bulletCS.getRarity();
        this.possesStrategy=bulletCS.getPossesStrategy();

    }

    private BulletSpecyfication(){

    }


    @Override
    public boolean equals(Object o)
    {
        if(! (o instanceof BulletSpecyfication))
            return false;
        if(this.getName().equals(((BulletSpecyfication)o).getName()))
        {
            return  true;
        }
        return false;


    }
    public BulletSpecyfication clone()
    {
        return new BulletSpecyfication(BE.getEnumByString(this.name), new DynamicVS(this.imageResource,this.getHeight()) ,
                new BulletPS(this.speed,this.shape,this.collisionAble,this.power,this.bulletScale),
                new BulletAS(this.bulletMoveStrategy.clone(),this.bulletDoToCharacterStrategy),
                new BulletCS (this.rarity,this.possesStrategy));
    }


    private Map<String,String> getParameters()
    {
        Map<String,String> map= new HashMap<String, String>() {{
            put("Power: ", getPower()+"");
            put("Speed: ",getSpeed()+"" );
            put("Speed: ",getSpeed()+"" );

            put("Size: ",bulletScale.name()+"" );
            put("Colisions: ",isCollisionAble()+"" );
            put("Move: ",getBulletMoveStrategy().describe() );
            put("Colisions ",isCollisionAble()+"" );

        }};
        return map;
    }

    @JsonIgnore
    public LinearLayout getBuleltDescription(Context context)
    {
        LinearLayout descriptionField= new LinearLayout(context);
        for(Map.Entry<String,String> entry : getParameters().entrySet())
        {
            LinearLayout linearLayout= new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView= new TextView(context);
            textView.setText(entry.getKey());

            TextView textView1= new TextView(context);
            textView1.setText(entry.getValue());

            linearLayout.addView(textView);
            linearLayout.addView(textView1);

            descriptionField.addView(linearLayout);
        }
        return descriptionField;
    }

    public void increasePower(int boost){
        this.power+=boost;
    }

    public void decreasePower(int decrease){
        this.power-=decrease;
        if(this.power<0) power=0;
    }
    public void slowDown(int n){
        this.speed-=n;
        if(speed<3) speed=3;
    }
    public void multiplyPower( float f){
        this.power*=f;
    }


    /**GETTERS & SETTERS*/

    public BulletDoToCharacterStrategy getBulletDoToCharacterStrategy() {
        return bulletDoToCharacterStrategy;
    }
    public void setBulletDoToCharacterStrategy(BulletDoToCharacterStrategy bulletDoToCharacterStrategy) {
        this.bulletDoToCharacterStrategy = bulletDoToCharacterStrategy;
    }

    public BulletMoveStrategy getBulletMoveStrategy() {
        return bulletMoveStrategy;
    }

    public void setBulletMoveStrategy(BulletMoveStrategy bulletMoveStrategy) {
        this.bulletMoveStrategy = bulletMoveStrategy;
    }

    public boolean isCollisionAble() {
        return collisionAble;
    }

    public void setCollisionAble(boolean collisionAble) {
        this.collisionAble = collisionAble;
    }
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public BulletScale getBulletScale() {
        return bulletScale;
    }

    public void setBulletScale(BulletScale bulletScale) {
        this.bulletScale = bulletScale;
    }



}
