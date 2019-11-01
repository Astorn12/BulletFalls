package com.example.user.bulletfalls.Game.Strategies;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Activities.GameListActivity.PictureLetter.Break;
import com.example.user.bulletfalls.Game.Strategies.End.EndGame;
import com.example.user.bulletfalls.Game.Strategies.Enemies.EnemysChooser;
import com.example.user.bulletfalls.Game.Strategies.Scenario.IGameScenario;
import com.example.user.bulletfalls.Game.Strategies.Bounty.BountyAssignerDecorator;
import com.example.user.bulletfalls.Game.Strategies.Requirements.IGameRequirements;
import com.example.user.bulletfalls.GlobalUsage.Supporters.DpsConverter;
import com.example.user.bulletfalls.GlobalUsage.Supporters.ScalerSupporter;

public class GameSketch {

    protected static GameSketch gameSketch = new GameSketch();
    int background;
    int miniature;
    String nameOfTheGame;


    EndGame end;
    EnemysChooser enemysChooser;//strategia wybierania bohaterów, ale posiada również zbiór dostępnych bphaterów
    IGameScenario scenario;
    BountyAssignerDecorator bounty;//przypisanie nagród na końcu
    IGameRequirements requirements;

    private boolean required;


    public GameSketch(String name,int miniature, IGameScenario scenario, EnemysChooser enemys, BountyAssignerDecorator bounty, EndGame end, IGameRequirements requirements){
        this.nameOfTheGame=name;
        this.scenario =scenario;
        this.enemysChooser=enemys;
        this.bounty =bounty;
        this.end =end;
        this.requirements =requirements;
        this.miniature=miniature;
    }
    GameSketch()
    {

    }



    public void setStrategies(EnemysChooser enemysChooser, int background , String nameOfTheGame, BountyAssignerDecorator IBountyAssigner)
    {
        this.enemysChooser=enemysChooser;
        this.background=background;
        this.nameOfTheGame=nameOfTheGame;
        this.bounty = IBountyAssigner;
    }

    public EnemysChooser getEnemysChooser(Context context) {

        return enemysChooser;
    }

    public int getBackground() {
        return background;
    }



    public static GameSketch getGameSketch() {
        return gameSketch;
    }

    public static void setGameSketch(GameSketch gameSketch) {
        GameSketch.gameSketch = gameSketch;
    }

    public EnemysChooser getEnemysChooser() {
        return enemysChooser;
    }

    public void setEnemysChooser(EnemysChooser enemysChooser) {
        this.enemysChooser = enemysChooser;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getMiniature() {
        return miniature;
    }

    public void setMiniature(int miniature) {
        this.miniature = miniature;
    }

    public String getNameOfTheGame() {
        return nameOfTheGame;
    }

    public void setNameOfTheGame(String nameOfTheGame) {
        this.nameOfTheGame = nameOfTheGame;
    }

    public BountyAssignerDecorator getBounty() {
        return bounty;
    }

    public void setBounty(BountyAssignerDecorator bounty) {
        this.bounty = bounty;
    }

    public EndGame getEnd() {
        return end;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public LinearLayout getHeaderView(Context context)
    {
        /**Ustawienia Panelu*/
        DpsConverter dps= new DpsConverter(context);
        LinearLayout minGame= new LinearLayout(context);
        minGame.setGravity(Gravity.CENTER_VERTICAL);
        minGame.setBackgroundResource(this.miniature);
        Drawable background=minGame.getBackground();
        background.setAlpha(50);

        int pad=dps.getPixels(4);
        minGame.setPadding(pad,pad,pad,pad);

        minGame.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,dps.getPixels(70)));
        minGame.setOrientation(LinearLayout.HORIZONTAL);

        /**Nazwa Gry*/
        TextView textView = new TextView(context);
        textView.setText(this.nameOfTheGame);
        minGame.addView(textView);

        textView.setTextSize(24);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(0,-1);
        layoutParams.weight=1.5f;
        textView.setLayoutParams(layoutParams);



        minGame.addView(new Break(context));
        /**Nagroda - IBountyAssiger*/
        ViewGroup bountyView=this.bounty.selfDescribe(context);

        LinearLayout.LayoutParams l= new LinearLayout.LayoutParams(0,-1);
        l.weight=1;
        bountyView.setLayoutParams(l);
        bountyView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        minGame.addView(bountyView);


        minGame.addView(new Break(context));
        /**Requirements - IGameRequirements*/
        ViewGroup requirementsView=this.requirements.selfDescribe(context);
        LinearLayout.LayoutParams requiredLayout= new LinearLayout.LayoutParams(0,-1);
        requiredLayout.weight=1;
        requirementsView.setLayoutParams(requiredLayout);

        minGame.addView(requirementsView);


        this.required=requirements.canPlay(context);
        if(!this.required)
        {

            final Drawable drawable = new ColorDrawable(Color.rgb(125,125,125));
            minGame.setForeground(drawable);
            minGame.getForeground().setAlpha(150);
        }


        return minGame;
    }
    public LinearLayout getExpandableView(Context context) {
        DpsConverter dps= new DpsConverter(context);
        ScalerSupporter scaler= new ScalerSupporter();

        LinearLayout minGame= new LinearLayout(context);
        //minGame.setBackgroundResource(this.miniature);
        int pad=dps.getPixels(4);
        minGame.setPadding(pad,pad,pad,pad);

        minGame.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,dps.getPixels(70)));
        minGame.setOrientation(LinearLayout.HORIZONTAL);


        /**Scenariusz Gry -  IGameScenario*/
        minGame.addView(scaler.inLineScaleReturn(this.scenario.selfDescribe(context),-2,-1,0));
        /**Lista przeciwników- IEnemyChooser*/
        minGame.addView(scaler.inLineScaleReturn(this.enemysChooser.selfDescribe(context),-1,-1,1));
        /**Game Ending - EndGame*/
        minGame.addView(scaler.inLineScaleReturn(this.end.selfDescribe(context),-2,-1,0));

        return minGame;
    }

    public IGameRequirements getRequirements() {

        return requirements;
    }

    public void setRequirements(IGameRequirements requirements) {
        this.requirements = requirements;
    }

    public boolean canPlay() {
        return this.required;
    }

    public IGameScenario getScenario() {
        return scenario;
    }
}
