package com.example.user.bulletfalls.Storage.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.HeroAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.HeroCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.HeroPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesCandyManager;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.AngelProtector;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.Breeder;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.HealerC;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.SuperShooter;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.MassDestructor;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.Mugol;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Ability.AbilitiesBar;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Profile.Collection.UserCollection;
import com.example.user.bulletfalls.Storage.Data.CurrencyEnum;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.GlobalUsage.Supporters.FileSupporter;
import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyNeed;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;
import com.example.user.bulletfalls.GlobalUsage.Par;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HeroesSet implements ISet<HeroSpecyfication>  {

    private final static HeroesSet ourInstance=new HeroesSet();
     private List<HeroSpecyfication> heroes=new LinkedList<>();
    final static  private String path="heroes.json";
    FamiliesCandyManager familiesCandyManager= new FamiliesCandyManager();

    private HeroesSet(){
    }

    public static HeroesSet getInstance()
    {
        return ourInstance;
    }

    public  void load(Context context)
    {

        String s=FileSupporter.LoadStrinFromFile(path,context);


        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, HeroSpecyfication.class);
        try {
            this.heroes=mapper.readValue(s,t);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void save(Context context)
    {
        ObjectMapper mapper= new ObjectMapper();
        String s ="";
        try {
            CollectionType t=mapper.getTypeFactory()
                    .constructCollectionType(List.class, HeroSpecyfication.class);
            s= mapper.writerFor(t)
                    .writeValueAsString(heroes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        FileSupporter.WriteToFile(path,context,s);
    }

    @Override
    public List<HeroSpecyfication> getAll() {

        List<HeroSpecyfication> list= new LinkedList<>();
        for(HeroSpecyfication hs:this.heroes){
            list.add((HeroSpecyfication)hs.clone());
        }

        return list;
    }

    public  void setBulletForHero(String name, BulletSpecyfication bullet)
    {
        getHero(name).setBulletSpecyfication(bullet);
    }
    /*-----------------------------------------------Geters and Seters----------------------------------------------------------*/

    public  List<HeroSpecyfication> getHeroesList() {

        return heroes;
    }

    /*----------------------------------TESTS-----------------------------*/
    public  void AddToDatabaseTest(Context context1)
    {
        int p=30;

        MoneyNeed first= new MoneyNeed(Arrays.asList(new Par<Currency,Integer>(new Currency(CurrencyEnum.Connifer),40),
                new Par<Currency,Integer>(new Currency(CurrencyEnum.MysteryCoin),20)));
        MoneyNeed second= new MoneyNeed(Arrays.asList(new Par<Currency,Integer>(new Currency(CurrencyEnum.Connifer),10),
                new Par<Currency,Integer>(new Currency(CurrencyEnum.MysteryCoin),30)));
        PossesStrategy standardPrice= new MoneyPossesStrategy(Arrays.asList(first,second));


        AppearAction nothing= new NothingAppearAction();

        AttackDefenceFilter adf= new AttackDefenceFilter();

        HeroSpecyfication mabel= new HeroSpecyfication("Mabel Pines",
                new CharacterVS(R.drawable.mabel,3,new Description()),
                new HeroPS(10,1500,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("mabel",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication dipper= new HeroSpecyfication("Dipper Pines",
                new CharacterVS(R.drawable.dipper,1,new Description()),
                new HeroPS(30,150,20,BulletSet.getInstance().getBullet(BE.FIRSTJURNAL)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new AngelProtector()),
                new HeroCS("dipper",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication dipperwithmirrors= new HeroSpecyfication("Dipper With Mirrors",
                new CharacterVS(R.drawable.mirroreddipper,1,new Description()),
                new HeroPS(30,150,20,BulletSet.getInstance().getBullet(BE.DISARM)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new AngelProtector()),
                new HeroCS("dipper",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.CHILD),standardPrice,1));

            HeroSpecyfication soos= new HeroSpecyfication("Soos Ramirez",
                new CharacterVS(R.drawable.soos,1,new Description()),
                new HeroPS(20,300,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("soos",Arrays.asList(FamilyName.MysteryShack,FamilyName.Ramirez),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));

        HeroSpecyfication stanek= new HeroSpecyfication("Stanford Pines",
                new CharacterVS(R.drawable.stanek,1,new Description()),
                new HeroPS(20,250,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new HealerC()),
                new HeroCS("stanek",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));

        HeroSpecyfication wendy= new HeroSpecyfication("Wendy Corduroy",
                new CharacterVS(R.drawable.wendy,1,new Description()),
                new HeroPS(20,150,10,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("wendy",Arrays.asList(FamilyName.MysteryShack,FamilyName.Lumberjack),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.TEENEAGER),standardPrice,1));

        HeroSpecyfication waddles= new HeroSpecyfication("Waddles",
                new CharacterVS(R.drawable.waddles,1,new Description()),
                new HeroPS(20,400,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new MassDestructor()),
                new HeroCS("waddles",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.ANIMAL),standardPrice,1));

        HeroSpecyfication grenda= new HeroSpecyfication("Grenda",
                new CharacterVS(R.drawable.grenda,1,new Description()),
                new HeroPS(15,150,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new SuperShooter()),
                new HeroCS("grenda",Arrays.asList(FamilyName.MabelTeam),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication loglandgirl= new HeroSpecyfication("Log Land Girl",
                new CharacterVS(R.drawable.loglandgirl,1,new Description()),
                new HeroPS(20,150,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("loglandgirl",Arrays.asList(),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication tremblin= new HeroSpecyfication("Quentin Trembley",
                new CharacterVS(R.drawable.tremblin,1,new Description()),
                new HeroPS(20,150,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Breeder()),
                new HeroCS("tremblay",Arrays.asList(),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));

        HeroSpecyfication candy= new HeroSpecyfication("Candy Chiu",
                new CharacterVS(R.drawable.candy,1,new Description()),
                new HeroPS(20,100,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("candy",Arrays.asList(FamilyName.MabelTeam),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication mcgucket= new HeroSpecyfication("Old Man McGucket",
                new CharacterVS(R.drawable.mcgucket,1,new Description()),
                new HeroPS(20,200,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("mcgucket",Arrays.asList(FamilyName.Scientists),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));

        HeroSpecyfication shootingmabel= new HeroSpecyfication("Mabel With Grappling Hook",
                new CharacterVS(R.drawable.shootingmabel,1,new Description()),
                new HeroPS(20,150,10,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("mabel",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication robie= new HeroSpecyfication("Robie",
                new CharacterVS(R.drawable.robie,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("robie",Arrays.asList(FamilyName.WendyTeam),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.TEENEAGER),standardPrice,1));

        HeroSpecyfication soossGrandma= new HeroSpecyfication(HE.SOOSSGRANDMA,
                new CharacterVS(R.drawable.soossgrandma,1.3f,new Description()),
                new HeroPS(12,260,20,BulletSet.getInstance().getBullet(BE.COUNTERBULLET)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("soossgrandma",Arrays.asList(FamilyName.Ramirez),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.SENIOR),standardPrice,3));

        HeroSpecyfication preston= new HeroSpecyfication(HE.PRESTONNORTHWEST,
                new CharacterVS(R.drawable.prestonnorthwest,1.7f,new Description()),
                new HeroPS(10,300,10,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("preston",Arrays.asList(FamilyName.Northwest),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));

        HeroSpecyfication priscilla= new HeroSpecyfication(HE.PRISCILLANORTHWEST,
                new CharacterVS(R.drawable.priscillanorthwest,1.5f,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("priscilla",Arrays.asList(FamilyName.Northwest),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.ADULT),standardPrice,1));

        HeroSpecyfication prestonpremium= new HeroSpecyfication(HE.PRESTONPREMIUM,
                new CharacterVS(R.drawable.prestonnorthwestpremiumdone,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("preston",Arrays.asList(FamilyName.Northwest),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));
        HeroSpecyfication priscillapremium= new HeroSpecyfication(HE.PRISCILLAPREMIUM,
                new CharacterVS(R.drawable.priscillanorthwestpremium,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("priscilla",Arrays.asList(FamilyName.Northwest),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.TEENEAGER),standardPrice,1));

        HeroSpecyfication pacifica= new HeroSpecyfication(HE.PACIFIC,
                new CharacterVS(R.drawable.pacific,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("pacific",Arrays.asList(FamilyName.Northwest),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication gideon =new HeroSpecyfication(HE.GIDEONGLEEFUL,
                new CharacterVS(R.drawable.gideon,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("gideon",Arrays.asList(FamilyName.TentOfThelepathy),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.CHILD),standardPrice,1));

        HeroSpecyfication mrsgleeful= new HeroSpecyfication(HE.MRSGLEEFUL,
                new CharacterVS(R.drawable.mrsgleeful,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("mrsgleeful",Arrays.asList(FamilyName.TentOfThelepathy),Arrays.asList(Kind.HUMAN,Kind.WOMEN,Kind.ADULT),standardPrice,1));
        HeroSpecyfication budgleeful= new HeroSpecyfication(HE.BUDGLEEFUL,
                new CharacterVS(R.drawable.buddgleeful,1,new Description()),
                new HeroPS(20,160,20,BulletSet.getInstance().getBullet(BE.STANDARD)),
                new HeroAS(CharacterPositioning.LEFTCENTER,adf,nothing,new Mugol()),
                new HeroCS("bud",Arrays.asList(FamilyName.TentOfThelepathy),Arrays.asList(Kind.HUMAN,Kind.MEN,Kind.ADULT),standardPrice,1));


        heroes.add(mabel);
        heroes.add(dipper);
        heroes.add(soos);
        heroes.add(stanek);
        heroes.add(wendy);
        heroes.add(waddles);
        heroes.add(grenda);
        heroes.add(loglandgirl);
        heroes.add(tremblin);
        heroes.add(candy);
        heroes.add(mcgucket);
        heroes.add(shootingmabel);
        heroes.add(robie);
        heroes.add(soossGrandma);
        heroes.add(pacifica);
        heroes.add(preston);
        heroes.add(prestonpremium);
        heroes.add(priscilla);
        heroes.add(priscillapremium);
        heroes.add(gideon);
        heroes.add(budgleeful);
        heroes.add(mrsgleeful);
        heroes.add(dipperwithmirrors);
        //RotateBullet wendyAxe= new RotateBullet(BE.WENDYAXE,context, 10, 20, null, 100, 100,  R.drawable.wendyaxe, null, false,1,new Horizontal(),Shape.RECTANGLE,Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",10));
        //wendy.setBullet(wendyAxe);
    }



    //------------------------------------BIZNES METHODS----------------------//
    public  HeroSpecyfication getHero(String name)
    {
        for(HeroSpecyfication h:heroes)
        {
            if(h.getName().equals(name)) return h;
        }
        return null;
    }
    public  boolean isEmpty()
    {
        if(heroes.size()==0) return true;
        else return false;
    }

    public  void clear()
    {
        heroes.clear();
    }

    public  void setAbilitiesForHero(Hero hero, List<AbilitySpecyfication> abilities)
    {
        getHero(hero.getName()).setAbilities(new AbilitiesBar(abilities));
    }
    public boolean ifHasThisHero(String name)
    {
        return true;
    }
    public boolean ifHasThisHero(Hero hero)
    {
        return ifHasThisHero(hero.getName());
    }

    public float getGroupPercentage(FamilyName familyName)
    {
        return ((float)getPossesedCandyfFromTheFamily(familyName))/((float) getNumberOfFamilysCandy(familyName));
    }
    public int getNumberOfFamilysCandy(FamilyName familyName)
    {
        int counter=0;
        for(HeroSpecyfication hero :heroes)
        {
            if(hero.isFromFamiy(familyName))
            {
                counter+=familiesCandyManager.countHeroCandy(hero,familyName);

            }
        }
        return counter;
    }

    public int getPossesedCandyfFromTheFamily(FamilyName familyName)
    {
        int counter=0;
        for(HeroSpecyfication hero :heroes)
        {
            if(hero.isFromFamiy(familyName)&& UserCollection.getInstance().doYouOwnIt(hero))
            {

                counter+=familiesCandyManager.countHeroCandy(hero,familyName);
            }
        }
        return counter;
    }

    public int getAmountOfOwnedMembers(FamilyName familyName){
        int counter=0;
        for(HeroSpecyfication hero :heroes)
        {
            if(hero.isFromFamiy(familyName)&& UserCollection.getInstance().doYouOwnIt(hero))
            {

                counter+=1;
            }
        }
        return counter;
    }

    public  List<HeroSpecyfication> getHeroesList(Family family)
    {
        List<HeroSpecyfication> heroList =new LinkedList<>();
        for(HeroSpecyfication h: heroes)
        {
            if(h.getFamilyNames().contains(family.getGroupName()))
            heroList.add(h);
        }

        return heroList;
    }

    public  List<HeroSpecyfication> getMarkedList(String mark)
    {
        List<HeroSpecyfication> markedList =new LinkedList<>();
        for(HeroSpecyfication h: heroes)
        {
            if(h.getIndyvidualHeroMarker().equals(mark))
                markedList.add(h);
        }

        return markedList;
    }

}
