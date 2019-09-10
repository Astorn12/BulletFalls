package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
// klasa która umożliwia stworzenie kulki z różnymi umiejętnościami np. trująco-spowalniającej, albo jakiejś innej
@JsonTypeName("mix")
public class Mix implements BulletDoToCharacterStrategy{
    List<BulletDoToCharacterStrategy>  bulletAbilityList;

    public Mix( List<BulletDoToCharacterStrategy> list)
    {
        this.bulletAbilityList=list;
    }
    public Mix(){}



    @Override
    public void doToCharacter(Character character) {
        for(BulletDoToCharacterStrategy b: bulletAbilityList)
        {
            b.doToCharacter(character);
        }
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return null;
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {
        for(BulletDoToCharacterStrategy b:this.bulletAbilityList)
        {
            b.showOwnDescription(linearLayout);
        }
    }

    public List<BulletDoToCharacterStrategy> getBulletAbilityList() {
        return bulletAbilityList;
    }

    public void setBulletAbilityList(List<BulletDoToCharacterStrategy> bulletAbilityList) {
        this.bulletAbilityList = bulletAbilityList;
    }
}
