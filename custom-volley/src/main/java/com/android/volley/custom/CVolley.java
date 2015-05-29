package com.android.volley.custom;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.ExecutorDelivery;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.*;

import java.io.File;

/**
 * Created by KHAN on 2015-05-29.
 */
public class CVolley {
    /** Default on-disk cache directory. */
    private static final String DEFAULT_CACHE_DIR = "volley";

    public static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;

    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link Context} to use for creating the cache dir.
     * @param stack An {@link HttpStack} to use for the network, or null for default.
     * @return A started {@link RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context, HttpStack stack, int threadPoolSize, ResponseDelivery delivery) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                stack = new CHurlStack();
            } else {
                String userAgent = "volley/0";
                try {
                    String packageName = context.getPackageName();
                    PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
                    userAgent = packageName + "/" + info.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                }
                // Prior to Gingerbread, HttpUrlConnection was unreliable.
                // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
                stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
            }
        }

        Network network = new BasicNetwork(stack);

        if (threadPoolSize < 1) {
            threadPoolSize = DEFAULT_NETWORK_THREAD_POOL_SIZE;
        }
        if (delivery == null) {
            delivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
        }
        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network, threadPoolSize, delivery);
        queue.start();

        return queue;
    }

    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link Context} to use for creating the cache dir.
     * @return A started {@link RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, new CHurlStack(), 0, null);
    }

    public static RequestQueue newRequestQueue(Context context, int threadPoolSize) {
        return newRequestQueue(context, new CHurlStack(), threadPoolSize, null);
    }

    public static RequestQueue newRequestQueue(Context context, ResponseDelivery delivery) {
        return newRequestQueue(context, new CHurlStack(), 0, delivery);
    }

    public static RequestQueue newRequestQueue(Context context, int threadPoolSize, ResponseDelivery delivery) {
        return newRequestQueue(context, new CHurlStack(), threadPoolSize, delivery);
    }
}
