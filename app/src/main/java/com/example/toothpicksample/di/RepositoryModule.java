package com.example.toothpicksample.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.toothpicksample.PrefUserRepository;
import com.example.toothpicksample.UserRepository;

import toothpick.config.Module;

public class RepositoryModule extends Module {

    public RepositoryModule(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app.prefs", Context.MODE_PRIVATE);
        bind(SharedPreferences.class).toInstance(sharedPreferences);

        bind(UserRepository.class)
                .to(PrefUserRepository.class)
                .singleton();
    }

}
