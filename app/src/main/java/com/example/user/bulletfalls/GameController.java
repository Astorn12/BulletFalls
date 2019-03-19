package com.example.user.bulletfalls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Activities.GameResult;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameSupporters.CollisionTester;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemysChooser;
import com.example.user.bulletfalls.GameSupporters.GameStrategy;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.EnemyShot;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.GameSummary;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.HeroSpecyfication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    int score;
    Activity gameActivity;
    FrameLayout game;
    LinkedList<Bullet> bullets;
    LinkedList<Enemy> enemys;
    LinkedList<Bullet> bulletsGarbageCollector;
    LinkedList<Enemy> enemyGravedigger;
    LinkedList<Hero> heroesCollection;
    //LinkedList<Enemy> enemysCollection;
    List<AbilityView> abilities;
    Hero hero;
    Timer timer;
    boolean startFlag;
    int enemyFrequency;
    int enemyCounter;
    EnemysChooser enemysChooser;
    CollisionTester collisionTester;
    Medium medium;

    public GameController(Activity activity, EnemysChooser enemysChooser)
    {
        this.gameActivity=activity;
        this.game=(FrameLayout) gameActivity.findViewById(R.id.frame);
        enemys= new LinkedList<>();
        timer= new Timer();
        this.bullets= new LinkedList<>();
        this.enemys= enemys;

        this.bulletsGarbageCollector= new LinkedList<>();
        this.enemyGravedigger= new LinkedList<>();
        enemyFrequency=50;
        enemyCounter=0;
        heroesCollection= new LinkedList<>();
        //enemysCollection= new LinkedList<>();
       // loadEnemyCollection();
        this.abilities= new LinkedList<>();
        this.enemysChooser=enemysChooser;
        collisionTester= new CollisionTester(hero,enemys,bullets);
        medium= new Medium();
    }

    public void start()
    {
       setScoreLabel(score+"");
       hero=getChoosenHero();
       hero.born();
       medium.heroBorning(new HeroSpecyfication(hero));
       abilitysLoading();
       abilityListining();
       final GameController controller= this;
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               /*enemyCounter+=1;
               if(enemyCounter%200==0)
               {
                   Enemy enemy1=enemysCollection.get(new Random().nextInt(enemysCollection.size()));
                   Enemy en=enemy1.clone();
               }*/
               Enemy en= enemysChooser.getEnemy(gameActivity);
               if(en!=null) {
               en.setFrame(game);
                   enemys.add(en);
                   en.appear();
                   medium.enemyBorning(new EnemySpecyfication(en));
               }

               repaint();
           }
       },0,20);
       medium.startTimer();
    }
    private void repaint()
    {
        for(Bullet b:tmpFurtka)
        {
            this.bullets.add(b);
        }
        tmpFurtka.clear();
        hero.startingMove();

       // System.out.println(heroBullets.size());
        for(Bullet bullet:bullets)
        {
            bullet.move();
        }
        Bullet newHeroBullet= hero.startShooting();

        if(newHeroBullet!=null)
        {
            this.bullets.add(newHeroBullet);
            medium.heroShot(new BulletSpecyfication(newHeroBullet));
            //newHeroBullet.appear();
        }

        for(Bullet bullet:bulletsGarbageCollector)
        {
            bullets.remove(bullet);
        }
        bulletsGarbageCollector.removeAll(bulletsGarbageCollector);
        for(Enemy enemy: enemys)
        {
            enemy.startingMove();
            Bullet newEnemyBullet= enemy.startShooting();
            if(newEnemyBullet!= null)
            {
                this.bullets.add(newEnemyBullet);
                medium.enemyShot(new EnemyShot(new EnemySpecyfication(enemy),new BulletSpecyfication(newEnemyBullet)));
            }
        }
        collisionTester.collisionChecking(hero,enemys,bullets,medium);
        heroLifeChecking();
        enenemysLifeChecking();
        bulletLifeChecking();
        enemyCleaning();
        bulletCleaning();
    }
    public void enemyCleaning()
    {
        for(Enemy enemy:enemyGravedigger)
        {
            enemys.remove(enemy);
            score+=enemy.killValue;
            setScoreLabel(score+"");
            medium.deathOfEnemy(new EnemySpecyfication(enemy));
        }
        enemyGravedigger.removeAll(enemyGravedigger);
    }

    public void bulletCleaning()
    {
        for (Bullet b:
             bulletsGarbageCollector) {
            bullets.remove(b);

        }
    }
    public void heroLifeChecking()
    {
        if(!this.hero.isAlive())
        {
            this.stop();
        }
    }
    private void enenemysLifeChecking()
    {
        for(Enemy enemy:enemys)
        {
            if(!enemy.isAlive()){

                //this.enemys.remove(enemy);
                enemyGravedigger.add(enemy);

            }
        }
    }
    private void bulletLifeChecking()
    {
        for(Bullet bullet:bullets)
        {
            if(bullet.getPower()<=0)
            {
                this.bulletsGarbageCollector.add(bullet);
                bullet.destroy();
            }
        }
        /*for(Bullet bullet:enemyBullets)
        {
            if(bullet.getPower()<=0)
            {
                this.enemyBulletsGarbageCollector.add(bullet);
                bullet.destroy();
            }
        }*/
    }
    private boolean sameDirectionChecking(Bullet a,Bullet b)
    {
        if((a.getSpeed()>0 &b.getSpeed()>0)||(a.getSpeed()<0&&b.getSpeed()<0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void stop()
    {
        timer.cancel();
        medium.stopTime();
        //Show Result
        GameStrategy.getInstance().getBountyAssigner().fillBounty(medium,medium.getBounty());
        UserProfile userProfile= new UserProfile(gameActivity);
        userProfile.makeOfBounty(medium.getBounty());

        GameSummary.getInstance().setSummary(medium,new HeroSpecyfication(this.hero),"Nowa gra",medium.getBounty());

        Intent intent= new Intent(gameActivity,GameResult.class);
       // intent.putExtra("SCORE",score);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        gameActivity.startActivity(intent);
        gameActivity.finish();

    }
    public void bullet(Bullet bullet)
    {
     this.bullets.add(bullet);
    }
    public void pressON()
    {
        this.hero.flyUp();
    }
    public void pressOFF()
    {
        this.hero.comeDown();
    }
    public void removeBullet(Bullet bullet)
    {
        this.bullets.remove(bullet);

    }
    public void removeEnemy(Enemy enemy)
    {
        this.enemyGravedigger.add(enemy);
    }
    private boolean bullet2bulletColisionChecking(Bullet a,Bullet b)
    { if(a.getShape().equals(Shape.CIRCLE)&&b.getShape().equals(Shape.CIRCLE)) {
        if (Math.sqrt(Math.pow(2, (a.getMiddle().x - b.getMiddle().x) + Math.pow(2, a.getMiddle().y - b.getMiddle().y))) > a.getWidth() / 2 + b.getWidth() / 2) {
            return false;
        } else return true;
    }
    else
    {
        if(Math.abs(a.getMiddle().x-b.getMiddle().x)<a.getWidth()/2+b.getWidth()/2&&Math.abs(a.getMiddle().y-b.getMiddle().y)<a.getHeight()/2+b.getHeight()/2)
        {
            return true;
        }
        else return false;
    }
    }
    private boolean damageToCharacterChecking(Character a,Bullet b)
    {
       if(Math.abs(a.getMiddle().x-b.getMiddle().x)<a.getWidth()/2+b.getWidth()/2&&Math.abs(a.getMiddle().y-b.getMiddle().y)<a.getHeight()/2+b.getHeight()/2)
        {
            return true;
        }
        else return false;
    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("hero.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            //Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            //Log.e("login activity", "Can not read file: " + e.toString());
        }
        catch(Exception ex)
        {

        }

        return ret;
    }
    private Hero getChoosenHero()
    {
        String s=readFromFile(gameActivity);
        if(s!="") {

            ObjectMapper mapper = new ObjectMapper();
            try {
                HeroSpecyfication heroSpecyfication=mapper.readValue(s,HeroSpecyfication.class);
                Hero bohater=new Hero(gameActivity,heroSpecyfication);
                heroEking(bohater);
                bulletEking(bohater.getBullet());
                return bohater;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else return null;
    }
    private void setScoreLabel(String text)
    {
        ( (Game) gameActivity).setScoreLabel(text+"");
    }

   private void heroEking(Hero hero)
    {
        hero.setFrame(game);
        hero.setStartingPoint(hero.getPosition());
        bulletEking(hero.getBullet());

    }
    private void bulletEking(Bullet bullet)
    {
        bullet.setFrame(game);
    }
    private static class IgnoreInheritedIntrospector extends JacksonAnnotationIntrospector {
        @Override
        public boolean hasIgnoreMarker(final AnnotatedMember m) {
            return m.getDeclaringClass() == android.support.v7.widget.AppCompatImageView.class || m.getDeclaringClass().isAssignableFrom(android.support.v7.widget.AppCompatImageView.class)||  m.getDeclaringClass() == Drawable.class||super.hasIgnoreMarker(m);
        }
    }
    private void abilitysLoading()
    {

        abilities.removeAll(abilities);
        this.abilities.add(new AbilityView(this.gameActivity,hero.getAbilities().getFirstAbility()));
        this.abilities.add(new AbilityView(this.gameActivity,hero.getAbilities().getSecondAbility()));
        this.abilities.add(new AbilityView(this.gameActivity,hero.getAbilities().getThirdAbility()));
        updateAbilitiesBar();
    }
    private void updateAbilitiesBar()
    {
        ((Game)gameActivity).setAbilitiesBar(abilities);
    }
    public void abilityListining()
    {
        this.abilities.get(0).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(hero.getAbilities().getFirstAbility().isReady())
            {
                hero.getFirstAbility().doToCharacter(hero);
                System.out.println("Pierwsza abilitka");
                medium.abilityUse(hero.getSecondAbility());
            }
        }
    });
        this.abilities.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hero.getAbilities().getSecondAbility().isReady())
                {
                    hero.getSecondAbility().doToCharacter(hero);
                    System.out.println("Druga abilitka");
                    medium.abilityUse(hero.getSecondAbility());
                }
            }
        });
        this.abilities.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hero.getAbilities().getThirdAbility().isReady())
                {
                    hero.getThirdAbility().doToCharacter(hero);
                    System.out.println("Trzecia abilitka");
                    medium.abilityUse(hero.getThirdAbility());
                }
            }
        });
    }



    //metody sekretne ;) furtki

    public void addBullet(Bullet bullet)
    {
        //this.bullets.add(bullet);
        tmpFurtka.add(bullet);
    }

    List<Bullet> tmpFurtka= new LinkedList<>();


}