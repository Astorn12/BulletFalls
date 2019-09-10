package com.example.user.bulletfalls.Supporters.TextViewSupporters;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.bulletfalls.GameSupporters.GroupPackage.Group;

import java.util.LinkedList;
import java.util.List;

public class SupporterTextView {

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
    public void setTextViewsTextSize(ViewGroup viewsGroup, int size)
    {
        List<TextView> textViews= new LinkedList<>();

        fillChildTextViewsList(viewsGroup,textViews);
        for(TextView textView: textViews)
        {
            textView.setTextSize(size);
        }
    }
}
