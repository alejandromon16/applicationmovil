package com.example.travell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SeatsSelectionActivity extends AppCompatActivity {

    private int selectedSeatCount = 0;
    private Button continueToinfoButton;
    private int price;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats_selection);

        continueToinfoButton = findViewById(R.id.button_send_email);
        continueToinfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SeatsSelectionActivity.this , InfoPersonActivity.class);
                intent.putExtra("price", totalPrice );
                startActivity(intent);

            }
        });
        price = getIntent().getIntExtra("itemId", 0);
        // Retrieve the LinearLayout container for seats
        LinearLayout seatContainer = findViewById(R.id.linear_layout_seats);

        // Get the seat string input ("s n s s , s n s s")
        String seatString = "s n s s , s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s, s n s s";

        // Split the string by comma to get individual columns
        String[] seatColumns = seatString.split(",");
        int id = 1;
        // Loop through each column
        for (String column : seatColumns) {
            LinearLayout columnLayout = new LinearLayout(this);
            columnLayout.setOrientation(LinearLayout.HORIZONTAL);
            columnLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            columnLayout.setPadding(10, 20, 10, 20);

            // Split the column string by space to get individual seats
            String[] seats = column.trim().split("\\s+");

            // Loop through each seat in the column
            for (String seat : seats) {
                TextView seatView = new TextView(this);
                seatView.setText(String.valueOf(id++));
                seatView.setGravity(Gravity.CENTER);
                seatView.setElevation(2);
                seatView.setWidth(140);
                seatView.setHeight(140);
//                seatView.setPadding(50, 50, 50, 50);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(10, 0, 10, 0); // Adjust the margins as needed

                seatView.setLayoutParams(layoutParams);
                seatView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (seatView.isSelected()) {
                            seatView.setSelected(false);
                            seatView.setBackgroundResource(R.drawable.seat_available);
                            selectedSeatCount--;
                            totalPrice -= price;
                        } else {
                            seatView.setSelected(true);
                            seatView.setBackgroundResource(R.drawable.seat_selected);
                            selectedSeatCount++;
                            totalPrice += price;

                        }

                        // Update the selected seat count
                        updateSelectedSeatCount();
                        updateSeatCountContainerVisibility();
                    }
                });

                // Customize the seat view appearance based on the seat type
                if (seat.equals("s")) {
                    seatView.setBackgroundResource(R.drawable.seat_available);
                } else if (seat.equals("n")) {
                    seatView.setBackgroundColor(Color.TRANSPARENT);
                    seatView.setText(" ");
                }

                // Add the seat view to the column layout
                columnLayout.addView(seatView);
            }

            // Add the column layout to the seat container
            seatContainer.addView(columnLayout);
        }
    }

    private void updateSelectedSeatCount() {
        TextView seatCountTextView = findViewById(R.id.text_view_selected_seat_count);
        seatCountTextView.setText(String.valueOf(selectedSeatCount));

        TextView totalPriceView = findViewById(R.id.textView_total_price);
        totalPriceView.setText(String.valueOf(totalPrice) + "Bs");
    }

    private void updateSeatCountContainerVisibility() {
        LinearLayout seatCountContainer = findViewById(R.id.container_seat_count);

        if (selectedSeatCount > 0) {
            seatCountContainer.setVisibility(View.VISIBLE);
            seatCountContainer.setBackgroundColor(Color.WHITE);
        } else {
            seatCountContainer.setVisibility(View.GONE);
        }
    }

}
