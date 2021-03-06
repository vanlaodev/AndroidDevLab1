package com.github.vanlaodev.androiddevlab1;

import com.github.vanlaodev.androiddevlab1.login.LoginActivity;

import javax.inject.Inject;

public abstract class AuthenticatedActivity<T extends Presenter> extends BaseActivity<T> {

    @Inject
    UserDataStore userDataStore;

    @Override
    protected void onResume() {
        super.onResume();
        if (userDataStore.getLoggedInUser() == null) {
            startActivity(LoginActivity.intent(this));
        }
    }
}
