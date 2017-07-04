package com.github.vanlaodev.daggerandroidlab1.main;

import dagger.Binds;
import dagger.Module;

@Module
public interface MainModule {

    @Binds
    MainPresenter bindPresenter(MainPresenterImpl mainPresenter);

    @Binds
    MainView bindView(MainActivity mainActivity);

}
