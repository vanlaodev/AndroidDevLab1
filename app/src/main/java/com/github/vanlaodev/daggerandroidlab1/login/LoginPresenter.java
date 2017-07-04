package com.github.vanlaodev.daggerandroidlab1.login;

import com.github.vanlaodev.daggerandroidlab1.Presenter;

public interface LoginPresenter extends Presenter {
    void login(String username, String password);

    void onLoginLoadingDialogCanceled();
}
