package com.example.user.bulletfalls.Game.Management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionMedium;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.GlobalUsage.Enums.Permission;
import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Items.ItemsController;
import com.example.user.bulletfalls.Game.Strategies.GameSketch;
import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Activities.GameResult;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.FamiliesContainer;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Items.Item;
import com.example.user.bulletfalls.Game.Elements.Bullet.RotateBullet;
import com.example.user.bulletfalls.Profile.UserProfile;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Storage.Sets.GameSet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage.Horizontal;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.MoneyPossesStrategy;
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

    ItemsController itemsController;
    List<Ability> abilities;
    Hero hero;
    Timer timer;
    boolean startFlag;
    int enemyFrequency;
    int enemyCounter;

    CollisionTester collisionTester;
    Medium medium;

    LinkedList<Beast> beasts;
    LinkedList<Beast> beastGarbageCollection;
    List<Bullet> tmpFurtka = new LinkedList<>();


    GameSketch gameSketch;
    ActionMedium actionMedium;
    EyeOnGame eyeOnGame;

    public GameController(Activity activity,String gameSketchName) {
        this.eyeOnGame= new EyeOnGame(this);
        this.gameSketch=GameSet.getSpecyficGame(gameSketchName);

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

        collisionTester = new CollisionTester();
        medium = new Medium();
        this.beasts = new LinkedList<>();
        this.beastGarbageCollection = new LinkedList<>();
        this.removeAfterMutation=new LinkedList<>();
        this.itemsController= new ItemsController(this.game);
        hero = getChoosenHero();
        actionMedium= new ActionMedium(new EyeOnGame(this));
        collectActionsAtBeginning();

        conductBeginningActions();
    }

    private void collectActionsAtBeginning() {
        /**Collecting actions from families*/
        FamiliesContainer familiesContainer = new FamiliesContainer();
        for(Action action: familiesContainer.getActionListByHero(this.hero)){
            actionMedium.addAction(action);
        }
        List<Action> gameScenarioActions=this.gameSketch.getScenario().getScenario();
        for(Action action: gameScenarioActions){
            actionMedium.addAction(action);
        }

    }

    public void start() {
        setScoreLabel(score + "");
        rotateBulletSpecialCause();
        hero.born();
        medium.heroBorning(hero.getSpecyfication());
        abilitysLoading();
        abilityListining();

        medium.startTimer();
        this.gameSketch.getEnd().setMedium(this.medium);
        gameLoop();
    }



    private void gameLoop()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                repaint();
            }
        }, 0, 20);
    }
    private void repaint() {

        conductInnerActions();

        endGameChecking();

        bornCharacter();

        moveCharacters();

        startShooting();

        animate();

        colliionCheck();

        checkCharactersLifes();

    }

    private void conductBeginningActions(){
        this.actionMedium.begin();
    }
    private void conductInnerActions(){
        this.actionMedium.innerAct();
    }

    private void conductEndingActions(){
        this.actionMedium.end();
    }

    private void bornCharacter(){
        Enemy en = gameSketch.getEnemysChooser().getEnemy(gameActivity);
        if(en!=null)
            bornEnemy(en);
        summoning();
    }

    private void endGameChecking(){
        if(this.gameSketch.getEnd().check())
        {
            this.stop();
        }
    }
    private void rotateBulletSpecialCause(){
        if(hero.getBulletSpecyfication().getName().equals(BE.WENDYAXE.getValue())) {
            //hero.setBulletSpecyfication(new RotateBullet(BE.WENDYAXE,this.gameActivity, 10, 20, null, 100, 100,  R.drawable.wendyaxe, game, false,1,new Horizontal(),Shape.RECTANGLE,Permission.YES,Rarity.STARTING,new MoneyPossesStrategy("Mystery Coin",10)));
        }
    }
    private void  colliionCheck(){
        collisionTester.collisionChecking(hero, enemies, bullets, medium, beasts);
    }
    private void moveCharacters()
    {
        hero.startingMove(new EyeOnGame(this));

        for (int i=0;i<enemies.size();i++) {
            Enemy enemy= enemies.get(i);
            enemy.startingMove(new EyeOnGame(this));
        }

        for (Beast beast : this.beasts) {
            beast.startingMove(new EyeOnGame(this));

        }
    }

    private void startShooting(){
        addBullets();
        moveBullets();

    }
    private void addBullets(){
        for (Bullet b : tmpFurtka) {
            this.bullets.add(b);
            b.setFrame(getGameFrame());
            b.born();
        }
        tmpFurtka.clear();

        List<Bullet> herosNewBullets = hero.startShooting();
        if(herosNewBullets!=null)
        {
            for(Bullet bullet: herosNewBullets){
            this.bullets.add(bullet);
            bullet.born();
                bullet.setFrame(getGameFrame());
            medium.heroShot(bullet.getSpecyfication(), abilities);

        }}

        for (int i=0;i<enemies.size();i++) {
            Enemy enemy= enemies.get(i);
            List<Bullet> enemysNewBullets = enemy.startShooting();
            if(enemysNewBullets!=null) {
                for (Bullet bullet : enemysNewBullets) {
                    this.bullets.add(bullet);
                    bullet.born();
                    bullet.setFrame(getGameFrame());
                    medium.enemyShot(new EnemyShot(enemy.getSpecyfication(), bullet.getSpecyfication()));
                }
            }
        }
        for (Beast beast : this.beasts) {

            List<Bullet> beastsNewBullets = beast.startShooting();
            if(beastsNewBullets!=null) {
                for (Bullet bullet : beastsNewBullets) {
                    this.bullets.add(bullet);/** tutaj jeśli chcemy żeby kulki wystrzelone przez zwierzę liczyły się w stosunku dokulek wystrzelonych przez bohatera, to musimy to ustalić*/
                    bullet.born();
                    bullet.setFrame(getGameFrame());
                }
            }
        }
    }

    private void moveBullets(){
        for (Bullet bullet : bullets) {
            bullet.move(new EyeOnGame(this));
        }
    }

    private void animate(){
        itemsController.moveItems();
    }

    private void checkCharactersLifes(){
        heroLifeChecking();
        enenemysLifeChecking();
        bulletLifeChecking();
        enemyCleaning();
        bulletCleaning();
        beastsLifeChecking();
        beastCleaning();
    }

    private void bornEnemy(Enemy en) {
        if (en != null) {
            en.setFrame(game);
            enemies.add(en);
            en.born();
            medium.enemyBorning(en.getSpecyfication());
        }
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



    private void summoning() {
             hero.Summon(game, this.beasts);
    }


    public void enemyCleaning() {
        for (Enemy enemy : enemyGravedigger) {
            removeEnemyFromGame(enemy);
            medium.deathOfEnemy(enemy.getSpecyfication());
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
        conductEndingActions();

        //Show Result
        this.gameSketch.getBounty().fillBounty(medium, medium.getBounty());
        UserProfile userProfile = new UserProfile(gameActivity);
        userProfile.makeOfBounty(medium.getBounty());

        GameSummary.getInstance().setSummary(medium, this.hero.getSpecyfication(), "Nowa gra", medium.getBounty());

        Intent intent = new Intent(gameActivity, GameResult.class);

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
        hero.setStartingPoint();
    }



    public void removeItem(Item itemView) {
        this.itemsController.removeItem(itemView);
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
                    hero.getFirstAbility().addAction(getEyeOnGame());
                    medium.abilityUse(hero.getFirstAbility());
                }
            }
        });
        this.abilities.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getAbilities().getSecondAbility().isReady() && hero.getAbilities().getSecondAbility().isActive()) {
                    hero.getSecondAbility().addAction(getEyeOnGame());
                    medium.abilityUse(hero.getSecondAbility());
                }
            }
        });
        this.abilities.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getAbilities().getThirdAbility().isReady() && hero.getAbilities().getThirdAbility().isActive()) {
                    hero.getThirdAbility().addAction(getEyeOnGame());
                    medium.abilityUse(hero.getThirdAbility());
                }
            }
        });
    }


    //metody sekretne ;) furtki

    public void addBullet(Bullet bullet) {
        //this.bullets.add(bullet);
        bullet.setFrame(game);
        tmpFurtka.add(bullet);
        medium.heroShot(bullet.getSpecyfication(), abilities);
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
                ret.add(e.getSpecyfication());
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


    public FrameLayout getGameFrame(){
        return this.game;
    }

    public EyeOnGame getEyeOnGame() {
        return eyeOnGame;
    }
}
