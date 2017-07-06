package com.github.vanlaodev.androiddevlab1.login;

import com.github.vanlaodev.androiddevlab1.Presenter;

public interface LoginPresenter extends Presenter {
    void login(String username, String password);

    void onLoginLoadingDialogCanceled();
}
