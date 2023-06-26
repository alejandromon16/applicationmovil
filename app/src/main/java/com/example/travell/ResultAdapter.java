package com.example.travell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<ResultItem> results;

    private OnItemClickListener listener;

    private ImageButton journeySelectedButton;

    public ResultAdapter(List<ResultItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        ResultItem result = results.get(position);
        holder.bind(result);

        journeySelectedButton = holder.itemView.findViewById(R.id.button_journey_selected);

        journeySelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(result.getPrice());
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(result.getPrice());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int itemId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public static class ResultViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDepartureTime;
        private TextView textViewArrivalTime;
        private TextView textViewDuration;
        private TextView textViewCompany;
        private ImageView imageViewCompanyLogo;
        private TextView textViewSeatType;
        private TextView textViewPrice;
        private TextView textViewOrigin;
        private TextView textViewDestination;
        private OnItemClickListener listener;


        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDepartureTime = itemView.findViewById(R.id.textView_departure_time);
            textViewArrivalTime = itemView.findViewById(R.id.textView_arrival_time);
            textViewDuration = itemView.findViewById(R.id.textView_duration);
            imageViewCompanyLogo = itemView.findViewById(R.id.imageView_company_logo);
            textViewSeatType = itemView.findViewById(R.id.textView_seat_type);
            textViewPrice = itemView.findViewById(R.id.textView_price);
            textViewOrigin = itemView.findViewById(R.id.textView_origin);
            textViewDestination = itemView.findViewById(R.id.textView_destination);
        }




        public void bind(ResultItem result) {
            textViewDepartureTime.setText(result.getDepartureTime());
            textViewArrivalTime.setText(result.getArrivalTime());
            textViewDuration.setText(timeToString(result.getDurationAproxInMins()));
            // Set the company logo image using Picasso or any image loading library
            // imageViewCompanyLogo.setImageResource(result.getCompanyLogo());
            textViewSeatType.setText(result.getTypeOfSeat());
            textViewPrice.setText(String.valueOf(result.getPrice()));
            textViewOrigin.setText(result.getOrigin());
            textViewDestination.setText(result.getDestination());
        }

        public String timeToString(int timeInMins){
            int hours = timeInMins / 60;
            int mins = timeInMins % 60;

            return String.valueOf(hours) + " h " + String.valueOf(mins) + " mins ";
        }
    }
}
