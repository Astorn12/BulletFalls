package com.example.user.bulletfalls.Strategies.Abilities.PositioningSupporters;

import android.graphics.Point;

import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Hero;

import java.util.LinkedList;
import java.util.List;

public class MultiBulletPositioner {
    public  void steadilyVerticalPositioning(List<Bullet> bullets, Character character) {
        int position = 0;
        int jump = character.getFrame().getHeight() / bullets.size();
        for (Bullet b : bullets) {


            position += jump;

            b.setStartingPoint(new Point(character.getStartingPointForBullet().x, position));
        }
    }

    public  List<Bullet> steadilyVerticalPositioning(Bullet pattern, int n, Character character) {
        List<Bullet> bullets = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Bullet clone = pattern.clone();
            bullets.add(clone);
        }
        this.steadilyVerticalPositioning(bullets,character);
        return bullets;
    }

}
