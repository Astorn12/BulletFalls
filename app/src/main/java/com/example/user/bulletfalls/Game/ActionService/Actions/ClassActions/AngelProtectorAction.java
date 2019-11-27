package com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.AngelProtector;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class AngelProtectorAction extends ClassAction {
    int image;
    AngelProtector angelProtector;
    int protectionSeconds;
    public AngelProtectorAction(int timeQuand,int image,int seconds) {
        super(timeQuand);
        this.image=image;
        angelProtector= new AngelProtector(seconds,this.image);
        this.protectionSeconds=seconds;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void doClassAction(EyeOnGame eog) {
       eog.addAction(new AngelProtector(protectionSeconds,this.image));
    }
}
