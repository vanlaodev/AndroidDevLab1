package com.github.vanlaodev.daggerandroidlab1.login;

import dagger.Binds;
import dagger.Module;

@Module
public interface LoginModule {

    @Binds
    LoginView bindView(LoginActivity loginActivity);

    @Binds
    LoginPresenter bindPresenter(LoginPresenterImpl loginPresenter);
}
