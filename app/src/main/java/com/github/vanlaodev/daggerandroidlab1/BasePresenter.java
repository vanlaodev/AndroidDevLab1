package com.github.vanlaodev.daggerandroidlab1;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T extends View> implements Presenter {

    private WeakReference<T> view;

    @Override
    public void bindView(View view) {
        this.view = new WeakReference<T>((T) view);
    }

    @Override
    public void unbindView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }

    protected T getView() {
        return view != null ? view.get() : null;
    }
}
