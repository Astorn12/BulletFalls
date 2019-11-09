package com.example.user.bulletfalls.GlobalUsage.Nieużywane;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.R;

public class MyAnimation extends View {

    private ObjectAnimator animator = new ObjectAnimator();
    private Character character;
    private String animation;
    public MyAnimation(Context context, AttributeSet attrs, int defStyleAttr, Character character, String name) {
        super(context, attrs, defStyleAttr);
        this.character = character;
        this.animation=name;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        animator.setTarget(this);
        // the value must match a function with the signature "void setAnimationFrame(int frame)" (using the magic of reflection)
        animator.setPropertyName("animationFrame");
        animator.setIntValues(0, 39);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setDuration(1000 / 60 * 40);
        animator.setInterpolator(new LinearInterpolator());

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // If you need to do something at the end of the animation
            }
        });
    }

    public void startAnimation() {
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setAnimationFrame(int frame) {
        setBackground(getDrawableForFrame(frame));
    }

    private Drawable getDrawableForFrame(int frame) {
        // Here you implement the loading from Glide or any other library or custom code
        Glide.with(this).load(R.drawable.grenda_shoot_animation).into(character);
        return null;
    }
}