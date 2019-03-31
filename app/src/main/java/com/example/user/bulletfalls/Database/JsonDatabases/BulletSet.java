package com.example.user.bulletfalls.Database.JsonDatabases;

import android.content.Context;

import com.example.user.bulletfalls.ObjectsOfGame.RotateBullet;
import com.example.user.bulletfalls.ObjectsOfGame.Bullet;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.KlasyPomocnicze.FileSupporter;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.Disarm;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NothingDoToCharacter;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Dam;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.SummonDam;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Throw;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.TimeDam;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BulletSet {



    static private List<Bullet> bulletList=new LinkedList<>();
    final static private String path="bullets.json";
    public static void Load(Context context)
    {
        List<BulletSpecyfication> bd= new LinkedList<>();
        //BulletListBroker bd= new BulletListBroker();
   String s=FileSupporter.LoadStrinFromFile(path,context);


        ObjectMapper mapper = new ObjectMapper();

       CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, BulletSpecyfication.class);
        try {
            bd=mapper.readerFor(t).readValue(s);

            for(BulletSpecyfication bs: bd)
            {
                bulletList.add(new Bullet(context,bs));
            }
        } catch (IOException e) {
            e.printStackTrace();
       }


    }

    public static void Save(Context context)
    {
        List<BulletSpecyfication> bd= new LinkedList<>();
        ///BulletListBroker bd= new BulletListBroker();

        for(Bullet b : bulletList)
        {
            bd.add(b.getSpecyfication());
        }
        ObjectMapper mapper= new ObjectMapper();
        String s ="";
      try {
            CollectionType t=mapper.getTypeFactory()
                   .constructCollectionType(List.class, BulletSpecyfication.class);
           s= mapper.writerFor(t).writeValueAsString(bd);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
       }


        FileSupporter.WriteToFile(path,context,s);


    }

/*-----------------------------------------------Geters and Seters----------------------------------------------------------*/


    public static List<Bullet> getBulletList(Context context) {

        List<Bullet> ret= new LinkedList<>();
        for(Bullet b: bulletList)
        {
            ret.add(b.changeContext(context));
           // b.setContext(context);
        }
        return ret;
    }

    public static void setBulletList(List<Bullet> bulletList) {
        BulletSet.bulletList = bulletList;
    }


//------------------------------------BIZNES METHODS----------------------//
    public static Bullet getBullet(String name)
    {
        for(Bullet b:bulletList)
        {
            if(b.getName().equals(name)) return b;
        }
        return null;
    }

    public static boolean isEmpty()
    {
        if(bulletList.size()==0) return true;
        else return false;
    }

    public static List<Bullet> getBulletListForHero(String heroName,Context context)
    {
        List<String> strings= HeroAbilityBulletMapper.getStringBulletListForHero(heroName);
        List<Bullet> retur= new LinkedList<>();
        for(String s:strings)
        {
            for(Bullet b:bulletList)
            {
                if(b.getName().equals(s)) retur.add(b);
            }
        }
        List <Bullet> returm= new LinkedList<>();
        for(Bullet b: retur)
        {
            returm.add(b.changeContext(context));
        }

        return returm;
    }


    /**----------------------------------TESTS-----------------------------*/
    public static void AddToDatabaseTest(Context context)
    {
        //spis dostępnych bulletów:
        // "grendaArmchair","dam","timedam","standard


/**++++++++++++++++++++++++++++++++++++++++++ General Bullets ++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

        Bullet grendaArmachair=new Bullet("grendaArmchair",context,100,20,null,110,110,20,R.drawable.grendaamchair,null,false,new Throw(45),Shape.RECTANGLE,new NothingDoToCharacter(),Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind
        Bullet dam=new Bullet("dam",context,100,20,null,110,110,20,R.drawable.dam,null,true,new Dam(300),Shape.RECTANGLE,new NothingDoToCharacter(),Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet timeDam=new Bullet("timedam",context,100,20,null,110,110,20,R.drawable.dam,null,true,new TimeDam(300,100),Shape.RECTANGLE,new NothingDoToCharacter(),Permission.YES,Rarity.COMMON,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet standard=new Bullet("standard", context,10, 20, null, 50, 50, 20, R.drawable.blue, null, false,new Horizontal(),Shape.CIRCLE,new NothingDoToCharacter(),Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet log=new Bullet("log",context,100,20,null,110,110,20,R.drawable.log,null,true,new SummonDam(300),Shape.RECTANGLE,new NothingDoToCharacter(),Permission.NOT,Rarity.COMMON,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet red=new Bullet("red", context,10, 20, null, 50, 50, 20, R.drawable.red, null, false,new Horizontal(),Shape.CIRCLE,new NothingDoToCharacter(),Permission.NOT,Rarity.UNCOMMON,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet diarm=new Bullet("disarm",context, 10, 20, null, 50, 50, 20, R.drawable.blue, null, false,new Horizontal(),Shape.CIRCLE,new Disarm(2000),Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet firstJurnal=new Bullet("firstjurnal",context, 10, 20, null, 50, 50, 20, R.drawable.jurnal1, null, false,new Horizontal(),Shape.CIRCLE,new Disarm(2000),Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet secondjurnal=new Bullet("secondjurnal",context, 10, 20, null, 100, 100, 20, R.drawable.jurnal2, null, false,new Horizontal(),Shape.CIRCLE,new Disarm(2000),Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));
        Bullet thirdjurnal=new Bullet("thirdjurnal",context, 10, 20, null, 100, 100, 20, R.drawable.jurnal3, null, false,new Horizontal(),Shape.CIRCLE,new Disarm(2000),Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));
        RotateBullet wendyAxe=new RotateBullet("wendyaxe",context, 10, 20, null, 100, 100, 20,R.drawable.wendyaxe, null, false,20,new Horizontal(),Shape.CIRCLE,Permission.YES,Rarity.RARE,new MoneyPossesStrategy("Mystery Coin",10));


        bulletList.add(grendaArmachair);
        bulletList.add(dam);
        bulletList.add(timeDam);
        bulletList.add(standard);
        bulletList.add(log);
        bulletList.add(red);
        bulletList.add(diarm);
        bulletList.add(firstJurnal);
        bulletList.add(secondjurnal);
        bulletList.add(thirdjurnal);
        bulletList.add(wendyAxe);


    }
    public static void clear()
    {
        bulletList.clear();
    }

    public static void givePermission(Bullet bullet, Context context)
    {
        for(Bullet b:bulletList)
        {
            if(b.getName().equals(bullet.getName()))
            {
                b.setPermission(Permission.YES);
                Save(context);
                break;
            }
        }
    }

    public boolean ifHasThisBullet(String name)
    {
        Bullet bullet = getBullet(name);
        if(bullet.getPermission().equals(Permission.NOT))
        {
            return false;
        }
        else return true;
    }
    public boolean ifHasThisBullet(Bullet bullet)
    {
        return ifHasThisBullet(bullet.getName());
    }
}
