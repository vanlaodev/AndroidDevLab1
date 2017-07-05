package com.github.vanlaodev.daggerandroidlab1;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

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
