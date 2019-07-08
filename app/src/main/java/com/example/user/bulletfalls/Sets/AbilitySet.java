package com.example.user.bulletfalls.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Enums.AE;
import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.Objects.WaitAbilitySpecyfication;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.Summoning.RandomSummon;
import com.example.user.bulletfalls.Strategies.Abilities.TimeCounting.FullCounter;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.Supporters.FileSupporter;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Abilities.CarpetDiem;
import com.example.user.bulletfalls.Strategies.Abilities.ChangeBullet;
import com.example.user.bulletfalls.Strategies.Abilities.Empty;
import com.example.user.bulletfalls.Strategies.Abilities.Heal;
import com.example.user.bulletfalls.Strategies.Abilities.Summoning.Summon;
import com.example.user.bulletfalls.Strategies.Abilities.SuperShoot;
import com.example.user.bulletfalls.Strategies.Abilities.TimeChangeBullet;
import com.example.user.bulletfalls.Strategies.Abilities.TimeCounting.TimeJump;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Arrays;
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


    private List<AbilitySpecyfication> abilitySpecyficationList =new LinkedList<>();
    final private String path="abilities.json";

    //------------------------------------BIZNES METHODS----------------------//




    public  void load(Context context)
    {
        String s=FileSupporter.LoadStrinFromFile(path,context);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, AbilitySpecyfication.class);
        try {
            abilitySpecyficationList =mapper.readerFor(t).readValue(s);
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
                    .constructCollectionType(List.class, AbilitySpecyfication.class);
            s= mapper.writerFor(t).writeValueAsString(abilitySpecyficationList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        FileSupporter.WriteToFile(path,context,s);


    }








    /*-----------------------------------------------Geters and Seters----------------------------------------------------------*/


    public List<AbilitySpecyfication> getAbilitySpecyficationList() {
        return abilitySpecyficationList;
    }

    public List<AbilitySpecyfication> getAll() {
        return abilitySpecyficationList;
    }

    public void setAbilitySpecyficationList(List<AbilitySpecyfication> abilitySpecyficationList) {
        abilitySpecyficationList = abilitySpecyficationList;
    }

    public AbilitySpecyfication getAbility(String name)
    {
        for(AbilitySpecyfication a: abilitySpecyficationList)
        {
            if(a.getName().equals(name)) return a;
        }
        return null;
    }
    public AbilitySpecyfication getAbility(AE ae)
    {

        return getAbility(ae.getValue());
    }
    /*----------------------------------TESTS-----------------------------*/
    public void AddToDatabaseTest(Context context)
    {
        //spis dostępnych abilitek:
        // "carpediem","abilitySpecyfication","nothing"
        AbilitySpecyfication carpetdiem= new AbilitySpecyfication(AE.CARPEDIEM.getValue(),R.drawable.carpetsmall,3000,new CarpetDiem(),Permission.YES,Rarity.RARE,
                true,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication abilitySpecyfication = new AbilitySpecyfication(AE.ABILITY.getValue(),R.drawable.heal,10000,new Heal(50),Permission.YES,Rarity.COMMON,
                false,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication nothing= new AbilitySpecyfication(AE.NOTHING,R.drawable.redx,10000,new Empty(),Permission.YES,Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication summonLog= new AbilitySpecyfication(AE.SUMMONLOG,R.drawable.log,10000,new TimeChangeBullet(BulletSet.getBullet(BE.LOG),5),
                Permission.NOT,Rarity.UNCOMMON,false,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication armchairthrow= new AbilitySpecyfication(AE.ARMCHAIRTHROW,R.drawable.grendaamchair,10000,new SuperShoot(BulletSet.getBullet(BE.GRENDAARMCHAIR)),
                Permission.YES,Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication firstJurnal= new AbilitySpecyfication(AE.FIRSTJURNAL,R.drawable.jurnal1,10000,new ChangeBullet(BulletSet.getBullet(BE.FIRSTJURNAL)),
                Permission.YES,Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10));
        WaitAbilitySpecyfication secondJurnal= new WaitAbilitySpecyfication(AE.SECONDJURNAL,R.drawable.jurnal2,0,new ChangeBullet(BulletSet.getBullet(BE.SEONDJURNAL)),
                Permission.YES,Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10),
                new BulletSpecyfication(BulletSet.getBullet(BE.FIRSTJURNAL)),5);
        WaitAbilitySpecyfication thirdJurnal= new WaitAbilitySpecyfication(AE.THIRDJURNAL,R.drawable.jurnal3,0,new ChangeBullet(BulletSet.getBullet(BE.THIRDJURNAL)),
                Permission.YES,Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10),
                new BulletSpecyfication(BulletSet.getBullet(BE.SEONDJURNAL)),10);
        AppearAction aa= new NothingAppearAction();
        Beast gompers= new Beast(context,10,10,null,200,200,R.drawable.goat,
                null,40,120,2,new Resistance(5,10),BulletSet.getBullet(BE.RED),"Gomper",Kind.MONSTER,Arrays.asList(GroupName.Animal),
                CharacterPositioning.HEROSUMMONEDBEAST,new NoneDoToBulletStrategy(),"",new Description(),EBeastType.HERO,aa);

        Beast dino1= new Beast(context,10,10,null,200,200,R.drawable.diplodok,
                null,40,120,2,new Resistance(5,10),BulletSet.getBullet(BE.RED),"Gomper",Kind.MONSTER,Arrays.asList(GroupName.Animal),
                CharacterPositioning.HEROSUMMONEDBEAST,new NoneDoToBulletStrategy(),"",new Description(),EBeastType.HERO,aa);
        Beast dino2= new Beast(context,10,10,null,200,200,R.drawable.raptor,
                null,40,120,2,new Resistance(5,10),BulletSet.getBullet(BE.RED),"Gomper",Kind.MONSTER,Arrays.asList(GroupName.Animal),
                CharacterPositioning.HEROSUMMONEDBEAST,new NoneDoToBulletStrategy(),"",new Description(),EBeastType.HERO,aa);
        Beast dino3= new Beast(context,10,10,null,200,200,R.drawable.trex,
                null,40,120,2,new Resistance(5,10),BulletSet.getBullet(BE.RED),"Gomper",Kind.MONSTER,Arrays.asList(GroupName.Animal),
                CharacterPositioning.HEROSUMMONEDBEAST,new NoneDoToBulletStrategy(),"",new Description(),EBeastType.HERO,aa);
        Beast dino4= new Beast(context,10,10,null,200,200,R.drawable.stegozaur,
                null,40,120,2,new Resistance(5,10),BulletSet.getBullet(BE.RED),"Gomper",Kind.MONSTER,Arrays.asList(GroupName.Animal),
                CharacterPositioning.HEROSUMMONEDBEAST,new NoneDoToBulletStrategy(),"",new Description(),EBeastType.HERO,aa);
        List<BeastSpecyfication> dinos= Arrays.asList(new BeastSpecyfication(dino1),new BeastSpecyfication(dino2),new BeastSpecyfication(dino3),new BeastSpecyfication(dino4));

        AbilitySpecyfication dinoSummon= new AbilitySpecyfication(AE.DINOSUMMON,R.drawable.dinosinresin,10000,new RandomSummon(dinos),Permission.YES,Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));

        AbilitySpecyfication firstsummoning= new AbilitySpecyfication(AE.FIRSTSUMMON,R.drawable.goat,10000,new Summon((BeastSpecyfication)gompers.getSpecyfication()),Permission.YES,Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));


        AbilitySpecyfication timemachine= new AbilitySpecyfication(AE.TIMEMACHINE,R.drawable.timemachine,1000,new TimeJump(2000), Permission.YES,Rarity.RARE,true,
                new MoneyPossesStrategy("Mystery Coin",10));

        AbilitySpecyfication counterAttack= new AbilitySpecyfication(AE.FULLCOUNTER,R.drawable.fullcounterbiscuits,1000,new FullCounter(30,3000), Permission.YES,Rarity.RARE,true,
                new MoneyPossesStrategy("Mystery Coin",10));

        // RandomSummon threeDinozours= new AbilitySpecyfication(AE.THREEDINOSAURS,10,10,null,200,200,20,R.drawable.);

        abilitySpecyficationList.add(carpetdiem);
          abilitySpecyficationList.add(abilitySpecyfication);
        abilitySpecyficationList.add(nothing);
        abilitySpecyficationList.add(summonLog);
        abilitySpecyficationList.add(armchairthrow);
        abilitySpecyficationList.add(firstJurnal);
        abilitySpecyficationList.add(secondJurnal);
        abilitySpecyficationList.add(thirdJurnal);
        abilitySpecyficationList.add(firstsummoning);
       abilitySpecyficationList.add(timemachine);
        abilitySpecyficationList.add(dinoSummon);
        abilitySpecyficationList.add(counterAttack);
    }
    public  List<AbilitySpecyfication> getAbilityListForHero(String heroName)
    {
       List<String> abilityNamesList=HeroAbilityBulletMapper.getStringAbilityListForHero(heroName);
       List<AbilitySpecyfication> retur= new LinkedList<>();
       for(String s:abilityNamesList)
       {
           for(AbilitySpecyfication a: abilitySpecyficationList)
           {
               if(a.getName().equals(s)) retur.add(a);
           }
       }
       return retur;
    }


    public boolean isEmpty()
    {
        if(abilitySpecyficationList.size()==0) return true;
        else return false;
    }

    public void clear()
    {
        abilitySpecyficationList.clear();
    }

    public void givePermission(AbilitySpecyfication abilitySpecyfication, Context context)
    {

        for(AbilitySpecyfication a: abilitySpecyficationList)
        {
            if(a.getName().equals(abilitySpecyfication.getName()))
            {
                a.setPermission(Permission.YES);
                save(context);
                break;
            }
        }
    }
    public boolean ifHasThisAbility(String name)
    {
        AbilitySpecyfication abilitySpecyfication = getAbility(name);
        if(abilitySpecyfication.getPermission().equals(Permission.NOT))
        {
            return false;
        }
        else return true;
    }
    public boolean ifHasThisAbility(AbilitySpecyfication abilitySpecyfication)
    {
        return ifHasThisAbility(abilitySpecyfication.getName());
    }

}
