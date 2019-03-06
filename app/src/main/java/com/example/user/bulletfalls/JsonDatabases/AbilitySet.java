package com.example.user.bulletfalls.JsonDatabases;

import android.content.Context;

import com.example.user.bulletfalls.Ability;
import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Hero;
import com.example.user.bulletfalls.KlasyPomocnicze.FileSupporter;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Abilities.CarpedDiem;
import com.example.user.bulletfalls.Strategies.Abilities.Empty;
import com.example.user.bulletfalls.Strategies.Abilities.Heal;
import com.example.user.bulletfalls.Strategies.Abilities.SuperShoot;
import com.example.user.bulletfalls.Strategies.Abilities.TimeChangeBullet;
import com.example.user.bulletfalls.Strategies.Character.Character.PossesStrategyPackage.MoneyPossesStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AbilitySet {
    private final static AbilitySet ourInstance=new AbilitySet();
    public static AbilitySet getInstance()
    {
        return ourInstance;
    }
    private AbilitySet()
    {

    }


    private List<Ability> abilityList=new LinkedList<>();
    final private String path="abilities.json";

    //------------------------------------BIZNES METHODS----------------------//




    public  void Load(Context context)
    {
        String s=FileSupporter.LoadStrinFromFile(path,context);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, Ability.class);
        try {
            abilityList=mapper.readValue(s,t);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void Save(Context context)
    {
        ObjectMapper mapper= new ObjectMapper();
        String s ="";
        try {
            s= mapper
                    .writeValueAsString(abilityList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        //  String s=new Gson().toJson(bulletList);
        FileSupporter.WriteToFile(path,context,s);


    }




    /*-----------------------------------------------Geters and Seters----------------------------------------------------------*/


    public List<Ability> getAbilityList() {
        return abilityList;
    }

    public void setAbilityList(List<Ability> abilityList) {
        abilityList = abilityList;
    }

    public  Ability getAbility(String name)
    {
        for(Ability a:abilityList)
        {
            if(a.getName().equals(name)) return a;
        }
        return null;
    }
    /*----------------------------------TESTS-----------------------------*/
    public void AddToDatabaseTest()
    {
        //spis dostępnych abilitek:
        // "carpediem","ability","nothing"
        Ability carpetdiem= new Ability("carpediem",R.drawable.carpetsmall,3000,new CarpedDiem(),Permission.YES,Rarity.RARE,true,new MoneyPossesStrategy("Mystery Coin",10));
        Ability ability= new Ability("ability",R.drawable.heal,10000,new Heal(50),Permission.YES,Rarity.COMMON,false,new MoneyPossesStrategy("Mystery Coin",10));
        Ability nothing= new Ability("nothing",R.drawable.black,10000,new Empty(),Permission.YES,Rarity.STARTING,false,new MoneyPossesStrategy("Mystery Coin",10));
        Ability summonLog= new Ability("summonlog",R.drawable.log,10000,new TimeChangeBullet(BulletSet.getBullet("log"),5),Permission.NOT,Rarity.UNCOMMON,false,new MoneyPossesStrategy("Mystery Coin",10));
        Ability armchairthrow= new Ability("armchairthrow",R.drawable.grendaamchair,10000,new SuperShoot(BulletSet.getBullet("grendaArmchair")),Permission.YES,Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10));


        abilityList.add(carpetdiem);
        abilityList.add(ability);
        abilityList.add(nothing);
        abilityList.add(summonLog);
        abilityList.add(armchairthrow);

    }
    public  List<Ability> getAbilityListForHero(String heroName)
    {
       List<String> abilityNamesList=HeroAbilityBulletMapper.getStringAbilityListForHero(heroName);
       List<Ability> retur= new LinkedList<>();
       for(String s:abilityNamesList)
       {
           for(Ability a:abilityList)
           {
               if(a.getName().equals(s)) retur.add(a);
           }
       }
       return retur;
    }


    public boolean isEmpty()
    {
        if(abilityList.size()==0) return true;
        else return false;
    }

    public void clear()
    {
        abilityList.clear();
    }

    public void givePermission(Ability ability, Context context)
    {

        for(Ability a:abilityList)
        {
            if(a.getName().equals(ability.getName()))
            {
                a.setPermission(Permission.YES);
                Save(context);
                break;
            }
        }
    }
    public boolean ifHasThisAbility(String name)
    {
        Ability ability = getAbility(name);
        if(ability.getPermission().equals(Permission.NOT))
        {
            return false;
        }
        else return true;
    }
    public boolean ifHasThisAbility(Ability ability)
    {
        return ifHasThisAbility(ability.getName());
    }

}
