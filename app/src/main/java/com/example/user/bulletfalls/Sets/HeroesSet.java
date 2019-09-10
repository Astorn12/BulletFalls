package com.example.user.bulletfalls.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Enums.AE;
import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.GameBiznesFunctions.Classes.AngelProtector;
import com.example.user.bulletfalls.GameBiznesFunctions.Classes.Breeder;
import com.example.user.bulletfalls.GameBiznesFunctions.Classes.HealerC;
import com.example.user.bulletfalls.GameBiznesFunctions.Classes.MassDestructor;
import com.example.user.bulletfalls.GameBiznesFunctions.Classes.Mugol;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.Group;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Objects.BarAbilities;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.RotateBullet;
import com.example.user.bulletfalls.Database.Data.CurrencyRepository;
import com.example.user.bulletfalls.Database.Data.CurrencyEnum;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.HeroSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.Supporters.FileSupporter;
import com.example.user.bulletfalls.ProfileActivity.Currency;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyNeed;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.MoneyPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.PossesStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.Stot;
import com.example.user.bulletfalls.Strategies.Par;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.TimePossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.TimeStrategiesPackage.DayOfWeekPossesStrategy;
import com.example.user.bulletfalls.Strategies.PossesStrategyPackage.TimeStrategiesPackage.TimeInDayPossesStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HeroesSet  {
    static private List<Hero> heroesList=new LinkedList<>();
    final static private String path="heroes.json";

    public static void Load(Context context)
    {
        List<HeroSpecyfication> bd= new LinkedList<>();
        String s=FileSupporter.LoadStrinFromFile(path,context);


        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, HeroSpecyfication.class);
        try {
            bd=mapper.readValue(s,t);

            for(HeroSpecyfication bs: bd)
            {
                heroesList.add(new Hero(context,bs));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void Save(Context context)
    {
        List<HeroSpecyfication> bd= new LinkedList<>();

        for(Hero b : heroesList)
        {

            bd.add(new HeroSpecyfication(b));

        }
        ObjectMapper mapper= new ObjectMapper();
        String s ="";
        try {
            CollectionType t=mapper.getTypeFactory()
                    .constructCollectionType(List.class, HeroSpecyfication.class);
            s= mapper.writerFor(t)
                    .writeValueAsString(bd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        FileSupporter.WriteToFile(path,context,s);
    }

    public static void setBulletForHero(String name, Bullet bullet)
    {
        getHero(name).setBullet(bullet);
    }
    /*-----------------------------------------------Geters and Seters----------------------------------------------------------*/

    public static List<Hero> getHeroesList(Context context) {

        List<Hero> heroList =new LinkedList<>();
        for(Hero h: heroesList)
        {
          heroList.add(h.changeContext(context));
        }
        return heroList;
    }

    public static void setHeroesList(List<Hero> heroesList) {
        HeroesSet.heroesList = heroesList;
    }


    /*----------------------------------TESTS-----------------------------*/
    public static void AddToDatabaseTest(Context context1)
    {
       // AbilitySpecyfication abilitySpecyfication= new AbilitySpecyfication("abilitySpecyfication",R.drawable.heal,10000,new Heal(50),Permission.YES);
        AbilitySpecyfication abilitySpecyfication = AbilitySet.getInstance().getAbility(AE.ABILITY);
        //AbilitySpecyfication nothing= new AbilitySpecyfication("nothing",R.drawable.black,10000,new Empty(),Permission.YES);
        AbilitySpecyfication nothing= AbilitySet.getInstance().getAbility(AE.NOTHING);
        AbilitySpecyfication gompers= AbilitySet.getInstance().getAbility(AE.FIRSTSUMMON);

        BarAbilities abilitirs= new BarAbilities(nothing.clone(),nothing.clone(),gompers);
        int p=30;
        Context context= context1.getApplicationContext();
        CurrencyRepository currencyDao= new CurrencyRepository(context1);

        PossesStrategy ps= new MoneyPossesStrategy("Mystery Coin",10);

        List<MoneyNeed> moneyNeeds= new LinkedList<>();

        MoneyNeed first= new MoneyNeed(Arrays.asList(new Par<Currency,Integer>(new Currency(CurrencyEnum.Connifer),40),
                new Par<Currency,Integer>(new Currency(CurrencyEnum.MysteryCoin),20)));
        MoneyNeed second= new MoneyNeed(Arrays.asList(new Par<Currency,Integer>(new Currency(CurrencyEnum.Connifer),10),
                new Par<Currency,Integer>(new Currency(CurrencyEnum.MysteryCoin),30)));
        PossesStrategy extendedPossesStrategy= new MoneyPossesStrategy(Arrays.asList(first,second));


        AppearAction aa= new NothingAppearAction();
        Mugol mugol= new Mugol();
        Hero hero1 = new Hero(context,10,20,null,p,p,R.drawable.eater,null/*(FrameLayout) this.findViewById(R.id.frame)*/,100,20,1,new Resistance(0,0),null,"Eater",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"żaden",Permission.YES,new Description(),ps,1,aa,mugol);
        Hero hero2 = new Hero(context,10,20,null,p,p,R.drawable.rinor,null/*(FrameLayout) this.findViewById(R.id.frame)*/,100,20,1,new Resistance(0,0),null,"Rinor",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"żaden",Permission.YES,new Description(),ps,1,aa,mugol);
        Hero hero3 = new Hero(context,10,20,null,p,p,R.drawable.pansyk,null/*(FrameLayout) this.findViewById(R.id.frame)*/,100,20,1,new Resistance(0,0),null,"Pansyk",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"żaden",Permission.YES,new Description(),ps,1,aa,mugol);

        Hero mabel= new Hero(context,10,30,null,p,p,R.drawable.mabel,null/*(FrameLayout) this.findViewById(R.id.frame)*/,0,20,1,new Resistance(0,0),
                null,"Mabel Pines",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"mabel",Permission.YES,
                new Description(),ps,1,aa,mugol);
        BarAbilities bar= new BarAbilities(AbilitySet.getInstance().getAbility(AE.CARPEDIEM),AbilitySet.getInstance().getAbility(AE.ABILITY),AbilitySet.getInstance().getAbility(AE.SUMMONLOG));
        mabel.setAbilities(bar);

        Hero dipper= new Hero(context,10,20,null,p,p,R.drawable.dipper,null/*(FrameLayout) this.findViewById(R.id.frame)*/,100,70,1,new Resistance(0,0),null,"Dipper Pines",null,Arrays.asList(GroupName.MysteryShack),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"dipper",Permission.YES,new Description(),ps,1,aa,mugol);
        AbilitySet as=AbilitySet.getInstance();
        BarAbilities dipperBarAbilities=new BarAbilities(as.getAbility(AE.FIRSTJURNAL),as.getAbility(AE.SECONDJURNAL),as.getAbility(AE.THIRDJURNAL));
        dipper.setAbilities(dipperBarAbilities);

        Hero soos= new Hero(context,10,20,null,p,p,R.drawable.soos,null/*(FrameLayout) this.findViewById(R.id.frame)*/,100,50,1,new Resistance(0,0),null,"Soos Ramirez",null,Arrays.asList(GroupName.MysteryShack),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"soos",Permission.NOT,new Description(),new TimePossesStrategy(new DayOfWeekPossesStrategy(3)),2,aa,mugol);
        Hero stanek= new Hero(context,5,20,null,p,p,R.drawable.stanek,null/*(FrameLayout) this.findViewById(R.id.frame)*/,1000,300,1,new Resistance(0,0),null,"Stan Pines",null,Arrays.asList(GroupName.MysteryShack),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"stanek",Permission.YES,new Description(),ps,3,aa,mugol);
        Hero wendy= new Hero(context,10,20,null,p,p,R.drawable.wendy,null/*(FrameLayout) this.findViewById(R.id.frame)*/,100,20,1,new Resistance(0,0),null,"Wendy Corduroy",null,Arrays.asList(GroupName.MysteryShack,GroupName.Lumberjack),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"wendy",Permission.YES,new Description(),extendedPossesStrategy,2,aa,mugol);
        Hero waddles= new Hero(context,4,10,null,p,p,R.drawable.waddles,null/*(FrameLayout) this.findViewById(R.id.frame)*/,500,400,1,new Resistance(20,1),null,"Waddles",null,Arrays.asList(GroupName.MysteryShack),CharacterPositioning.LEFTCENTER,new Stot(10),abilitirs,"waddle",Permission.YES,new Description(),ps,1,aa,new MassDestructor());
        Hero grenda= new Hero(context,4,10,null,p,p,R.drawable.grenda,null/*(FrameLayout) this.findViewById(R.id.frame)*/,200,50,1,new Resistance(0,0),null,"Grenda",null,Arrays.asList(GroupName.MabelTeam),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"grenda",Permission.YES,new Description(),ps,1,aa,mugol);
        Hero loglandgirl= new Hero(context,4,10,null,p,p,R.drawable.loglandgirl,null/*(FrameLayout) this.findViewById(R.id.frame)*/,200,50,1,new Resistance(0,0),null,"Log Land Girl",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"loglandgirl",Permission.YES,new Description(),ps,1,aa,new HealerC());
        Hero tremblin= new Hero(context,4,10,null,40,p,R.drawable.tremblin,null/*(FrameLayout) this.findViewById(R.id.frame)*/,200,50,1,new Resistance(0,0),null,"Quentin Trembley",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"quentintrembley",Permission.YES,new Description(),extendedPossesStrategy,1,aa,new Breeder());
        Hero candy= new Hero(context,4,10,null,40,p,R.drawable.candy,null/*(FrameLayout) this.findViewById(R.id.frame)*/,200,50,1,new Resistance(0,0),null,"Candy Chiu",null,Arrays.asList(GroupName.MabelTeam),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"candy",Permission.YES,new Description(),ps,1,aa,mugol);
        Hero mcgucket= new Hero(context,4,10,null,40,p,R.drawable.mcgucket,null/*(FrameLayout) this.findViewById(R.id.frame)*/,200,50,1,new Resistance(0,0),null,"Old Man McGucket",null,Arrays.asList(GroupName.Null),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"mcgucket",Permission.NOT,new Description(),new TimePossesStrategy(new TimeInDayPossesStrategy(9,0,10,30)),1,aa,mugol);
        Hero shootingMabel= new Hero(context,4,10,null,40,p,R.drawable.shootingmabel,null/*(FrameLayout) this.findViewById(R.id.frame)*/,200,50,1,new Resistance(0,0),null,"Mabel With Grappling Hook",null,Arrays.asList(GroupName.MysteryShack),CharacterPositioning.LEFTCENTER,new NoneDoToBulletStrategy(),abilitirs,"mabel",Permission.YES,new Description(),ps,1,aa,new AngelProtector());
        List<Hero> heroes = heroesList;
        heroes.add(hero1);
        heroes.add(hero2);
        heroes.add(hero3);
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
        heroes.add(shootingMabel);

        for(Hero h: heroes)
        {
            if(h.getBullet()==null)
                //h.setBulletSpecyfication(new BulletSpecyfication("standardherobullet", context,10, 20, null, 50, 50, 20, R.drawable.blue, null, false,new Horizontal(),Shape.CIRCLE,new NoneDoToBulletStrategy(),Permission.YES));
                h.setBullet(BulletSet.getBullet(BE.STANDARD));
        }

        //AbilitySpecyfication carpetdiem= new AbilitySpecyfication("carpediem",R.drawable.carpetsmall,3000,new CarpetDiem(),Permission.YES);
        BarAbilities standardCandy= new BarAbilities(abilitySpecyfication, abilitySpecyfication,AbilitySet.getInstance().getAbility(AE.CARPEDIEM));
        candy.setAbilities(standardCandy);

        tremblin.setAbilities(new BarAbilities(abilitySpecyfication, abilitySpecyfication,nothing));
        RotateBullet wendyAxe= new RotateBullet(BE.WENDYAXE,context, 10, 20, null, 100, 100,  R.drawable.wendyaxe, null, false,1,new Horizontal(),Shape.RECTANGLE,Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",10));
        wendy.setBullet(wendyAxe);
        //BulletSpecyfication armchair=new BulletSpecyfication("armchair",context,100,20,null,110,110,20,R.drawable.grendaamchair,null,false,new Throw(45),Shape.RECTANGLE,new NoneDoToBulletStrategy(),Permission.YES);  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind
        //grenda.setBulletSpecyfication(armchair);
        grenda.setBullet(BulletSet.getBullet(BE.GRENDAARMCHAIR));
        //AbilitySpecyfication armchairthrow= new AbilitySpecyfication("airmchairthrow",R.drawable.grendaamchair,10000,new SuperShoot(BulletSet.getBulletSpecyfication("grendaArmchair")),Permission.YES);

        grenda.setAbilities(new BarAbilities(abilitySpecyfication, abilitySpecyfication,AbilitySet.getInstance().getAbility(AE.ARMCHAIRTHROW)));
        grenda.setIrrealHeight(84);
        grenda.setIrrealWidth(61);
        //BulletSpecyfication dam=new BulletSpecyfication("dam",context,100,20,null,110,110,20,R.drawable.dam,null,true,new Dam(300),Shape.RECTANGLE,new NoneDoToBulletStrategy(),Permission.YES);
        //soos.setBulletSpecyfication(dam);
        soos.setBullet(BulletSet.getBullet(BE.DAM));

        //BulletSpecyfication timeDam=new BulletSpecyfication("tiemDam",context,100,20,null,110,110,20,R.drawable.dam,null,true,new TimeDam(300,100),Shape.RECTANGLE,new NoneDoToBulletStrategy(),Permission.YES);

        dipper.setBullet(BulletSet.getBullet(BE.TIMEDAM));
        //BulletSpecyfication log=new BulletSpecyfication("log",context,100,20,null,110,110,20,R.drawable.log,null,true,new SummonDam(300),Shape.RECTANGLE,new NoneDoToBulletStrategy(),Permission.YES);
        //AbilitySpecyfication summonLog= new AbilitySpecyfication("summonlog",R.drawable.log,10000,new TimeChangeBullet(BulletSet.getBulletSpecyfication("log"),5),Permission.YES);
        BarAbilities abilitirslog= new BarAbilities(abilitySpecyfication, abilitySpecyfication,AbilitySet.getInstance().getAbility(AE.SUMMONLOG));

        stanek.setBullet(BulletSet.getBullet(BE.DISARM));

        //soos.setBulletSpecyfication(new BulletSpecyfication("soosbullet",context, 10, 20, null, 50, 50, 20, R.drawable.blue, null, false,new Horizontal(),Shape.CIRCLE,new Poison(1000,10000,10,1000),Permission.YES));

        //Abilitki z jurnalami
        // BulletSpecyfication jurnal1=new BulletSpecyfication(this.getApplicationContext(),100,20,null,110,110,20,R.drawable.,null,false,new Throw(45),Shape.RECTANGLE);  //tutaj trzeba będzie zamienić na kod który tworzy kulki określonego rodzaju wykorzystująć klasę BulletKind
        // loglandgirl.setBulletSpecyfication(log);
        loglandgirl.setAbilities(abilitirslog);

        for(Hero h: heroes)
        {
            if(h.getBullet()==null)h.setBullet(BulletSet.getBullet(BE.STANDARD));
        }

    }
    //------------------------------------BIZNES METHODS----------------------//
    public static Hero getHero(String name)
    {
        for(Hero h:heroesList)
        {
            if(h.getName().equals(name)) return h;
        }
        return null;
    }
    public static boolean isEmpty()
    {
        if(heroesList.size()==0) return true;
        else return false;
    }

    public static void clear()
    {
        heroesList.clear();
    }

    public static void setAbilitiesForHero(Hero hero, List<AbilitySpecyfication> abilities)
    {
        getHero(hero.getName()).setAbilities(new BarAbilities(abilities));
    }
    public boolean ifHasThisHero(String name)
    {
        Hero hero = getHero(name);
        if(hero.getPermission().equals(Permission.NOT))
        {
            return false;
        }
        else return true;
    }
    public boolean ifHasThisHero(Hero hero)
    {
        return ifHasThisHero(hero.getName());
    }
    public static void givePermission(Hero hero, Context context)
    {
        for(Hero h:heroesList)
        {
            if(h.getName().equals(hero.getName()))
            {
                h.setPermission(Permission.YES);
                Save(context);
                break;
            }
        }
    }
    public float getGroupPercentage(GroupName groupName)
    {
        return ((float)getPossesedCharacterfFromTheGroup(groupName))/((float)getNumberOfCharacterOfGroup(groupName));
    }
    public int getNumberOfCharacterOfGroup(GroupName groupName)
    {
        int counter=0;
        for(Hero hero :this.heroesList)
        {
            if(hero.isFromGroup(groupName))
            {
                counter++;
            }
        }
        return counter;
    }

    public int getPossesedCharacterfFromTheGroup(GroupName groupName)
    {
        int counter=0;
        for(Hero hero :this.heroesList)
        {
            if(hero.isFromGroup(groupName)&& hero.isPermitted())
            {
                counter++;
            }
        }
        return counter;
    }

    public static List<Hero> getHeroesList(Context context, Group group)
    {
        List<Hero> heroList =new LinkedList<>();
        for(Hero h: heroesList)
        {
            if(h.getGroupNames().contains(group.getGroupName()))
            heroList.add(h.changeContext(context));
        }

        return heroList;
    }

    public static List<Hero> getMarkedList(Context context,String mark)
    {
        List<Hero> markedList =new LinkedList<>();
        for(Hero h: heroesList)
        {
            if(h.getIndyvidualHeroMarker().equals(mark))
                markedList.add(h.changeContext(context));
        }

        return markedList;
    }
}
