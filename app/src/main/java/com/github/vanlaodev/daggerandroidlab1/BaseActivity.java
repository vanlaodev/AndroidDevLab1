package com.github.vanlaodev.daggerandroidlab1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends Presenter> extends DaggerAppCompatActivity implements View {

    public static final String KEY_PRESENTER = "presenter";
    private static final String TAG_LIFECYCLE_DATA_HOLDER = "lifecycle_data_holder";
    @Inject
    protected T presenter;

    private LifecycleDataHolder lifecycleDataHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        lifecycleDataHolder = (LifecycleDataHolder) fm.findFragmentByTag(TAG_LIFECYCLE_DATA_HOLDER);
        if (lifecycleDataHolder == null) {
            lifecycleDataHolder = new LifecycleDataHolder();
            fm.beginTransaction().add(lifecycleDataHolder, TAG_LIFECYCLE_DATA_HOLDER).commit();
        }
        T savedPresenter = (T) lifecycleDataHolder.getData(KEY_PRESENTER);
        if (savedPresenter != null) {
            presenter = savedPresenter;
        } else {
            lifecycleDataHolder.putData(KEY_PRESENTER, presenter);
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
