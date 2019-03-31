package com.example.user.bulletfalls.Database.JsonDatabases;

import android.content.Context;

import com.example.user.bulletfalls.KlasyPomocnicze.FileSupporter;
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






    /*-----------------------------Load & Save---------------------------*/
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
        HeroAB mabel= new HeroAB("Mabel Pines");
        mabel.setAvaillableAbilities( Arrays.asList("carpediem","summonlog","ability","armchairthrow"));
        mabel.setAvaillableBullets(Arrays.asList("standard","grendaArmchair","dam","timedam","log","red","disarm"));
        habList.add(mabel);

         HeroAB dipper= new HeroAB("Dipper Pines");
         dipper.setAvaillableAbilities( Arrays.asList("nothing","firstjurnal","secondjurnal","thirdjurnal"));
         dipper.setAvaillableBullets(Arrays.asList("standard","firstjurnal"));

         habList.add(dipper);

         HeroAB soos= new HeroAB("Soos Ramirez");
         soos.setAvaillableAbilities( Arrays.asList("nothing"));
         soos.setAvaillableBullets(Arrays.asList("standard","dam","red"));
         habList.add(soos);

         HeroAB stanek= new HeroAB("Stan Pines");
         stanek.setAvaillableAbilities( Arrays.asList("nothing"));
         stanek.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(stanek);

         HeroAB wendy= new HeroAB("Wendy Corduroy");
         wendy.setAvaillableAbilities( Arrays.asList("nothing"));
         wendy.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(wendy);

         HeroAB waddles= new HeroAB("Waddles");
         waddles.setAvaillableAbilities( Arrays.asList("nothing"));
         waddles.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(waddles);

         HeroAB grenda= new HeroAB("Grenda");
         grenda.setAvaillableAbilities( Arrays.asList("nothing"));
         grenda.setAvaillableBullets(Arrays.asList("standard","grendaArmchair"));
         habList.add(grenda);

         HeroAB loglandgirl= new HeroAB("Log Land Girl");
         loglandgirl.setAvaillableAbilities( Arrays.asList("nothing"));
         loglandgirl.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(loglandgirl);

         HeroAB quentintrembley= new HeroAB("Quentin Trembley");
         quentintrembley.setAvaillableAbilities( Arrays.asList("nothing"));
         quentintrembley.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(quentintrembley);

         HeroAB candy= new HeroAB("Candy Chiu");
         candy.setAvaillableAbilities( Arrays.asList("nothing"));
         candy.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(candy);

         HeroAB oldmanmcgucket= new HeroAB("Old Man McGucket");
         oldmanmcgucket.setAvaillableAbilities( Arrays.asList("nothing"));
         oldmanmcgucket.setAvaillableBullets(Arrays.asList("standard"));
         habList.add(oldmanmcgucket);

         HeroAB mabelwithgrapplinghook= new HeroAB("Mabel With Grappling Hook");
         mabelwithgrapplinghook.setAvaillableAbilities( Arrays.asList("nothing"));
         mabelwithgrapplinghook.setAvaillableBullets(Arrays.asList("standard"));
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
