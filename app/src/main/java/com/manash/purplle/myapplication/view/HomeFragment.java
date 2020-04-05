package com.manash.purplle.myapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manash.purplle.myapplication.R;
import com.manash.purplle.myapplication.adapter.HomeHorizontalRecyclerAdapter;
import com.manash.purplle.myapplication.nework.VolleyResponse;
import com.manash.purplle.myapplication.viewModel.HomeFragmentViewModel;

public class HomeFragment extends Fragment implements View.OnClickListener, Observer<VolleyResponse> {

    private HomeFragmentViewModel homeViewModel;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        homeViewModel.getResultData().observe(getViewLifecycleOwner(), this);

        initView(view);
        return view;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.home_horizontal_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new HomeHorizontalRecyclerAdapter(getContext()));
        progressBar = view.findViewById(R.id.progress);
        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
                homeViewModel.getResponse(((Button) v).getText().toString());
                break;

        }

    }

    @Override
    public void onChanged(VolleyResponse response) {
        switch (response.getStatus()) {
            case ERROR:
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), response.getResponse(), Toast.LENGTH_LONG).show();
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                break;

        }
    }
}
