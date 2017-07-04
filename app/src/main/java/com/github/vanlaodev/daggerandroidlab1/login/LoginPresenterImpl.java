package com.github.vanlaodev.daggerandroidlab1.login;

import com.github.vanlaodev.daggerandroidlab1.BasePresenter;
import com.github.vanlaodev.daggerandroidlab1.UserDataStore;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl extends BasePresenter<LoginView> implements LoginPresenter {

    private Disposable loginDisposable;
    private final UserDataStore userDataStore;

    @Inject
    public LoginPresenterImpl(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    @Override
    public void login(final String username, final String password) {
        LoginView loginView = getView();
        if (loginView != null) {
            if (username.isEmpty()) {
                loginView.setUsernameError();
            }
            if (password.isEmpty()) {
                loginView.setPasswordError();
            }
            if (!username.isEmpty() && !password.isEmpty()) {
                loginView.showLoadingDialog();
                loginDisposable = Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        Thread.sleep(3000);
                        if (!username.equals(password)) {
                            throw new Exception("Failed to login.");
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        userDataStore.setLoggedInUser(username);
                        LoginView loginView = getView();
                        if (loginView != null) {
                            loginView.hideLoadingDialog();
                            loginView.navigateToMainView();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        LoginView loginView = getView();
                        if (loginView != null) {
                            loginView.hideLoadingDialog();
                            loginView.showErrorMessage(throwable.getMessage());
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onLoginLoadingDialogCanceled() {
        if (loginDisposable != null) {
            loginDisposable.dispose();
            LoginView loginView = getView();
            if (loginView != null) {
                loginView.showLoginCanceledMessage();
            }
        }
    }
}
