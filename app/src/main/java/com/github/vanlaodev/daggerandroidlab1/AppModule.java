package com.github.vanlaodev.daggerandroidlab1;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Map<String, Presenter> providePresenterMap() {
        return new HashMap<>();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(App app) {
        return app.getSharedPreferences("main", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    UserDataStore provideUserDataStore(UserDataStoreImpl userDataStore) {
        return userDataStore;
    }
}
