package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.skydoves.powermenu.MenuBaseAdapter;

public class SelectorMenuAdapter extends MenuBaseAdapter<FeatureMenuItem> {

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        FeatureMenuItem item = (FeatureMenuItem) getItem(index);

        if(view == null) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(item.getMasterAbility().getImage());
            imageView.setAdjustViewBounds(true);
            view=imageView;
            View finalView = view;
            ((ImageView) view).setMaxHeight(100);

            /*imageView.post(new Runnable() {
                @Override
                public void run() {
                    finalView.getLayoutParams().height=100;
                }
            });*/

        }

        if(item.isSelected) view.setBackgroundColor(Color.RED);
        else view.setBackgroundColor(Color.TRANSPARENT);
        return super.getView(index, view, viewGroup);
    }
}
