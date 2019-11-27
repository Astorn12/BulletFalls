package com.example.user.bulletfalls.Game.Elements.Beast;

import android.content.Context;
import android.graphics.Point;

import com.example.user.bulletfalls.Game.Elements.Helper.Sizers.CharacterSizer;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.CharacterAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.CharacterCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BeastPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.CharacterVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.EBeastType;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("beast")
public class Beast extends Character {

    /**PASSIVE STATISTICS*/
    EBeastType eBeastType;

    public Beast(Context context, BeastSpecyfication sbs) {
        super(context, sbs);
        this.eBeastType=sbs.geteBeastType();
    }



    @Override
    public Point getStartingPointForBullet() {
        switch(this.eBeastType)
        {
            case ENEMY:
                return new Point((int)(getX()-getWidth()),(int)(getY()+getHeight()/2));

            case HERO:
                return new Point((int)(getX()+getWidth()),(int)(getY()+getHeight()/2));
            default:
                return null;
        }
    }

    @Override
    public void died() {
        ((Game)getContext()).removeLifeText(this.textLife);
        ((Game)getContext()).removeObject(this);
    }

    public EBeastType geteBeastType() {
        return eBeastType;
    }

    public void seteBeastType(EBeastType eBeastType) {
        this.eBeastType = eBeastType;
    }

    public Beast clone()
    {
        return new Beast(this.getContext(),this.getSpecyfication());
    }

    public BeastSpecyfication getSpecyfication(){
        return new BeastSpecyfication(this.name,
                new CharacterVS(this.image,CharacterSizer.getDipperCounter(this.height),this.description),
                new BeastPS(this.speed,this.life,this.shootingSpeed,this.bulletSpecyfication,this.eBeastType),
                new CharacterAS(this.characterPositioning,this.attackDefenceFilter,this.appearAction),
                new CharacterCS(this.indyvidualHeroMarker,this.familyNames,this.kind));
    }
    @Override
    public void move(EyeOnGame eyeOnGame)
    {
        uploadlifeview();
        ((Game)this.getContext()).moveViewElement(this,0,speed);
        if(this.getY()<0-speed)
        {
            ((Game) this.getContext()).setViewElement(this,(int)this.getX(),0);
            this.speed*=-1;
        }
        else if (getY()>frame.getHeight()-this.getHeight()-speed)
        {
            ((Game)this.getContext()).setViewElement(this,(int)this.getX(),frame.getHeight() - this.getHeight());
            this.speed*=-1;
        }
    }

    @Override
    public void setStartingPoint() {

    }

    @Override
    public void born()
    {
        super.born();
        this.moveAble=true;
        this.setImageResource(this.getImage());
    }
}
