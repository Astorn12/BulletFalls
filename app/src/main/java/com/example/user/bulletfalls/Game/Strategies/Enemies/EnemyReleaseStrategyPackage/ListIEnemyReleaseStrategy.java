package com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.BorderSetter;
import com.example.user.bulletfalls.GlobalUsage.Supporters.ScalerSupporter;

import java.util.LinkedList;
import java.util.List;

public abstract class ListIEnemyReleaseStrategy implements IEnemyReleaseStrategy {



    List<EnemySpecyfication> enemySpecyficationSpecyfications;

    public ListIEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyficationSpecyfications)
    {
        this.enemySpecyficationSpecyfications = enemySpecyficationSpecyfications;

    }
    public ListIEnemyReleaseStrategy(EnemySpecyfication... enemys)
    {
        this.enemySpecyficationSpecyfications= new LinkedList<>();

        for(EnemySpecyfication e: enemys)
        {
            this.enemySpecyficationSpecyfications.add(e);
        }

    }

    @Override
    public abstract Enemy releaseChoosenEnemy(Context context);


    @Override
    public ViewGroup selfDescribe(Context context) {
        BorderSetter borderSetter= new BorderSetter(2,Color.BLACK);


        LinearLayout cover= new LinearLayout(context);
        cover.setOrientation(LinearLayout.VERTICAL);

        HorizontalScrollView horizontalScrollView= new HorizontalScrollView(context);


        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(-2,-2);

        params.gravity=Gravity.CENTER;
        horizontalScrollView.setLayoutParams(params);


        LinearLayout ll= new LinearLayout(context);

        ScalerSupporter is= new ScalerSupporter();


        horizontalScrollView.addView(ll);

        ll.setLayoutParams(new HorizontalScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        ll.setBackgroundColor(Color.RED);
        for(EnemySpecyfication e:this.enemySpecyficationSpecyfications) {
            Enemy enemy=new Enemy(context,e);
            is.scaleByHeight(enemy);
            ll.addView(enemy);
        }

        TextView title= new TextView(context);
        title.setText("Opponents");
        title.setGravity(Gravity.CENTER);
        cover.addView(title);
        cover.addView(horizontalScrollView);


        ll.setGravity(Gravity.CENTER);
        borderSetter.setBorderToLayout(ll);
        cover.setPadding(10,0,10,10);
        cover.setGravity(Gravity.CENTER);
        return cover;
    }

}
