package com.github.vanlaodev.androiddevlab1;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDataStoreImpl implements UserDataStore {

    private final SharedPreferences sharedPreferences;

    @Inject
    public UserDataStoreImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public String getLoggedInUser() {
        return sharedPreferences.getString("loggedInUser", null);
    }

    @Override
    public void setLoggedInUser(String loggedInUser) {
        sharedPreferences.edit().putString("loggedInUser", loggedInUser).apply();
    }
}
