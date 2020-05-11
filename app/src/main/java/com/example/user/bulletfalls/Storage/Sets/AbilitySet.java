package com.example.user.bulletfalls.Storage.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationAndActionSimultaneously;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationBeforeAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.HeroAnimation;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.HeroDance;
import com.example.user.bulletfalls.Game.ActionService.Actions.AnimationAction;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.AnimatedStartAction;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ProtectedBall;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.GlobalUsage.Enums.AE;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.WaitAbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ShootBooster;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastChosers.AllOfTheme;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastChosers.Progress;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastChosers.RoundRobin;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers.AlwaysOne;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastStoragers.AsList;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastStoragers.Single;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.SummonStrategy;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.ProgressAmountMassSummoner;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.RandomSummon;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.FullCounter;
import com.example.user.bulletfalls.GlobalUsage.Exceptions.IncorrectBeastNameException;
import com.example.user.bulletfalls.GlobalUsage.Supporters.FileSupporter;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.CarpetDiem;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ChangeBullet;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Empty;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Heal;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SuperShoot;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeChangeBullet;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.TimeJump;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AbilitySet implements ISet<AbilitySpecyfication> {

    private final static AbilitySet ourInstance=new AbilitySet();
    private List<AbilitySpecyfication> abilities =new LinkedList<>();
    final private String path="abilities.json";

    private AbilitySet()
    {

    }

    public static AbilitySet getInstance()
    {
        return ourInstance;
    }
    //------------------------------------BIZNES METHODS----------------------//




    public  void load(Context context)
    {
        String s=FileSupporter.LoadStrinFromFile(path,context);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, AbilitySpecyfication.class);
        try {
            abilities =mapper.readerFor(t).readValue(s);
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
            s= mapper.writerFor(t).writeValueAsString(abilities);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        FileSupporter.WriteToFile(path,context,s);


    }










    /*-----------------------------------------------Geters and Seters----------------------------------------------------------*/


    public List<AbilitySpecyfication> getAbilities() {
        return abilities;
    }

    @Override
    public List<AbilitySpecyfication> getAll() {
        return abilities;
    }


    public AbilitySpecyfication getAbility(String name)
    {
        for(AbilitySpecyfication a: abilities)
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
    public void AddToDatabaseTest()
    {


        AbilitySpecyfication carpetdiem= new AbilitySpecyfication(AE.CARPEDIEM.getValue(),R.drawable.carpetsmall,3000,new CarpetDiem(),Rarity.RARE,
                true,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication abilitySpecyfication = new AbilitySpecyfication(AE.ABILITY.getValue(),R.drawable.heal,10000,new Heal(50),Rarity.COMMON,
                false,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication nothing= new AbilitySpecyfication(AE.NOTHING,R.drawable.redx,10000,new Empty(),Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication summonLog= new AbilitySpecyfication(AE.SUMMONLOG,R.drawable.log,10000,new TimeChangeBullet(BulletSet.getInstance().getBullet(BE.LOG),5),
              Rarity.UNCOMMON,false,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication armchairthrow= new AbilitySpecyfication(AE.ARMCHAIRTHROW,R.drawable.grendaamchair,5000,

                new AnimatedStartAction(new SuperShoot(BulletSet.getInstance().getBullet(BE.GRENDAARMCHAIR).clone()),
                        new HeroAnimation(new HeroDance(HE.GRENDA,R.drawable.grenda_shoot_animation,8)),new AnimationBeforeAction()),
                Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication firstJurnal= new AbilitySpecyfication(AE.FIRSTJURNAL,R.drawable.jurnal1,10000,new ChangeBullet(BulletSet.getInstance().getBullet(BE.FIRSTJURNAL)), Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10));
        WaitAbilitySpecyfication secondJurnal= new WaitAbilitySpecyfication(AE.SECONDJURNAL,R.drawable.jurnal2,0,new ChangeBullet(BulletSet.getInstance().getBullet(BE.SECONDJURNAL)),
                Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10),
                BulletSet.getInstance().getBullet(BE.FIRSTJURNAL),5);
        WaitAbilitySpecyfication thirdJurnal= new WaitAbilitySpecyfication(AE.THIRDJURNAL,R.drawable.jurnal3,0,new ChangeBullet(BulletSet.getInstance().getBullet(BE.THIRDJURNAL)),
                Rarity.LEGENDARY,true,new MoneyPossesStrategy("Mystery Coin",10),
               BulletSet.getInstance().getBullet(BE.SECONDJURNAL),10);


        List<BeastSpecyfication> dinos= BeastsSet.getInstance().getChosen("dino1","dino2","dino3","dino4");
        AbilitySpecyfication dinoSummon= new AbilitySpecyfication(AE.DINOSUMMON,R.drawable.dinosinresin,10000,
                new AnimatedStartAction( new RandomSummon(dinos), new HeroAnimation(new HeroDance(HE.QUENTINTREBLEY,R.drawable.quentintrembley_empty_animation,1)),new AnimationBeforeAction()),


                Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));

        AbilitySpecyfication firstsummoning= new AbilitySpecyfication(AE.FIRSTSUMMON,R.drawable.goat,10000,new SummonStrategy(new Single("Gompers"),new AlwaysOne(),new AllOfTheme()),Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));




        AbilitySpecyfication timemachine= new AbilitySpecyfication(AE.TIMEMACHINE,R.drawable.timemachine,1000,new TimeJump(2000), Rarity.RARE,true,
                new MoneyPossesStrategy("Mystery Coin",10));

        AbilitySpecyfication counterAttack= new AbilitySpecyfication(AE.FULLCOUNTER,R.drawable.fullcounterbiscuits,1000,new FullCounter(30,3000), Rarity.RARE,true,
                new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication shootingBooster= new AbilitySpecyfication(AE.INCREASESHOOTING,R.drawable.increaseshooting,1000,new ShootBooster(BulletSet.getInstance().getBullet(BE.RED)), Rarity.RARE,true,
                new MoneyPossesStrategy("Mystery Coin",10));
        // RandomSummon threeDinozours= new AbilitySpecyfication(AE.THREEDINOSAURS,10,10,null,200,200,20,R.drawable.);
        AbilitySpecyfication beaverProgressAttack= null;
        try {
            beaverProgressAttack = new AbilitySpecyfication(AE.MULTIBEAVERSATTACK,R.drawable.beavers,1000,new ProgressAmountMassSummoner(BeastsSet.getInstance().getByName("Beaver"),2), Rarity.RARE,true,
                    new MoneyPossesStrategy("Mystery Coin",10));
        } catch (IncorrectBeastNameException e) {
            e.printStackTrace();
        }

        AbilitySpecyfication rrsummoning= new AbilitySpecyfication(AE.PSTEST,R.drawable.multisummoning,1000,new SummonStrategy(new AsList("Beaver","Unicorn","Waddles Beast"),new AlwaysOne(),new RoundRobin()),Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));
        AbilitySpecyfication progress= new AbilitySpecyfication(AE.PROGRESS,R.drawable.beavers,1000,new SummonStrategy(new AsList("Beaver","Unicorn","Waddles Beast"),new AlwaysOne(),new Progress(2)),Rarity.STARTING,false,
                new MoneyPossesStrategy("Mystery Coin",10));

        AbilitySpecyfication hamsterBall= new AbilitySpecyfication(AE.HAMSTERBALL.getValue(),R.drawable.hamsterball,3000,new ProtectedBall(R.drawable.hamsterball,6),Rarity.RARE,
                true,new MoneyPossesStrategy("Mystery Coin",10));


        abilities.add(carpetdiem);
        abilities.add(abilitySpecyfication);
        abilities.add(nothing);
        abilities.add(summonLog);
        abilities.add(armchairthrow);
        abilities.add(firstJurnal);
        abilities.add(secondJurnal);
        abilities.add(thirdJurnal);
        abilities.add(firstsummoning);
        abilities.add(timemachine);
        abilities.add(dinoSummon);
        abilities.add(counterAttack);
        abilities.add(shootingBooster);
        abilities.add(beaverProgressAttack);
        abilities.add(rrsummoning);
        abilities.add(progress);
        abilities.add(hamsterBall);

    }
    public  List<AbilitySpecyfication> getAbilityListForHero(String heroName)
    {
       List<String> abilityNamesList=HeroAbilityBulletMapper.getStringAbilityListForHero(heroName);
       List<AbilitySpecyfication> retur= new LinkedList<>();
       for(String s:abilityNamesList)
       {
           for(AbilitySpecyfication a: abilities)
           {
               if(a.getName().equals(s)) retur.add(a);
           }
       }
       return retur;
    }


    public boolean isEmpty()
    {
        if(abilities.size()==0) return true;
        else return false;
    }

    public void clear()
    {
        abilities.clear();
    }

    public void givePermission(AbilitySpecyfication abilitySpecyfication, Context context)
    {

        for(AbilitySpecyfication a: abilities)
        {
            if(a.getName().equals(abilitySpecyfication.getName()))
            {

                UserCollection.getInstance().succesfulTransaction(a);
                save(context);
                break;
            }
        }
    }
    public boolean ifHasThisAbility(String name)
    {
        AbilitySpecyfication abilitySpecyfication = getAbility(name);
        if(!UserCollection.getInstance().doYouOwnIt(abilitySpecyfication))
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
