package com.github.vanlaodev.androiddevlab1;

public interface Presenter {

    void bindView(View view);

    void unbindView();

    void onDestory();
}
