package com.example.user.bulletfalls.Missions;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.user.bulletfalls.MyApplication;
import com.example.user.bulletfalls.Missions.Rewards.RewardKinds.MissionReward;
import com.example.user.bulletfalls.Missions.Requirements.OveralStatistick;
import com.example.user.bulletfalls.Storage.Data.OveralStatisticsRepository;

public  class Mission {
   OveralStatistick statistickToAchive;
   MissionReward missionReward;
   String description;

    public Mission(OveralStatistick statistickToAchive,MissionReward missionReward, String description) {
        this.statistickToAchive = statistickToAchive;
        this.missionReward= missionReward;
        this.description=description;
    }

//    public void actForAction(OveralStatistick  updateForOveralStatistick){
//
//        if(overal)
//
//
//       if(isMissionMatchOveralStatistic(updateForOveralStatistick)){
//
//           OveralStatistick finalStatictick=updateStatistic(updateForOveralStatistick);
//
//           if(!wasMissionSuccededBefore(updateForOveralStatistick,finalStatictick)&&isMissionSucceded(finalStatictick)){
//
//           }
//           if(finalStatictick.getAmount()>=this.statistickToAchive.getAmount()){
//               reward();
//           }
//       }
//       }

       public void actForAction(Missionable missionable,int token,Context context){

        OveralStatisticsRepository repo= new OveralStatisticsRepository(context);
        OveralStatistick statFromDB=repo.getByName(this.statistickToAchive.getName());

        if(statFromDB==null){
            statFromDB= new OveralStatistick(this.statistickToAchive.getName(),0);
           addOveralStatistic(repo, statFromDB);
        }

        /**update oberal statistics*/
        statFromDB.setChecker(this.statistickToAchive.getChecker());
            statFromDB.update(missionable,token,context);
        /**check mission complentes*/
           if(!isMissionAlreadyCompleted(statFromDB)){
              if( repo.getByName(this.statistickToAchive.getName()).getAmount()<=this.statistickToAchive.getAmount()){
                  reward();
              }
           }
       }

    private boolean isMissionAlreadyCompleted(OveralStatistick statFromDB) {

        if(statFromDB.getAmount()<=this.statistickToAchive.getAmount()) return true;
        return false;
    }

       private void addOveralStatistic(OveralStatisticsRepository repo, OveralStatistick stats) {
         repo.update(stats);
       }



    private boolean isMissionMatchOveralStatistic(OveralStatistick updateForOveralStatistick) {
        return this.statistickToAchive.getName().equals(updateForOveralStatistick.getName());
    }
    public OveralStatistick updateStatistic(OveralStatistick updateForOveralStatistic){
        OveralStatistick updatedStatistick;
        OveralStatisticsRepository overalStatisticsRepository= new OveralStatisticsRepository(new MyApplication().getBaseContext());
        OveralStatistick overalStatistickFromDatabase =overalStatisticsRepository.getByName(updateForOveralStatistic.getName());

        if(overalStatistickFromDatabase!=null) {


            overalStatistickFromDatabase.incrementAmount(updateForOveralStatistic.getAmount());
            updatedStatistick=overalStatistickFromDatabase;
        }
        else
            updatedStatistick=updateForOveralStatistic;

        overalStatisticsRepository.update(updatedStatistick);


        return updatedStatistick;
    }
    private boolean wasMissionSuccededBefore(OveralStatistick update, OveralStatistick updatedStatistic){
        return updatedStatistic.getAmount()-update.getAmount()>=this.statistickToAchive.getAmount();
    }
    private boolean isMissionSucceded(OveralStatistick finalStatictick) {
        return finalStatictick.getAmount()>=this.statistickToAchive.getAmount();
    }
    protected  void reward(){
        this.missionReward.rewardUser(MyApplication.getAppContext());
    }



