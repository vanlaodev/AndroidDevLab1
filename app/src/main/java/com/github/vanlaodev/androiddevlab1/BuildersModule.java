package com.github.vanlaodev.androiddevlab1;

import com.github.vanlaodev.androiddevlab1.login.LoginActivity;
import com.github.vanlaodev.androiddevlab1.login.LoginModule;
import com.github.vanlaodev.androiddevlab1.main.MainActivity;
import com.github.vanlaodev.androiddevlab1.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity contributeLoginActivityInjector();

    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity contributeMainActivityInjector();

}
