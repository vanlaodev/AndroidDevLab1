package com.github.vanlaodev.daggerandroidlab1.login;

import com.github.vanlaodev.daggerandroidlab1.View;

public interface LoginView extends View {
    void setUsernameError();

    void setPasswordError();

    void showLoadingDialog();

    void hideLoadingDialog();

    void navigateToMainView();

    void showErrorMessage(String message);

    void showLoginCanceledMessage();
}
