package com.android.volley.custom;

import com.android.volley.VolleyError;

public abstract class AbsNetworkListener<T> implements NetworkListener<T> {
	private NetworkListener<T> listener;
	
	public AbsNetworkListener() {}
	
	public AbsNetworkListener(NetworkListener<T> listener) {
		this.listener = listener;
	}
	
	protected NetworkListener<T> getListener() {
		return listener;
	}
	
	@Override
	public final void onResponse(T response) {
		onSuccess(response);
		loadComplete();
		if (listener != null) {
			listener.onResponse(response);
		}
	}
	
	@Override
	public final void onErrorResponse(VolleyError error) {
		onFailure(error);
		loadComplete();
		if (listener != null) {
			listener.onErrorResponse(error);
		}
	}
}
