package com.android.volley.custom;

import com.android.volley.VolleyError;

/**
 * Created by KHAN on 2015-05-29.
 */
public class SimpleNetworkListener<T> extends AbsNetworkListener<T> {

    public SimpleNetworkListener() {}

    public SimpleNetworkListener(NetworkListener<T> listener) {
        super(listener);
    }

    @Override
    public void loadStart() {
        // do nothing...
    }

    @Override
    public void onSuccess(T response) {
        // do nothing...
    }

    @Override
    public void onFailure(VolleyError error) {
        // do nothing...
    }

    @Override
    public void loadComplete() {
        // do nothing...
    }
}
