package com.example.user.bulletfalls.Supporters.GuiSupporters;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.NotImplementedException;
import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class SupporterBackground {

    public void setTextViewBackground(TextView textView, String color, float alpha)
    {
        int colorNr=Color.parseColor(color);
        textView.setBackgroundColor(colorNr);
        textView.setAlpha(alpha);
        textView.setTextColor(Color.BLACK);
    }

    public void setTextViewBackground(TextView textView,String color)
    {
       this.setTextViewBackground(textView,color,0.5f);
    }

    public void setTextViewBackground(TextView textView)
    {
        this.setTextViewBackground(textView,"#ffffff");




    }

    public void setChildViewBackground(ViewGroup viewGroup)
    {
        viewGroup.setBackgroundColor(Color.WHITE);
        viewGroup.setAlpha(0.5f);
        List<TextView> textViews= new LinkedList<>();

        fillChildTextViewsList(viewGroup,textViews);
        for(TextView textView: textViews)
        {
            textView.setTextColor(Color.BLACK);
        }

    }
    private void fillChildTextViewsList(ViewGroup viewGroup,List<TextView> textViewList)
    {
        for(int i=0;i<viewGroup.getChildCount();i++)
        {

            if(ViewGroup.class.isAssignableFrom(viewGroup.getChildAt(i).getClass()))
            {
                 fillChildTextViewsList((ViewGroup)viewGroup.getChildAt(i),textViewList);
            }
            else if(viewGroup.getChildAt(i).getClass().equals(TextView.class))
            {
               textViewList.add ((TextView)viewGroup.getChildAt(i));
            }
        }
    }



    /*public void setChildTextViewsBackground(ViewGroup viewGroup)
    {

        viewGroup.setBackgroundColor(Color.WHITE);
        viewGroup.setAlpha(0.5f);

        for(int i=0;i<viewGroup.getChildCount();i++)
        {



             if(ViewGroup.class.isAssignableFrom(viewGroup.getChildAt(i).getClass()))
            {
                setChildTextViewsBackground((ViewGroup)viewGroup.getChildAt(i));
            }
            else if(viewGroup.getChildAt(i).getClass().equals(TextView.class))
            {
                ((TextView)viewGroup.getChildAt(i)).setTextColor(Color.BLACK);
            }
        }
      //  throw new NotImplementedException("Kącept metody nie działa");
    }*/

}
