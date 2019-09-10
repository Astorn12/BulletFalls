package com.example.user.bulletfalls.Supporters;

public class Size {

    public int getBulletSize(int width, int height)
    {
        int field=width*height;
        int size=0;
       for(int i=0;i<10000;i+=100)
       {
           if(field>i)
           {
               return size;
           }
           size++;
       }
       return 0;
    }
}
