package com.github.vanlaodev.androiddevlab1.main;

import com.github.vanlaodev.androiddevlab1.BasePresenter;
import com.github.vanlaodev.androiddevlab1.UserDataStore;

import javax.inject.Inject;

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    private final UserDataStore userDataStore;

    @Inject
    public MainPresenterImpl(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    @Override
    public void logout() {
        userDataStore.setLoggedInUser(null);
        MainView view = getView();
        if (view != null) {
            view.navigateToLoginView();
        }
    }
}
