package com.github.vanlaodev.daggerandroidlab1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class LifecycleDataHolder extends Fragment {

    private final Map<String, Object> data = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void putData(String key, Object value) {
        synchronized (data) {
            data.put(key, value);
        }
    }

    public Object getData(String key) {
        return data.containsKey(key) ? data.get(key) : null;
    }
}
