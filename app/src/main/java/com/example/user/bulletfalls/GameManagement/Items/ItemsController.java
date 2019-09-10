package com.example.user.bulletfalls.GameManagement.Items;

import android.widget.FrameLayout;

import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.Objects.Item;

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
        for(Item item:items)
        {
            if(item.isShowed())
                item.move();
            else {
                ((Game) item.getContext()).removeObject(item);
                this.garbage.add(item);
                System.out.println("Usuwanie itemu");

            }
        }
        odśmiecanie();
    }

    private void odśmiecanie() {
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
