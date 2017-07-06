package com.github.vanlaodev.androiddevlab1.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.github.vanlaodev.androiddevlab1.BaseActivity;
import com.github.vanlaodev.androiddevlab1.LoadingDialogFragment;
import com.github.vanlaodev.androiddevlab1.R;
import com.github.vanlaodev.androiddevlab1.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) getSupportFragmentManager().findFragmentByTag("loading_dialog");
        if (loadingDialogFragment != null) {
            loadingDialogFragment.setCallback(new LoadingDialogFragment.Callback() {
                @Override
                public void onCancelled() {
                    super.onCancelled();
                    presenter.onLoginLoadingDialogCanceled();
                }
            });
        }
    }

    @OnClick(R.id.btn_login)
    void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        presenter.login(username, password);
    }

    @Override
    public void setUsernameError() {
        etUsername.setError(getString(R.string.required));
    }

    @Override
    public void setPasswordError() {
        etPassword.setError(getString(R.string.required));
    }

    @Override
    public void showLoadingDialog() {
        hideLoadingDialog();
        LoadingDialogFragment fragment = LoadingDialogFragment.create();
        fragment.setCallback(new LoadingDialogFragment.Callback() {
            @Override
            public void onCancelled() {
                super.onCancelled();
                presenter.onLoginLoadingDialogCanceled();
            }
        });
        fragment.show(getSupportFragmentManager(), "loading_dialog");
    }

    @Override
    public void hideLoadingDialog() {
        LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) getSupportFragmentManager().findFragmentByTag("loading_dialog");
        if (loadingDialogFragment != null) {
            loadingDialogFragment.dismissAllowingStateLoss();
        }
    }

    @Override
    public void navigateToMainView() {
        startActivity(MainActivity.intent(this));
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginCanceledMessage() {
        Toast.makeText(this, R.string.canceled, Toast.LENGTH_SHORT).show();
    }

    public static Intent intent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }
}
