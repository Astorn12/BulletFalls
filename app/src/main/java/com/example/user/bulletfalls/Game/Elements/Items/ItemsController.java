package com.example.user.bulletfalls.Game.Elements.Items;

import android.widget.FrameLayout;

import com.example.user.bulletfalls.Game.Activities.Game;

import java.util.LinkedList;
import java.util.List;

public class ItemsController {
    List<Item> items;
    List<Item> garbage;
    FrameLayout game;
    public ItemsController(FrameLayout frameLayout)
    {
        this.items=new LinkedList<>();
        this.garbage= new LinkedList<>();
        this.game=frameLayout;
    }


    public void registerItem(Item item)
    {
        this.items.add(item);
        //((Game)this.game.getContext()).addView(item);

    }

    public void moveItems()
    {
        for(int i=0;i<items.size();i++)
        {
            Item item=items.get(i);
            if(item.isShowed())
                item.move(null);
            else {
                ((Game) item.getContext()).removeObject(item);
                this.garbage.add(item);
                System.out.println("Usuwanie itemu");

            }
        }
        garbageCleaning();
    }

    private void garbageCleaning() {
        for(Item item: garbage)
        {
            this.items.remove(item);
        }
        garbage.clear();
    }

    public void removeItem(Item item) {
        this.garbage.add(item);
    }
}
