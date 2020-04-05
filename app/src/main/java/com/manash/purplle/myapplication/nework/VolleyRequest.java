package com.manash.purplle.myapplication.nework;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class VolleyRequest {
    private static VolleyRequest instance;
    private static RequestQueue queue;

    private static final String URL = "https://qa-doctor.medcords.com/mhc/test_handle_click";

    private VolleyRequest(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static VolleyRequest getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyRequest(context);
        }
        return instance;
    }

    public static void postRequest(final String param, final NetworkResponseListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message;
                try {
                    message = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                } catch (UnsupportedEncodingException e) {
                    message = e.getMessage();
                }
                listener.onError(message);
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() {
                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("bname", param);
                    String requestBody = jsonBody.toString();
                    return requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        queue.add(stringRequest);

    }

}
