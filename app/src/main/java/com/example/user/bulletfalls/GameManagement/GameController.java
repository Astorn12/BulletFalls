package com.example.user.bulletfalls.GameManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Objects.Ability;
import com.example.user.bulletfalls.Activities.GameResult;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Enums.Shape;
import com.example.user.bulletfalls.GameSupporters.CollisionTester;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemysChooser;
import com.example.user.bulletfalls.GameSupporters.GameStrategy;
import com.example.user.bulletfalls.GameSupporters.GroupPackage.GroupsContainer;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.EnemyShot;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.GameSummary;
import com.example.user.bulletfalls.GameSupporters.MediumTasks.Medium;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.ProfileActivity.UserProfile;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Sets.EnemySet;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.HeroSpecyfication;
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
    LinkedList<Enemy> enemies;
    LinkedList<Bullet> bulletsGarbageCollector;
    LinkedList<Enemy> enemyGravedigger;
    LinkedList<Enemy> removeAfterMutation;

    LinkedList<Hero> heroesCollection;
    //LinkedList<EnemySpecyfication> enemysCollection;
    List<Ability> abilities;
    Hero hero;
    Timer timer;
    boolean startFlag;
    int enemyFrequency;
    int enemyCounter;
    EnemysChooser enemysChooser;
    CollisionTester collisionTester;
    Medium medium;

    LinkedList<Beast> beasts;
    LinkedList<Beast> beastGarbageCollection;
    List<Bullet> tmpFurtka = new LinkedList<>();

    public GameController(Activity activity, EnemysChooser enemysChooser) {
        this.gameActivity = activity;
        this.game = (FrameLayout) gameActivity.findViewById(R.id.frame);
        enemies = new LinkedList<>();
        timer = new Timer();
        this.bullets = new LinkedList<>();
        this.enemies = enemies;

        this.bulletsGarbageCollector = new LinkedList<>();
        this.enemyGravedigger = new LinkedList<>();
        enemyFrequency = 50;
        enemyCounter = 0;
        heroesCollection = new LinkedList<>();
        //enemysCollection= new LinkedList<>();
        // loadEnemyCollection();
        this.abilities = new LinkedList<>();
        this.enemysChooser = enemysChooser;
        collisionTester = new CollisionTester();
        medium = new Medium();
        this.beasts = new LinkedList<>();
        this.beastGarbageCollection = new LinkedList<>();
        this.removeAfterMutation=new LinkedList<>();

    }

    public void start() {
        setScoreLabel(score + "");
        hero = getChoosenHero();
        hero.born();
        medium.heroBorning(new HeroSpecyfication(hero));
        abilitysLoading();
        abilityListining();
        final GameController controller = this;
        GroupsContainer groupsContainer = new GroupsContainer();
        groupsContainer.boost(hero);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Enemy ex=new EnemySet(game.getContext()).getEnemy("Shmebullock");
                Enemy en = enemysChooser.getEnemy(gameActivity);
                if(en!=null)
                bornEnemy(en);
                repaint();
            }
        }, 0, 20);
        medium.startTimer();
    }

    private void bornEnemy(Enemy en) {
        if (en != null) {
            en.setFrame(game);
            enemies.add(en);
            en.appear();
            medium.enemyBorning(new EnemySpecyfication(en));
        }
    }

    private void repaint() {
        for (Bullet b : tmpFurtka) {
            this.bullets.add(b);
        }
        tmpFurtka.clear();
        hero.startingMove(new EyeOnGame(this));

        for (Bullet bullet : bullets) {
            bullet.move(new EyeOnGame(this));
        }
        Bullet newHeroBullet = hero.startShooting();

        if (newHeroBullet != null) {
            this.bullets.add(newHeroBullet);
            medium.heroShot(new BulletSpecyfication(newHeroBullet), abilities);
        }

        for (Bullet bullet : bulletsGarbageCollector) {
            bullets.remove(bullet);
        }
        bulletsGarbageCollector.removeAll(bulletsGarbageCollector);
        for (Enemy enemy : enemies) {
            enemy.startingMove(new EyeOnGame(this));

            Bullet newEnemyBullet = enemy.startShooting();
            if (newEnemyBullet != null) {
                this.bullets.add(newEnemyBullet);
                medium.enemyShot(new EnemyShot(new EnemySpecyfication(enemy), new BulletSpecyfication(newEnemyBullet)));
            }
        }

        beastsAction();


        collisionTester.collisionChecking(hero, enemies, bullets, medium, beasts);
        heroLifeChecking();
        enenemysLifeChecking();
        bulletLifeChecking();
        enemyCleaning();
        bulletCleaning();
        beastsLifeChecking();
        beastCleaning();

        summoning();
    }

    private void beastCleaning() {
        for (Beast beast : this.beastGarbageCollection) {
            this.beasts.remove(beast);
        }
        this.beastGarbageCollection.clear();
    }

    private void beastsLifeChecking() {
        for (Beast beast : this.beasts) {
            if (!beast.isAlive()) {
                this.beastGarbageCollection.add(beast);
            }
        }
    }

    private void beastsAction() {
        for (Beast beast : this.beasts) {
            beast.startingMove(new EyeOnGame(this));
            Bullet newEnemyBullet = beast.startShooting();
            if (newEnemyBullet != null) {
                this.bullets.add(newEnemyBullet);/** tutaj jeśli chcemy żeby kulki wystrzelone przez zwierzę liczyły się w stosunku dokulek wystrzelonych przez bohatera, to musimy to ustalić*/

                // medium.enemyShot(new EnemyShot(beast.getSpecyfication(),new BulletSpecyfication(newEnemyBullet)));
            }
        }
    }

    private void summoning() {
        hero.Summon(game, this.beasts);

    }


    public void enemyCleaning() {
        for (Enemy enemy : enemyGravedigger) {
            removeEnemyFromGame(enemy);
            medium.deathOfEnemy(new EnemySpecyfication(enemy));
        }
        for(Enemy enemy: removeAfterMutation)
        {
            removeEnemyFromGame(enemy);
        }
        enemyGravedigger.removeAll(enemyGravedigger);
        removeAfterMutation.removeAll(removeAfterMutation);
    }
    private void removeEnemyFromGame(Enemy enemy)
    {
        enemies.remove(enemy);
        score += enemy.getKillValue();
        setScoreLabel(score + "");

        ((Game)gameActivity).removeLifeText(enemy.getTextLife());
        ((Game)gameActivity).removeObject(enemy);
    }

    public void bulletCleaning() {
        for (Bullet b :
                bulletsGarbageCollector) {
            bullets.remove(b);

        }
        bulletsGarbageCollector.clear();
    }

    public void heroLifeChecking() {
        if (!this.hero.isAlive()) {
            this.stop();
        }
    }

    private void enenemysLifeChecking() {
        for (Enemy enemy : enemies) {
            if (!enemy.isAlive()) {

                //this.enemys.remove(enemy);
                enemyGravedigger.add(enemy);

            }
        }
    }

    private void bulletLifeChecking() {
        for (Bullet bullet : bullets) {
            if (bullet.getPower() <= 0) {
                this.bulletsGarbageCollector.add(bullet);
                bullet.destroy();
            }
        }
    }

    private boolean sameDirectionChecking(Bullet a, Bullet b) {
        if ((a.getSpeed() > 0 & b.getSpeed() > 0) || (a.getSpeed() < 0 && b.getSpeed() < 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void stop() {
        cancelAllAbilityThreads();
        timer.cancel();
        medium.stopTime();
        //Show Result
        GameStrategy.getInstance().getBountyAssigner().fillBounty(medium, medium.getBounty());
        UserProfile userProfile = new UserProfile(gameActivity);
        userProfile.makeOfBounty(medium.getBounty());

        GameSummary.getInstance().setSummary(medium, new HeroSpecyfication(this.hero), "Nowa gra", medium.getBounty());

        Intent intent = new Intent(gameActivity, GameResult.class);
        // intent.putExtra("SCORE",score);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        gameActivity.startActivity(intent);
        gameActivity.finish();
    }

    private void cancelAllAbilityThreads() {
        for (Ability av : this.abilities) {
            av.getAbilitySpecyfication().cancelThread();
        }
    }

    public void bullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void pressON() {
        this.hero.flyUp();
    }

    public void pressOFF() {
        this.hero.comeDown();
    }

    public void removeBullet(Bullet bullet) {
        this.bullets.remove(bullet);

    }

    public void removeEnemy(Enemy enemy) {
        this.enemyGravedigger.add(enemy);
    }

    private boolean bullet2bulletColisionChecking(Bullet a, Bullet b) {
        if (a.getShape().equals(Shape.CIRCLE) && b.getShape().equals(Shape.CIRCLE)) {
            if (Math.sqrt(Math.pow(2, (a.getMiddle().x - b.getMiddle().x) + Math.pow(2, a.getMiddle().y - b.getMiddle().y))) > a.getWidth() / 2 + b.getWidth() / 2) {
                return false;
            } else return true;
        } else {
            if (Math.abs(a.getMiddle().x - b.getMiddle().x) < a.getWidth() / 2 + b.getWidth() / 2 && Math.abs(a.getMiddle().y - b.getMiddle().y) < a.getHeight() / 2 + b.getHeight() / 2) {
                return true;
            } else return false;
        }
    }

    private boolean damageToCharacterChecking(Character a, Bullet b) {
        if (Math.abs(a.getMiddle().x - b.getMiddle().x) < a.getWidth() / 2 + b.getWidth() / 2 && Math.abs(a.getMiddle().y - b.getMiddle().y) < a.getHeight() / 2 + b.getHeight() / 2) {
            return true;
        } else return false;
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("heroSpecyfication.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            //Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            //Log.e("login activity", "Can not read file: " + e.toString());
        } catch (Exception ex) {

        }

        return ret;
    }

    private Hero getChoosenHero() {
        String s = readFromFile(gameActivity);
        if (s != "") {

            ObjectMapper mapper = new ObjectMapper();
            try {
                HeroSpecyfication heroSpecyficationSpecyfication = mapper.readValue(s, HeroSpecyfication.class);
                Hero bohater = new Hero(gameActivity, heroSpecyficationSpecyfication);
                heroEking(bohater);
                bulletEking(bohater.getBullet());
                return bohater;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

    private void setScoreLabel(String text) {
        ((Game) gameActivity).setScoreLabel(text + "");
    }

    private void heroEking(Hero hero) {
        hero.setFrame(game);
        hero.setStartingPoint(hero.getPosition());
        bulletEking(hero.getBullet());

    }

    private void bulletEking(Bullet bullet) {
        bullet.setFrame(game);
    }

    private static class IgnoreInheritedIntrospector extends JacksonAnnotationIntrospector {
        @Override
        public boolean hasIgnoreMarker(final AnnotatedMember m) {
            return m.getDeclaringClass() == android.support.v7.widget.AppCompatImageView.class || m.getDeclaringClass().isAssignableFrom(android.support.v7.widget.AppCompatImageView.class) || m.getDeclaringClass() == Drawable.class || super.hasIgnoreMarker(m);
        }
    }

    private void abilitysLoading() {

        abilities.removeAll(abilities);
        this.abilities.add(new Ability(this.gameActivity, hero.getAbilities().getFirstAbility()));
        this.abilities.add(new Ability(this.gameActivity, hero.getAbilities().getSecondAbility()));
        this.abilities.add(new Ability(this.gameActivity, hero.getAbilities().getThirdAbility()));
        updateAbilitiesBar();
    }

    private void updateAbilitiesBar() {
        ((Game) gameActivity).setAbilitiesBar(abilities);
    }

    public void abilityListining() {
        this.abilities.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getAbilities().getFirstAbility().isReady() && hero.getAbilities().getFirstAbility().isActive()) {
                    hero.getFirstAbility().doToCharacter(hero);
                    medium.abilityUse(hero.getSecondAbility());
                }
            }
        });
        this.abilities.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getAbilities().getSecondAbility().isReady() && hero.getAbilities().getSecondAbility().isActive()) {
                    hero.getSecondAbility().doToCharacter(hero);
                    medium.abilityUse(hero.getSecondAbility());
                }
            }
        });
        this.abilities.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getAbilities().getThirdAbility().isReady() && hero.getAbilities().getThirdAbility().isActive()) {
                    hero.getThirdAbility().doToCharacter(hero);
                    medium.abilityUse(hero.getThirdAbility());
                }
            }
        });
    }


    //metody sekretne ;) furtki

    public void addBullet(Bullet bullet) {
        //this.bullets.add(bullet);
        tmpFurtka.add(bullet);
        medium.heroShot(new BulletSpecyfication(bullet), abilities);
    }



    public Hero getHero() {
        return hero;
    }

    public Medium getMedium() {
        return this.medium;
    }
    /*public List<EnemySpecyfication> getReadOnlyCurrentlyEnemyList()//to jest fajny bajer no ale chyba działało by to zbyt długo, no i w niektórych przypadkach nie wystarczające
                                                            //bo np. boostowanie innych enemys
    {
        return null;
    }*/

    public List<EnemySpecyfication> getReadOnlyCurrentEnemyList() {
            List<EnemySpecyfication> ret= new LinkedList<>();
        for(Enemy e: this.enemies)
            {
                ret.add(new EnemySpecyfication(e));
            }
            return ret;
    }

    public void mutate(List<EnemySpecyfication> toMutate, List<EnemySpecyfication> mutationOutcome)
    {
        for(EnemySpecyfication es: toMutate)
        {
            for(Enemy e: this.enemies)
            {
                if(es.getName().equals(e.getName()))
                {
                    if(!removeAfterMutation.contains(e))
                    {this.removeAfterMutation.add(e);
                    break;}
                }
            }
        }
        for(EnemySpecyfication es: mutationOutcome)
        {
            bornEnemy(new Enemy(game.getContext(),es));
        }
    }

    public List<Enemy> getEnemyList()
    {
        return this.enemies;
    }



}