    /**Showing methods*/

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getVizualization(Context context){
        OveralStatisticsRepository overalStatisticsRepository= new OveralStatisticsRepository(context);

        OveralStatistick overalStatistick= overalStatisticsRepository.getByName(this.statistickToAchive.getName());

        return getView(context,overalStatistick.getAmount(),this.statistickToAchive.getAmount());
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private View getView(Context context, int alreadyHave, int toFulfill){
        GradientDrawable mainshape = new GradientDrawable();

        mainshape.setCornerRadii(new float[] { 20, 20, 20, 20, 20, 20, 20, 20 });
        mainshape.setStroke(4,Color.BLACK);
        //mainshape.setColor(Color.rgb(233,197,139));
        mainshape.setColors(new int []{Color.rgb(233,197,139),Color.rgb(255,231,175)});

        LinearLayout main= new LinearLayout(context);

        LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
        layoutParams1.setMargins(10,20,10,20);
        main.setLayoutParams(layoutParams1);
        main.setOrientation(LinearLayout.HORIZONTAL);
        main.setBackground(mainshape);


        LinearLayout missionNameWithProgressBar= new LinearLayout(context);
        missionNameWithProgressBar.setOrientation(LinearLayout.VERTICAL);


        FrameLayout missionProgresBar= new FrameLayout(context);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);

        missionNameWithProgressBar.setLayoutParams(layoutParams);

        LinearLayout barContainer= new LinearLayout(context);
        barContainer.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        barContainer.setOrientation(LinearLayout.HORIZONTAL);

        int greenWeight;
        int redWeight;

        if(alreadyHave<toFulfill) {
            greenWeight=alreadyHave;
            redWeight=toFulfill;
        }else{
            greenWeight=1;
            redWeight=0;
        }

        FrameLayout green=new FrameLayout(context);
        LinearLayout.LayoutParams layoutParams3=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,greenWeight);
        layoutParams3.setMargins(10,10,10,10);
        green.setLayoutParams(layoutParams3);

        GradientDrawable shape1 = new GradientDrawable();

        shape1.setCornerRadii(new float[] { 10, 10, 10, 10, 10, 10, 10, 10 });
        shape1.setColors(new int[]{Color.rgb(84,247,96),Color.rgb(25,222,106)});
        green.setBackground(shape1);



        FrameLayout red= new FrameLayout(context);
        red.setBackgroundColor(Color.TRANSPARENT);
        red.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,redWeight));

        barContainer.addView(green);
        barContainer.addView(red);

        missionProgresBar.addView(barContainer);
        LinearLayout.LayoutParams layoutParams2= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(10,2,10,2);
        missionProgresBar.setLayoutParams(layoutParams2);

        int cR=20;
        GradientDrawable shape = new GradientDrawable();

        shape.setCornerRadii(new float[] { 20, 20, 20, 20, 20, 20, 20, 20 });
        shape.setColors(new int[]{Color.rgb(167,100,47),Color.rgb(198,133,75)});

        missionProgresBar.setBackground(shape);






        TextView textProgress = new TextView(context);
        textProgress.setGravity(Gravity.CENTER);



        TextView description= new TextView(context);
        description.setGravity(Gravity.CENTER);
        description.setText(this.description);


        if(alreadyHave<toFulfill){
            textProgress.setText(alreadyHave+"/"+toFulfill);
        }else textProgress.setText("Mission fulfill");

        missionProgresBar.addView(textProgress);

        missionNameWithProgressBar.addView(description);
        missionNameWithProgressBar.addView(missionProgresBar);

        main.addView(missionNameWithProgressBar);
        View rewardView=this.missionReward.getIcon(context);
        rewardView.setLayoutParams(new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT,1));
        main.addView(rewardView);
        return main;
    }


    public void updateOveralStatisticSaving(Context context){
        OveralStatisticsRepository overalStatisticsRepository= new OveralStatisticsRepository(context);

        if (!overalStatisticsRepository.exist(this.statistickToAchive.getName())){
            OveralStatistick initialOveralStatistics = new OveralStatistick(this.statistickToAchive.getName(), 0);
            overalStatisticsRepository.add(initialOveralStatistics);
        }
    }




}


