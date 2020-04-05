package com.manash.purplle.myapplication.nework;

public interface NetworkResponseListener {
    void onSuccess(String response);

    void onError(String error);
}
