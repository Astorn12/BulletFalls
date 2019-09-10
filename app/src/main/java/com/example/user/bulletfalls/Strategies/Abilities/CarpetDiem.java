package com.example.user.bulletfalls.Strategies.Abilities;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers.Linear;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage.Stot;
import com.example.user.bulletfalls.Supporters.ImageScaler;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
@JsonTypeName("carpeddiem")
public class CarpetDiem implements DoToCharacterStrategy{

    private static List<Integer> list= Arrays.asList(R.drawable.candy,R.drawable.grenda,R.drawable.waddles,R.drawable.soos,R.drawable.dipper,
            R.drawable.mabel,R.drawable.mcgucket);

    @Override
    public void doToCharacter(Character character) {
        Random random = new Random();
        int los=random.nextInt(8)+1;

        switch(los){
            case 1:
                character.setImageResource(list.get(0));
                character.getBullet().speedUp(10);
                break;
            case 2:
                character.setImageResource(list.get(1));
                character.getBullet().boostPower(0.1);
                break;
            case 3:
                character.setImageResource(list.get(2));
                if(character.getDoToBulletStrategy().getClass().equals(NoneDoToBulletStrategy.class))
                {
                    character.setDoToBulletStrategy(new Stot());
                }
                else
                {
                    character.heal(30);
                }
                break;
            case 4:
                character.setImageResource(list.get(3));
                character.boostResistance(10);
                break;
            case 5:
                character.setImageResource(list.get(4));
                character.speedUpShooting(1);
                break;
            case 6:
                character.setImageResource(list.get(5));
                character.speedUp(10);

                break;
            case 7:
                character.setImageResource(list.get(6));

                break;
        }
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
        ImageScaler imageScaler= new ImageScaler();
        for(int i=0;i<list.size();i++)
      {
          LinearLayout l1= new LinearLayout(context);
          l1.setOrientation(LinearLayout.HORIZONTAL);
          changingView.addView(l1);

          ImageView i1= new ImageView(context);
          i1.setImageResource(list.get(i));
          l1.addView(i1);
          imageScaler.scaleByHeight(i1,300);
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
