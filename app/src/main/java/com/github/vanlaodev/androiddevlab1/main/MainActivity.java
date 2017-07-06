package com.github.vanlaodev.androiddevlab1.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.vanlaodev.androiddevlab1.AuthenticatedActivity;
import com.github.vanlaodev.androiddevlab1.R;
import com.github.vanlaodev.androiddevlab1.login.LoginActivity;

import butterknife.OnClick;

public class MainActivity extends AuthenticatedActivity<MainPresenter> implements MainView {
    public static Intent intent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btn_logout)
    void logout() {
        presenter.logout();
    }

    @Override
    public void navigateToLoginView() {
        startActivity(LoginActivity.intent(this));
    }
}
