package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.CarpediemAction;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.GlobalUsage.Supporters.ScalerSupporter;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;
import java.util.List;

@JsonTypeName("carpeddiem")
public class CarpetDiem implements StartAction {

    private static List<Integer> list= Arrays.asList(R.drawable.candy,R.drawable.grenda,R.drawable.waddles,R.drawable.soos,R.drawable.dipper,
            R.drawable.mabel,R.drawable.mcgucket);

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

      return new CarpediemAction(ActionType.INNER);

    }

    @Override
    public String getDescription() {
        return "Umiejętność zmienia postać losowo na jedną z poniższych, dodatkowo postać usyzkuje na stałe pewne umiejętności przy każdorazowej zmianie, zależne" +
                "od postaci w jaką się zamieni";
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {
        List<String> descriptionList= Arrays.asList(
                "Zwiększenie prędkości kulki o 10",
                "Zwiększenie siły kulki o 10%",
                "Umiejętność STOT / heal 30",
                "Zwiększenie rezystancji o 10",
                "Zwiększenie szybkostrzelności o 1",
                "Przyśpieszenie szybkości postaci o 10",
                "Brak bonusu"
        );


        Context context= linearLayout.getContext();

        HorizontalScrollView scrollView= new HorizontalScrollView(context);
        linearLayout.addView(scrollView);
        LinearLayout changingView= new LinearLayout(context);
        scrollView.addView(changingView);
        ScalerSupporter scalerSupporter = new ScalerSupporter();
        for(int i=0;i<list.size();i++)
      {
          LinearLayout l1= new LinearLayout(context);
          l1.setOrientation(LinearLayout.HORIZONTAL);
          changingView.addView(l1);

          ImageView i1= new ImageView(context);
          i1.setImageResource(list.get(i));
          l1.addView(i1);
          scalerSupporter.scaleByHeight(i1,300);
          ((LinearLayout.LayoutParams) i1.getLayoutParams()).weight=1;
          TextView t1= new TextView(context);
          t1.setText(descriptionList.get(i));
          l1.addView(t1);
          t1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
          t1.getLayoutParams().width=200;
          ((LinearLayout.LayoutParams)t1.getLayoutParams()).weight=  0.5f;
      }
    }


}
