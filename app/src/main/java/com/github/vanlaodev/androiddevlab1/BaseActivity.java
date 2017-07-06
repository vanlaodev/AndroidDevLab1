package com.github.vanlaodev.androiddevlab1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends Presenter> extends DaggerAppCompatActivity implements View {

    public static final String KEY_PRESENTER = "presenter";
    private static final String TAG_LIFECYCLE_DATA_HOLDER = "lifecycle_data_holder";
    @Inject
    Lazy<T> lazyPresenter;

    protected T presenter;

    private ActivityDataHolder activityDataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        activityDataHolder = (ActivityDataHolder) fm.findFragmentByTag(TAG_LIFECYCLE_DATA_HOLDER);
        if (activityDataHolder == null) {
            activityDataHolder = new ActivityDataHolder();
            fm.beginTransaction().add(activityDataHolder, TAG_LIFECYCLE_DATA_HOLDER).commit();
        }
        T savedPresenter = (T) activityDataHolder.getData(KEY_PRESENTER);
        if (savedPresenter != null) {
            presenter = savedPresenter;
        } else {
            presenter = lazyPresenter.get();
            activityDataHolder.putData(KEY_PRESENTER, presenter);
        }
        presenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        if (isFinishing()) {
            presenter.onDestory();
        }
        super.onDestroy();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
}
