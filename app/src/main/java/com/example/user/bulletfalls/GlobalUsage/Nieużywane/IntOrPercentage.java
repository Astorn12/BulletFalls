package com.example.user.bulletfalls.GlobalUsage.Nieużywane;

public class IntOrPercentage {
    Integer x;
    Float percentage;
    IntOrPercentage(Integer x,Float percentage)
    {
        if(x!=null&&percentage!=null){
        }

        else if(x!=null)
        {
            this.x=x;
            this.percentage=null;
        }
        else if(percentage!=null&&percentage<1.f)
        {
         this.percentage=percentage;
        }
        else
        {
            this.x=null;
            this.percentage=null;
        }


    }

    IntOrPercentage(int x) {
        this.x = x;
        this.percentage = null;
    }
    IntOrPercentage(float percentage)
    {
        if(percentage<1)
        {
            this.percentage=percentage;

        }
        else
        {
            this.percentage=null;
        }
        this.x=null;
    }

    public int changeValue(int power)
    {
        if(x!=null)
        {
            return power+this.x;
        }
        else if(this.percentage!=null)
        {
            return (int)(power*percentage);
        }
        else return power;
    }

}
