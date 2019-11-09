package com.example.user.bulletfalls.Storage.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.CharacterAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BeastPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.GlobalUsage.Enums.EBeastType;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.R;

import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.GlobalUsage.Supporters.FileSupporter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BeastsSet implements ISet<BeastSpecyfication> {

    private final static BeastsSet ourInstance=new BeastsSet();
    private List<BeastSpecyfication> beasts=new LinkedList<>();
    final private String path="beasts.json";

    private BeastsSet(){
    }


    public static BeastsSet getInstance()
    {
        return ourInstance;
    }


    @Override
    public void load(Context context) {
        String s=FileSupporter.LoadStrinFromFile(path,context);
        ObjectMapper mapper = new ObjectMapper();

        CollectionType t=mapper.getTypeFactory()
                .constructCollectionType(List.class, BeastSpecyfication.class);
        try {
            beasts =mapper.readerFor(t).readValue(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Context context) {
        ObjectMapper mapper= new ObjectMapper();
        //mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String s ="";
        try {
            CollectionType t=mapper.getTypeFactory()
                    .constructCollectionType(List.class, BeastSpecyfication.class);
            s= mapper.writerFor(t).writeValueAsString(beasts);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        FileSupporter.WriteToFile(path,context,s);
    }

    @Override
    public List<BeastSpecyfication> getAll() {

        List<BeastSpecyfication> clone= new LinkedList<>();

        for(BeastSpecyfication b: this.beasts)
        {
            clone.add(b);
        }
        return clone;
    }

    @Override
    public void clear() {
       this.beasts.clear();
    }


    public void AddToDatabaseTest(Context context) {
        AppearAction aa= new NothingAppearAction();
        AttackDefenceFilter adf= new AttackDefenceFilter();


        BeastSpecyfication gompers= new BeastSpecyfication("Gompers",
                new CharacterVS(R.drawable.goat,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("gompers",Arrays.asList(FamilyName.MysteryShack),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication dino1= new BeastSpecyfication("dino1",
                new CharacterVS(R.drawable.diplodok,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("dino",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication dino2= new BeastSpecyfication("dino2",
                new CharacterVS(R.drawable.raptor,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("dino",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication dino3= new BeastSpecyfication("dino2",
                new CharacterVS(R.drawable.trex,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("dino",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication dino4= new BeastSpecyfication("dino4",
                new CharacterVS(R.drawable.stegozaur,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("dino",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication beaver= new BeastSpecyfication("Beaver",
                new CharacterVS(R.drawable.beaver,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("beaver",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication weaddledoctor= new BeastSpecyfication("Waddles Doctor",
                new CharacterVS(R.drawable.weadledoctor,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("weaddle",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication waddlebeast= new BeastSpecyfication("Waddles Beast",
                new CharacterVS(R.drawable.weadlebeast,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("weaddle",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        BeastSpecyfication unicorn= new BeastSpecyfication("Unicorn",
                new CharacterVS(R.drawable.unicorn,1,new Description()),
                new BeastPS(20,40,10,BulletSet.getInstance().getBullet(BE.RED),EBeastType.HERO),
                new CharacterAS(CharacterPositioning.HEROSUMMONEDBEAST,adf,aa),
                new CharacterCS("unicorn",Arrays.asList(),Arrays.asList(Kind.MONSTER)));

        this.beasts.add(gompers);
        this.beasts.add(dino1);
        this.beasts.add(dino2);
        this.beasts.add(dino3);
        this.beasts.add(dino4);
        this.beasts.add(beaver);
        this.beasts.add(waddlebeast);
        this.beasts.add(weaddledoctor);
        this.beasts.add(unicorn);
       }

    public BeastSpecyfication getByName(String name)
    {
        for(BeastSpecyfication b :this.beasts)
        {
            if(b.getName().equals(name)) return b;
        }
        return null;
    }

    public boolean isEmpty()
    {
        if(beasts.size()==0) return true;
        else return false;
    }

    public List<BeastSpecyfication> getChosen(String... parameters) {
        List<BeastSpecyfication> ret = new LinkedList<>();
        for (String s : parameters) {
            for (BeastSpecyfication b : beasts) {
                if (s.equals(b.getName())) ret.add(b);
            }
        }
        return ret;
    }

}
