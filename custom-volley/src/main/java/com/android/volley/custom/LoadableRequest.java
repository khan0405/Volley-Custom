package com.android.volley.custom;


public interface LoadableRequest {
	void loadStart();
	void loadComplete();
	void putHeader(String key, String value);
	long getContentLength();
}
