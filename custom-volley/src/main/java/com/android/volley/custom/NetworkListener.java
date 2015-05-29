package com.android.volley.custom;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public interface NetworkListener<T> extends Listener<T>, ErrorListener {
	void loadStart();
	void onSuccess(T response);
	void onFailure(VolleyError error);
	void loadComplete();
}
