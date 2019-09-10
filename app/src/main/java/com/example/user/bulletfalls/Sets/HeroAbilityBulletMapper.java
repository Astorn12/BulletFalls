package com.example.user.bulletfalls.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Enums.AE;
import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.Enums.HE;
import com.example.user.bulletfalls.Supporters.FileSupporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HeroAbilityBulletMapper {

   static List<HeroAB> habList= new LinkedList<>();
    final static private String path="hab.json";
    /*-----------------------------Constructors---------------------------*/






    /*-----------------------------load & save---------------------------*/
    public static void Load(Context context)
    {
        String s=FileSupporter.LoadStrinFromFile(path,context);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, HeroAB.class);
        try {
            habList=mapper.readValue(s,t);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void Save(Context context)
    {
        ObjectMapper mapper= new ObjectMapper();
        String s ="";
        try {
            s= mapper
                    .writeValueAsString(habList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        //  String s=new Gson().toJson(bulletList);
        FileSupporter.WriteToFile(path,context,s);
    }

    public static List<String> getStringAbilityListForHero(String heroName) {
        return getABHero(heroName).availlableAbilities;
    }
    public static List<String> getStringBulletListForHero(String heroName) {
        return getABHero(heroName).availlableBullets;
    }





    public static HeroAB getABHero(String heroName)
    {
        for(HeroAB h :habList)
        {
            if(h.heroName.equals(heroName))return h;
        }
        return null;//tu powinna być obsługa wyjątku nieznalezienia HeroAB o podanym imieniu
    }


    /*-----------------------------Geters & Setters---------------------------*/
    public static List<HeroAB> getAbList() {
        return habList;
    }



    public void setAbList(List<HeroAB> abList) {
        this.habList = abList;
    }



     public static void AddToDatabaseTest(Context context)
     {
        HeroAB mabel= new HeroAB(HE.MABELPINES);
        mabel.setAvaillableAbilitiesenums( Arrays.asList(AE.CARPEDIEM,AE.SUMMONLOG,AE.ABILITY,AE.ARMCHAIRTHROW,AE.NOTHING));
        mabel.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD,BE.GRENDAARMCHAIR,BE.DAM,BE.TIMEDAM,BE.LOG,BE.RED,BE.DISARM));
        habList.add(mabel);

         HeroAB dipper= new HeroAB(HE.DIPPERPINES);
         dipper.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING,AE.FIRSTJURNAL,AE.SECONDJURNAL,AE.THIRDJURNAL));
         dipper.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD,BE.FIRSTJURNAL));

         habList.add(dipper);

         HeroAB soos= new HeroAB("Soos Ramirez");
         soos.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING,AE.MULTIBEAVERSATTACK));
         soos.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD,BE.RED,BE.DAM));
         habList.add(soos);

         HeroAB stanek= new HeroAB("Stan Pines");
         stanek.setAvaillableAbilitiesenums( Arrays.asList(AE.FIRSTSUMMON,AE.FULLCOUNTER,AE.NOTHING,AE.INCREASESHOOTING,AE.PSTEST,AE.PROGRESS));
         stanek.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(stanek);

         HeroAB wendy= new HeroAB("Wendy Corduroy");
         wendy.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING,AE.TIMEMACHINE));
         wendy.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD,BE.WENDYAXE));
         habList.add(wendy);

         HeroAB waddles= new HeroAB("Waddles");
         waddles.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING));
         waddles.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(waddles);

         HeroAB grenda= new HeroAB("Grenda");
         grenda.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING,AE.PSTEST));
         grenda.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD,BE.GRENDAARMCHAIR));
         habList.add(grenda);

         HeroAB loglandgirl= new HeroAB("Log Land Girl");
         loglandgirl.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING));
         loglandgirl.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(loglandgirl);

         HeroAB quentintrembley= new HeroAB("Quentin Trembley");
         quentintrembley.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING,AE.DINOSUMMON));
         quentintrembley.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(quentintrembley);

         HeroAB candy= new HeroAB("Candy Chiu");
         candy.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING));
         candy.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(candy);

         HeroAB oldmanmcgucket= new HeroAB("Old Man McGucket");
         oldmanmcgucket.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING));
         oldmanmcgucket.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(oldmanmcgucket);

         HeroAB mabelwithgrapplinghook= new HeroAB("Mabel With Grappling Hook");
         mabelwithgrapplinghook.setAvaillableAbilitiesenums( Arrays.asList(AE.NOTHING));
         mabelwithgrapplinghook.setAvaillableBulletsenums(Arrays.asList(BE.STANDARD));
         habList.add(mabelwithgrapplinghook);

     }
    public static boolean isEmpty()
    {
        if(habList.size()==0) return true;
        else return false;
    }

    public static void clear()
    {
        habList.clear();
    }

}
