package com.example.user.bulletfalls.Storage.Sets;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.EnemyAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.EnemyPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.GnomeAppearAction;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.NothingAppearAction;
import com.example.user.bulletfalls.Game.Elements.Items.DropStrategy.Drop;
import com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage.UpDownMove;

import org.apache.commons.lang3.NotImplementedException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EnemySet implements ISet<EnemySpecyfication>{

    private final static EnemySet ourInstance=new EnemySet();
    public  List<EnemySpecyfication> enemys = new LinkedList<>();

    private EnemySet()
    {

    }
    public static EnemySet getInstance()
    {
        return ourInstance;
    }




    public void load(Context context)
    {
        AttackDefenceFilter adf= new AttackDefenceFilter();
        AppearAction aa= new NothingAppearAction();


        Drop sDrop= new Drop(context,1,0.8);

        AppearAction gnomePowerAction= new GnomeAppearAction();



        EnemySpecyfication gideon= new EnemySpecyfication("Gideon",
                new CharacterVS(R.drawable.gideon,0.9f,new Description()),
                new EnemyPS(20,70,30,BulletSet.getInstance().getBullet(BE.STANDARD),10),
                new EnemyAS(CharacterPositioning.RIGHTRANDOM,adf,aa,new UpDownMove(),sDrop),
                new CharacterCS("gideon",Arrays.asList(FamilyName.TentOfThelepathy),Arrays.asList(Kind.CHILD,Kind.HUMAN)));

        EnemySpecyfication gnome2= new EnemySpecyfication("Random Gnome",
                new CharacterVS(R.drawable.gnome2,0.9f,new Description()),
                new EnemyPS(20,70,30,BulletSet.getInstance().getBullet(BE.STANDARD),10),
                new EnemyAS(CharacterPositioning.RIGHTRANDOM,adf,gnomePowerAction,new UpDownMove(),sDrop),
                new CharacterCS("gnome",Arrays.asList(FamilyName.Gnomes),Arrays.asList(Kind.MONSTER)));

        EnemySpecyfication jeff= new EnemySpecyfication("Jeff",
                new CharacterVS(R.drawable.jeff,0.9f,new Description()),
                new EnemyPS(20,70,30,BulletSet.getInstance().getBullet(BE.STANDARD),10),
                new EnemyAS(CharacterPositioning.RIGHTRANDOM,adf,gnomePowerAction,new UpDownMove(),sDrop),
                new CharacterCS("gnome",Arrays.asList(FamilyName.Gnomes),Arrays.asList(Kind.MONSTER)));

        EnemySpecyfication shmebulock= new EnemySpecyfication("Shmebulock",
                new CharacterVS(R.drawable.shmebulock,0.9f,new Description()),
                new EnemyPS(20,70,30,BulletSet.getInstance().getBullet(BE.STANDARD),10),
                new EnemyAS(CharacterPositioning.RIGHTRANDOM,adf,gnomePowerAction,new UpDownMove(),sDrop),
                new CharacterCS("gnome",Arrays.asList(FamilyName.Gnomes),Arrays.asList(Kind.MONSTER)));

        EnemySpecyfication gnomeMonster= new EnemySpecyfication("Gnome Monster",
                new CharacterVS(R.drawable.gnomemonster,0.9f,new Description()),
                new EnemyPS(20,250,30,BulletSet.getInstance().getBullet(BE.STANDARD),10),
                new EnemyAS(CharacterPositioning.RIGHTRANDOM,adf,gnomePowerAction,new UpDownMove(),sDrop),
                new CharacterCS("gnome",Arrays.asList(FamilyName.Gnomes),Arrays.asList(Kind.MONSTER)));






        enemys.add(gideon);
        enemys.add(gnome2);
        enemys.add(jeff);
        enemys.add(shmebulock);
        enemys.add(gnomeMonster);
    }

    @Override
    public void save(Context context) {
        throw new NotImplementedException("Enemy set nie ma logicznego zapisywania");
    }

    public EnemySpecyfication getEnemy(String name)
    {

        for(EnemySpecyfication es: enemys)
        {
            if(es.getName().equals(name))
            {
                return es;
            }
        }
        return null;
    }

    public List<EnemySpecyfication> getEnemys(String... params)
    {
        List<EnemySpecyfication> enemys= new LinkedList<>();
        for(String param : params)
        {
            enemys.add(getEnemy(param));
        }
        return enemys;
    }

    public List<EnemySpecyfication> getAll(Context context)
    {
        return this.enemys;
    }

    @Override
    public void clear() {
        this.enemys.clear();
    }

    public List<EnemySpecyfication> getByGroup(FamilyName familyName)
    {
        List<EnemySpecyfication> group= new LinkedList<>();
        for(EnemySpecyfication es: enemys)
        {
            if(es.isFromGroup(familyName))
            group.add(es);
        }
        return group;
    }
    public List<EnemySpecyfication> convertToEnemySpecyficationList(List<Enemy> enemies)
    {
        List<EnemySpecyfication> enemySpecyficationSpecyfications = new LinkedList<>();
        for(Enemy e: enemies) {
            enemySpecyficationSpecyfications.add(e.getSpecyfication());
        }
        return enemySpecyficationSpecyfications;
    }

}

