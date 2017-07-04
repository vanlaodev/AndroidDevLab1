package com.github.vanlaodev.daggerandroidlab1;

public interface Presenter {

    void bindView(View view);

    void unbindView();

    void onDestory();
}
