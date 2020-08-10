package com.example.toothpicksample;

import android.app.Application;

import com.example.toothpicksample.di.RepositoryModule;

import toothpick.Scope;
import toothpick.Toothpick;

public final class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Scope appScope = Toothpick.openScope("APP");
        appScope.installModules(new RepositoryModule(getApplicationContext()));
    }
}
