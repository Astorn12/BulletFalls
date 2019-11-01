package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.PositioningSupporters;

import android.graphics.Point;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

import java.util.LinkedList;
import java.util.List;

public class MultiBulletPositioner {
    public  void steadilyVerticalPositioning(List<Bullet> bullets, Character character) {
        int position = 0;
        int jump = character.getFrame().getHeight() / bullets.size();
        for (Bullet b : bullets) {


            position += jump;

            b.setStartingCoordinates(new Point(character.getStartingPointForBullet().x, position));
        }
    }

    public  List<Bullet> steadilyVerticalPositioning(Bullet pattern, int n, Character character) {
        List<Bullet> bullets = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Bullet clone = (Bullet)pattern.clone();
            bullets.add(clone);
        }
        this.steadilyVerticalPositioning(bullets,character);
        return bullets;
    }

}
