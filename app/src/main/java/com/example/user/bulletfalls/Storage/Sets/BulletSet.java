package com.example.user.bulletfalls.Storage.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.BulletScale;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.BulletAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.BulletCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BulletPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.DynamicVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.Game.Elements.Bullet.RotateBullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Supporters.FileSupporter;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.Disarm;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage.NoneBulletDoToCharacterStrategy;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Dam;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.SummonDam;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Throw;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.TimeDam;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BulletSet implements ISet<BulletSpecyfication>{

    private final static BulletSet instance= new BulletSet();
    private List<BulletSpecyfication> bullets =new LinkedList<>();
    final static private String path="bullets.json";

    private BulletSet(){

    }
    public static BulletSet getInstance(){
        return instance;
    }


    public  void load(Context context)
    {
        String s=FileSupporter.LoadStrinFromFile(path,context);

        ObjectMapper mapper = new ObjectMapper();

       CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, BulletSpecyfication.class);
        try {
            bullets=mapper.readerFor(t).readValue(s);
        } catch (IOException e) {
            e.printStackTrace();
       }
    }

    public void save(Context context)
    {
        ObjectMapper mapper= new ObjectMapper();
        String s ="";
      try {
            CollectionType t=mapper.getTypeFactory()
                   .constructCollectionType(List.class, BulletSpecyfication.class);
           s= mapper.writerFor(t).writeValueAsString(bullets);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
       }

        FileSupporter.WriteToFile(path,context,s);
    }

    @Override
    public List<BulletSpecyfication> getAll() {
        return this.bullets;
    }

    /*-----------------------------------------------Geters and Seters----------------------------------------------------------*/


    public List<BulletSpecyfication> getBulletList() {
        return this.bullets;
    }


//------------------------------------BIZNES METHODS----------------------//
    public  BulletSpecyfication getBullet(String name)
    {
        for(BulletSpecyfication b: bullets)
        {
            if(b.getName().equals(name)) return b;
        }
        return null;
    }

    public  BulletSpecyfication getBullet(BE be) {
        return getBullet(be.getValue());
    }

    public  boolean isEmpty()
    {
        if(bullets.size()==0) return true;
        else return false;
    }

    public List<BulletSpecyfication> getBulletListForHero(String heroName, Context context)
    {
        List<String> strings= HeroAbilityBulletMapper.getStringBulletListForHero(heroName);
        List<BulletSpecyfication> heroesBullets= new LinkedList<>();
        for(String s:strings)
        {
            for(int i=0;i<bullets.size();i++)
            {
                if(bullets.get(i).getName().equals(s)) heroesBullets.add(bullets.get(i));
            }
        }

        return heroesBullets;
    }


    /**----------------------------------TESTS-----------------------------*/
    public  void AddToDatabaseTest(Context context)
    {
        //spis dostępnych bulletów:
        // "grendaArmchair","dam","timedam","standard


/**++++++++++++++++++++++++++++++++++++++++++ General Bullets ++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        BulletSpecyfication grendaArmachair= new BulletSpecyfication(BE.GRENDAARMCHAIR,
                new DynamicVS(R.drawable.grendaamchair,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Throw(45),new NoneBulletDoToCharacterStrategy()),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication dam= new BulletSpecyfication(BE.DAM,
                new DynamicVS(R.drawable.dam,100),
                new BulletPS(20,Shape.RECTANGLE,false,20,BulletScale.M),
                new BulletAS(new Dam(300),new NoneBulletDoToCharacterStrategy()),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication timeDam= new BulletSpecyfication(BE.TIMEDAM,
                new DynamicVS(R.drawable.dam,100),
                new BulletPS(20,Shape.RECTANGLE,false,20,BulletScale.M),
                new BulletAS(new TimeDam(300,100),new NoneBulletDoToCharacterStrategy()),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication standard= new BulletSpecyfication(BE.STANDARD,
            new DynamicVS(R.drawable.blue,100),
            new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
            new BulletAS(new Horizontal(),new NoneBulletDoToCharacterStrategy()),
            new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication log= new BulletSpecyfication(BE.LOG,
                new DynamicVS(R.drawable.log,100),
                new BulletPS(20,Shape.RECTANGLE,false,20,BulletScale.M),
                new BulletAS(new SummonDam(300),new NoneBulletDoToCharacterStrategy()),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication red= new BulletSpecyfication(BE.RED,
                new DynamicVS(R.drawable.red,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Horizontal(),new NoneBulletDoToCharacterStrategy()),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication orange= new BulletSpecyfication(BE.COUNTERBULLET,
                new DynamicVS(R.drawable.biscuit,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Horizontal(),new NoneBulletDoToCharacterStrategy()),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication disarm= new BulletSpecyfication(BE.DISARM,
                new DynamicVS(R.drawable.blue,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Horizontal(),new Disarm(2000)),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication firstJurnal= new BulletSpecyfication(BE.FIRSTJURNAL,
                new DynamicVS(R.drawable.jurnal1,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Horizontal(),new Disarm(2000)),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication secondJurnal= new BulletSpecyfication(BE.SECONDJURNAL,
                new DynamicVS(R.drawable.jurnal2,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Horizontal(),new Disarm(2000)),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication thirdjurnal= new BulletSpecyfication(BE.THIRDJURNAL,
            new DynamicVS(R.drawable.jurnal3,100),
            new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
            new BulletAS(new Horizontal(),new Disarm(2000)),
            new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));

        BulletSpecyfication increasingbullet= new BulletSpecyfication(BE.INCREASINGBULLET,
                new DynamicVS(R.drawable.increasingbullet,100),
                new BulletPS(20,Shape.CIRCLE,false,20,BulletScale.M),
                new BulletAS(new Horizontal(),new Disarm(2000)),
                new BulletCS(Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10)));






       // RotateBullet wendyAxe=new RotateBullet(BE.WENDYAXE,context, 10, 20, null, 300, 300, R.drawable.wendyaxe, null, false,20,new Horizontal(),Shape.CIRCLE,Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));


        bullets.add(grendaArmachair);
        bullets.add(dam);
        bullets.add(timeDam);
        bullets.add(standard);
        bullets.add(log);
        bullets.add(red);
        bullets.add(disarm);
        bullets.add(firstJurnal);
        bullets.add(secondJurnal);
        bullets.add(thirdjurnal);
        //bullets.add(wendyAxe);
        bullets.add(orange);
        bullets.add(increasingbullet);


    }
    public  void clear()
    {
        bullets.clear();
    }

    public  void givePermission(Bullet bullet, Context context)
    {
        for(BulletSpecyfication b: bullets)
        {
            if(b.getName().equals(bullet.getName()))
            {
                UserCollection.getInstance().succesfulTransaction(b);
                save(context);
                break;
            }
        }
    }


}
