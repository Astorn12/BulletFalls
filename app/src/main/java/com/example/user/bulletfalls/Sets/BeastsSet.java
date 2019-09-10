package com.example.user.bulletfalls.Sets;

import android.content.Context;
import android.renderscript.Script;

import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.EBeastType;
import com.example.user.bulletfalls.Enums.GroupName;
import com.example.user.bulletfalls.Enums.Kind;
import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Supporters.FileSupporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import org.apache.commons.lang3.NotImplementedException;

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
    public List<BeastSpecyfication> getAll(Context context) {

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
       BeastSpecyfication gompers= new BeastSpecyfication("Gompers",R.drawable.goat,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);

        BeastSpecyfication dino1= new BeastSpecyfication("dino1",R.drawable.diplodok,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);

        BeastSpecyfication dino2= new BeastSpecyfication("dino2",R.drawable.raptor,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);

        BeastSpecyfication dino3= new BeastSpecyfication("dino3",R.drawable.trex,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);

        BeastSpecyfication dino4= new BeastSpecyfication("dino4",R.drawable.stegozaur,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);
        BeastSpecyfication beaver= new BeastSpecyfication("Beaver",R.drawable.beaver,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);
        BeastSpecyfication weaddledoctor= new BeastSpecyfication("Waddles Doctor",R.drawable.weadledoctor,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);
        BeastSpecyfication waddlebeast= new BeastSpecyfication("Waddles Beast",R.drawable.weadlebeast,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);
        BeastSpecyfication unicorn= new BeastSpecyfication("Unicorn",R.drawable.unicorn,10,10,200,40,120,2,new Resistance(5,10),
                BulletSet.getBullet(BE.RED).getSpecyfication(),Kind.MONSTER,Arrays.asList(GroupName.Animal),CharacterPositioning.HEROSUMMONEDBEAST,
                new NoneDoToBulletStrategy(),"",new Description(),R.drawable.goat,aa,EBeastType.HERO);

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
