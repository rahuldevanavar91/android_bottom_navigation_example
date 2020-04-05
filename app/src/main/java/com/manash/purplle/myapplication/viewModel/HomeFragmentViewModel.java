package com.manash.purplle.myapplication.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.manash.purplle.myapplication.nework.NetworkResponseListener;
import com.manash.purplle.myapplication.nework.Status;
import com.manash.purplle.myapplication.nework.VolleyRequest;
import com.manash.purplle.myapplication.nework.VolleyResponse;

public class HomeFragmentViewModel extends AndroidViewModel {


    private MutableLiveData<VolleyResponse> resultData = new MutableLiveData<>();
    private RequestQueue queue;
    private Context context;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        queue = Volley.newRequestQueue(context);

    }


    public MutableLiveData<VolleyResponse> getResultData() {
        return resultData;
    }

    public void getResponse(String button) {
        resultData.postValue(VolleyResponse.setLoading());
        VolleyRequest.getInstance(context).postRequest(button, new NetworkResponseListener() {
            @Override
            public void onSuccess(String response) {
                resultData.postValue(VolleyResponse.setReponse(Status.SUCCESS, response));
            }

            @Override
            public void onError(String error) {
                resultData.postValue(VolleyResponse.setReponse(Status.ERROR, error));

            }
        });
    }


}
