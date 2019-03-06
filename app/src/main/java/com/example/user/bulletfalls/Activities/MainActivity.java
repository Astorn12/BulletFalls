
package com.example.user.bulletfalls.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Bullet;
import com.example.user.bulletfalls.Database.DAO.ProfileDao;
import com.example.user.bulletfalls.Database.DatabaseAdministrator;
import com.example.user.bulletfalls.Description;
import com.example.user.bulletfalls.Enums.CharacterPositioning;
import com.example.user.bulletfalls.Enums.Permission;
import com.example.user.bulletfalls.Enums.Rarity;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage.RandomEnemyReleaseStrategy;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemysChooser;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.TimeReleaseStrategyPackage.LinearTimeReleaseStrategy;
import com.example.user.bulletfalls.GameSupporters.GameStrategy;
import com.example.user.bulletfalls.JsonDatabases.AbilitySet;
import com.example.user.bulletfalls.JsonDatabases.BulletSet;
import com.example.user.bulletfalls.JsonDatabases.HeroAbilityBulletMapper;
import com.example.user.bulletfalls.JsonDatabases.HeroesSet;
import com.example.user.bulletfalls.Enemy;
import com.example.user.bulletfalls.Game;
import com.example.user.bulletfalls.GameController;
import com.example.user.bulletfalls.Hero;
import com.example.user.bulletfalls.P2PPackage.P2PConnection;
import com.example.user.bulletfalls.P2PPackage.P2PGame;
import com.example.user.bulletfalls.ProfileActivity.ProfileScreen;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.ShopPackage.Shop;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;
import com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage.NothingDoToCharacter;
import com.example.user.bulletfalls.Strategies.Bullet.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.Standard;
import com.example.user.bulletfalls.Strategies.Character.Character.PossesStrategyPackage.MoneyPossesStrategy;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView scoreLabel;

    FrameLayout frame;
    Hero hero;
    LinkedList<Enemy> enemys;

    private GameController controller;

    private final int MEMORY_ACCESS=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){}
        else {
         /*   BulletSet.Load(this);//to powinno się znaleźć później tutaj normalnie
            AbilitySet.Load(this);
            HeroesSet.Load(this);
            HeroAbilityBulletMapper.Load(this);
*/


            if (BulletSet.isEmpty()) {
                BulletSet.AddToDatabaseTest(this);
                BulletSet.Save(this);
                BulletSet.clear();
                BulletSet.Load(this);//to powinno się znaleźć później tutaj normalnie
            }
            if (AbilitySet.getInstance().isEmpty()) {
                AbilitySet.getInstance().AddToDatabaseTest();
                AbilitySet.getInstance().Save(this);
                AbilitySet.getInstance().clear();
                AbilitySet.getInstance().Load(this);
            }
            if (HeroesSet.isEmpty()) {


                HeroesSet.AddToDatabaseTest(this);
                HeroesSet.Save(this);
                HeroesSet.clear();
                HeroesSet.Load(this);
            }

            if (HeroAbilityBulletMapper.isEmpty()) {
                HeroAbilityBulletMapper.AddToDatabaseTest(this);
                HeroAbilityBulletMapper.Save(this);
                HeroAbilityBulletMapper.clear();
                HeroAbilityBulletMapper.Load(this);
            }
        }

        //ładowanie bazy danych SQLite
        ProfileDao profileDao= new ProfileDao(this);
        //UserProfile up=profileDao.getById(0);
      //  up.setName("Oskar");
       // up.setResource(R.drawable.cartoonmy);
      //  profileDao.update(up);

      //  CurrencyDao cd= new CurrencyDao(this);
      //  Currency mysteryCoin= new Currency("Mystery Coin",R.drawable.mysterycoin);
      //  Currency conifer= new Currency("Conifer symbol",R.drawable.dippercaptree);
      //  cd.save(mysteryCoin);
      //  cd.save(conifer);

      //  StockDao sd= new StockDao(this);
       // sd.save(new MutablePair<Currency, Integer>(mysteryCoin,10));
       // sd.save(new MutablePair<Currency, Integer>(conifer,20));
        DatabaseAdministrator da=new DatabaseAdministrator(this);
        da.actualization(this);

        //UserProfile userProfile= new ProfileDao(this).getById(1);
        //userProfile.pay(userProfile.getStock().get(0).left,10);
        //userProfile.pay(userProfile.getStock().get(0).left,1000);
        //userProfile.earn(userProfile.getStock().get(1).left,100);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case MEMORY_ACCESS:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {

                }
                else
                {

                }
        }
    }

    {

    }

    public void startGame(View view)
    {
        GameStrategy.getInstance().setStrategies(new EnemysChooser(new RandomEnemyReleaseStrategy(convertToSpecyfication(getDefalultEnemyList())),new LinearTimeReleaseStrategy(200)),R.drawable.fightbackground);
        Intent intent = new Intent(this, Game.class);
        this.startActivity(intent);

    }

    private List<EnemySpecyfication> convertToSpecyfication(List<Enemy> enemys)
    {
        List<EnemySpecyfication> enemySpecyfications= new LinkedList<>();
        for(Enemy enemy: enemys)
        {
            enemySpecyfications.add(new EnemySpecyfication(enemy));
        }

        return enemySpecyfications;
    }
    private List<Enemy> getDefalultEnemyList()
    {
        FrameLayout game=(FrameLayout) this.findViewById(R.id.frame);
        Enemy enemy1= new Enemy(this,10,20,new Point(0,0),200,200,20,R.drawable.enemy,game,100,20,1,0,10,null,"enemy",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy enemy2= new Enemy(this,10,30,new Point(0,0),200,200,20,R.drawable.rinor,game,200,20,1,0,10,null,"Rinor",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy enemy3= new Enemy(this,10,20,new Point(0,0),200,200,20,R.drawable.creature,game,30,20,1,0,10,null,"Creature",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy gideon= new Enemy(this,10,30,new Point(0,0),200,200,20,R.drawable.gideon,game,45,20,1,0,10,null,"Gideon",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy gnome2= new Enemy(this,30,10,new Point(0,0),200,200,20,R.drawable.gnome2,game,40,20,1,0,10,null,"Gnome2",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy goblin= new Enemy(this,10,10,new Point(0,0),200,200,20,R.drawable.goblin,game,100,20,1,0,10,null,"Goblin",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy pacific= new Enemy(this,10,100,new Point(0,0),200,200,20,R.drawable.pacific,game,100,20,1,0,10,null,"Pacific",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());
        Enemy police1= new Enemy(this,20,2,new Point(0,0),200,200,20,R.drawable.police1,game,500,20,1,0,10,null,"Police1",null,null,CharacterPositioning.RIGHTRANDOM,new Standard(),"żaden",new Description());

        List<Enemy> enemysCollection= new LinkedList<>();

        enemysCollection.add(enemy1);
        enemysCollection.add(enemy2);
        enemysCollection.add(enemy3);
        enemysCollection.add(gideon);
        enemysCollection.add(gnome2);
        enemysCollection.add(goblin);
        enemysCollection.add(pacific);
        enemysCollection.add(police1);
        for(Enemy enemy:enemysCollection)
        {
            enemy.setBullet(new Bullet("defaultenemybullet",this,10,20,null,50,50,20,R.drawable.blue,null,false,new Horizontal(),Shape.CIRCLE,new NothingDoToCharacter(),Permission.YES,Rarity.COMMON,new MoneyPossesStrategy("Mystery Coin",10)).clone());
            enemy.getBullet().setFrame(game);
        }
        return  enemysCollection;
    }




    public void chooseHero(View view) {
        Intent intent = new Intent(this, Heroes.class);



        this.startActivity(intent);
    }

    public void P2PGame(View view) {
        Intent intent= new Intent(this,P2PConnection.class);
        this.startActivity(intent);

    }

    public void showProfile(View view) {
        Intent intent= new Intent(this,ProfileScreen.class);
        this.startActivity(intent);
    }



    public void ToGame(View view) {
        Intent intent= new Intent(this,P2PGame.class);
        this.startActivity(intent);
    }


    public void mysteryMap(View view) {
        Intent intent= new Intent(this,MysterymMap.class);
        this.startActivity(intent);
    }

    public void shoppingtime(View view) {
        Intent intent= new Intent(this,Shop.class);
        this.startActivity(intent);
    }
}
