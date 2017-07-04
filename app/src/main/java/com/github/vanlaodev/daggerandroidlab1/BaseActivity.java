package com.github.vanlaodev.daggerandroidlab1;

import android.os.Bundle;

import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends Presenter> extends DaggerAppCompatActivity implements View {

    private String id;

    @Inject
    Map<String, Presenter> presenterMap;

    @Inject
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            id = UUID.randomUUID().toString();
        } else {
            id = savedInstanceState.getString("id", UUID.randomUUID().toString());
        }
        if (!presenterMap.containsKey(id)) {
            presenterMap.put(id, presenter);
        } else {
            presenter = (T) presenterMap.get(id);
        }
        presenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("id", id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }
}
