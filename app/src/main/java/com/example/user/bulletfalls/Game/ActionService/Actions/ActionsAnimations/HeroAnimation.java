package com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationListeners.AnimationController;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Elements.Helper.Dynamic;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Enums.HE;
import com.example.user.bulletfalls.GlobalUsage.Supporters.Dimension;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.security.AccessController.getContext;

@JsonTypeName("heroanimation")
public class HeroAnimation implements GameAnimation {

    List<HeroDance> heroDances;
    AnimationController animationController;
    public HeroAnimation(HeroDance... heroDances){
       this.heroDances= Arrays.asList(heroDances);
       this.animationController= new AnimationController();
    }
    private HeroAnimation(){
        this.animationController= new AnimationController();
    }
    @Override
    public AnimationController animate(EyeOnGame eyeOnGame) {

        Hero hero= eyeOnGame.getHero();
        //hero.powerAnimation(tryGetAnimationResource(eyeOnGame));

        HeroDance heroDance=tryGetAnimationResource(eyeOnGame);
       // ((Game)eyeOnGame.getGameContext()).bigChange(hero, eyeOnGame.getGameContext().getResources().getDrawable(R.drawable.quentintrembley_empty_animation));
        Game game=((Game)eyeOnGame.getGameContext());
       Runnable r= ((Game)eyeOnGame.getGameContext()).setBackgroundResource(hero,heroDance.animationResource);
      // game.runOnUiThread(r);

        hero.post(new Runnable() {
            @Override
            public void run() {
                int h1;
                int w1;


                    h1=hero.getDrawable().getIntrinsicHeight();
                    w1=hero.getDrawable().getIntrinsicWidth();


                Dimension oldDimension= new Dimension(hero.getLayoutParams().width,hero.getLayoutParams().height);








                AnimationDrawable animationDrawable= (AnimationDrawable) hero.getBackground();

                int oldSpeed= hero.getSpeed();
                hero.setSpeed(0);
                int ha= animationDrawable.getFrame(1).getIntrinsicHeight();
                int wa =  animationDrawable.getFrame(1).getIntrinsicWidth();
                int realwa=wa*hero.getLayoutParams().width/w1;
                int realha=ha*hero.getLayoutParams().height/h1;

                hero.setAlpha(0);
                hero.getLayoutParams().width=(int)(realwa*heroDance.scale);
                hero.getLayoutParams().height=(int)(realha*heroDance.scale);

                animationDrawable.start();
                checkIfAnimationDone(hero,animationDrawable,oldSpeed,oldDimension);

            }
        });
        return this.animationController;
    }

    public void checkIfAnimationDone(Hero hero,AnimationDrawable animation, final int oldSpeed,final Dimension dimension){
        final AnimationDrawable a = animation;
        int timeBetweenChecks = 300;
        Handler h = new Handler();

        h.postDelayed(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void run(){
                if (a.getCurrent() != a.getFrame(a.getNumberOfFrames() - 1)){
                    checkIfAnimationDone(hero,a,oldSpeed,dimension);
                } else{
                    hero.setSpeed(oldSpeed);
                    hero.setAlpha(255);
                    hero.setBackground(null);
                    hero.getLayoutParams().width=dimension.getWidth();
                    hero.getLayoutParams().height=dimension.getHeight();
                    animationController.animationHasEnded();
                }
            }
        }, timeBetweenChecks);
    }














    @Override
    public boolean willBeAnimated(EyeOnGame eyeOnGame) {

        if(tryGetAnimationResource(eyeOnGame)!=null) return true;

        return false;
    }

    private HeroDance tryGetAnimationResource(EyeOnGame eyeOnGame){
        String heroName= eyeOnGame.getHero().getName();

        for(HeroDance dance: this.heroDances){
            if(dance.heroId.getValue().equals(heroName)) return dance;
        }
        return null;
    }

    public List<HeroDance> getHeroDances() {
        return heroDances;
    }

    public void setHeroDances(List<HeroDance> heroDances) {
        this.heroDances = heroDances;
    }
}
