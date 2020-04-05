package com.manash.purplle.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manash.purplle.myapplication.R;
import com.manash.purplle.myapplication.util.Utils;

public class HomeHorizontalRecyclerAdapter extends RecyclerView.Adapter<HomeHorizontalRecyclerAdapter.ViewHolder> {

    private Context mContext;

    public HomeHorizontalRecyclerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_recycler_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item.setText("Position " + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            RecyclerView.LayoutParams layoutParmas = new RecyclerView.LayoutParams((int) (Utils.getDeviceWith(mContext) * .7), RecyclerView.LayoutParams.MATCH_PARENT);

            layoutParmas.rightMargin = (int) mContext.getResources().getDimension(R.dimen._10dp);
            itemView.setLayoutParams(layoutParmas);
        }
    }
}
