package com.example.travell;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<String> locationList;
    private List<String> filteredList;
    private OnItemClickListener itemClickListener;

    public LocationAdapter(List<String> locationList) {
        this.locationList = locationList;
        Collections.sort(this.locationList);
        this.filteredList = new ArrayList<>(locationList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String location = filteredList.get(position);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String searchText) {
        filteredList.clear();

        if (searchText.isEmpty()) {
            filteredList.addAll(locationList);
        } else {
            for (String location : locationList) {
                if (location.toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(location);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(String location);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLocation = itemView.findViewById(R.id.textview_location);
            itemView.setOnClickListener(this);
        }

        public void bind(String location) {
            textViewLocation.setText(location);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                String location = filteredList.get(position);
                itemClickListener.onItemClick(location);
            }
        }
    }
}


