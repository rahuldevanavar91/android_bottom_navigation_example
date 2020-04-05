package com.manash.purplle.myapplication.nework;

public class VolleyResponse {
    private String response;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    private VolleyResponse(Status status) {
        this.status = status;
    }


    private VolleyResponse(Status status, String response) {
        this.response = response;
        this.status = status;
    }

    public static VolleyResponse setLoading() {
        return new VolleyResponse(Status.LOADING);
    }

    public static VolleyResponse setReponse(Status status, String response) {
        return new VolleyResponse(status, response);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

