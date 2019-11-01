package com.example.user.bulletfalls.Game.Strategies.Scenario;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.IfActions.ActionConditions.ACGirlHero;
import com.example.user.bulletfalls.Game.ActionService.Actions.IfActions.IfAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.MultiplyHeroBullet;
import com.example.user.bulletfalls.Game.ActionService.Actions.MultiplyHeroLife;
import com.example.user.bulletfalls.GlobalUsage.Enums.DayNightView;
import com.example.user.bulletfalls.GlobalUsage.Enums.SymbolResource;
import com.example.user.bulletfalls.Game.Management.GameController;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Supporters.DpsConverter;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.BorderSetter;
import com.example.user.bulletfalls.GlobalUsage.Supporters.SymbolPackage.SymbolMaker;
import com.example.user.bulletfalls.GlobalUsage.Supporters.ScalerSupporter;

import java.util.LinkedList;
import java.util.List;

public class GirlPower extends UniversalGameScenario {


    @Override
    public List<Action> getScenario() {
        IfAction doubleGirlsLife=new IfAction(ActionType.BEGIN,new MultiplyHeroLife(ActionType.BEGIN,2),new ACGirlHero());
        IfAction doubleGirlsBulletPower=new IfAction(ActionType.BEGIN,new MultiplyHeroBullet(ActionType.BEGIN,2),new ACGirlHero());

        List<Action> actions=new LinkedList<>();
        actions.add(doubleGirlsBulletPower);
        actions.add(doubleGirlsLife);
        return actions;
    }



    @Override
    public ViewGroup selfDescribe(Context context) {

        LinearLayout cover= new LinearLayout(context);
        cover.setOrientation(LinearLayout.VERTICAL);


        BorderSetter borderSetter= new BorderSetter(2,Color.BLACK);
        ScalerSupporter scaler= new ScalerSupporter();
        ScalerSupporter scalerSupporter = new ScalerSupporter();
        DpsConverter dpsConverter= new DpsConverter(context);

        LinearLayout main= new LinearLayout(context);

        main.setOrientation(LinearLayout.HORIZONTAL);
        main.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT));
        main.setGravity(Gravity.CENTER);
        ImageView femaleIcon= new ImageView(context);
        femaleIcon.setImageResource(R.drawable.femaleicon);
        main.addView(femaleIcon);
        scalerSupporter.scaleByHeight(femaleIcon);


        ImageView arrow= new ImageView(context);
        arrow.setImageResource(R.drawable.arrow);


        scaler.scaleByHeight(arrow);
        main.addView(arrow);
        arrow.getLayoutParams().height=dpsConverter.getPixels(30);


        LinearLayout bonusses= new LinearLayout(context);


        bonusses.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
        bonusses.setOrientation(LinearLayout.VERTICAL);
        bonusses.setGravity(Gravity.LEFT);
        bonusses.setPadding(10,10,10,10);
        borderSetter.setBorderToLayout(bonusses);
        main.addView(bonusses);


        SymbolMaker life= new SymbolMaker(context);
        life.addSymbolContent(DayNightView.HEART.getValue());
        life.addText("x2");
        life.setListUse();

        bonusses.addView(life.getSymbol());


        SymbolMaker bulletPower= new SymbolMaker(context);
        bulletPower.addSymbolContent(R.drawable.blue);
        bulletPower.addFootage(SymbolResource.POWER.getValue());
        bulletPower.addText("x2");
        bulletPower.setListUse();


        bonusses.addView(bulletPower.getSymbol());


        TextView title=new TextView(context);
        title.setGravity(Gravity.CENTER);
        title.setText("Boost");


        cover.addView(title);
        cover.addView(main);
        cover.setPadding(10,0,10,0);
        return cover;
    }
}
