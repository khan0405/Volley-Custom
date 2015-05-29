package com.android.volley.custom;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by KHAN on 2015-05-28.
 */
public abstract class CRequest<T> extends Request<T> {

    public CRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    public CRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }


    public HttpEntity getBodyEntity() throws AuthFailureError {
        return null;
    }

    public boolean hasBody() throws AuthFailureError {
        Map<String, String> params = getParams();
        return (params != null && !params.isEmpty()) || getBodyEntity() != null;
    }

    public void writeBody(OutputStream out) throws IOException, AuthFailureError {
        if (getBodyEntity() != null) {
            getBodyEntity().writeTo(out);
        }
        else if (getBody() != null) {
            out.write(getBody());
        }
        else {
            return;
        }
        out.flush();
        out.close();
    }

}
