package com.example.travell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int ORIGIN_REQUEST_CODE = 1;
    private static final int DESTINATION_REQUEST_CODE = 2;

    private Button originButton;
    private Button destinationButton;

    private String origin;
    private String destination;

    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originButton = findViewById(R.id.button_origin);
        destinationButton = findViewById(R.id.button_destination);
        searchButton = findViewById(R.id.button_search);

        originButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LocationSearchActivity with request code for origin selection
                Intent intent = new Intent(MainActivity.this, LocationSearchActivity.class);
                startActivityForResult(intent, ORIGIN_REQUEST_CODE);
            }
        });

        destinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LocationSearchActivity with request code for destination selection
                Intent intent = new Intent(MainActivity.this, LocationSearchActivity.class);
                startActivityForResult(intent, DESTINATION_REQUEST_CODE);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultsSearchActivity.class);
                intent.putExtra("origin", origin);
                intent.putExtra("destination", destination);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == ORIGIN_REQUEST_CODE) {
                // Handle the origin location selection
                String selectedOrigin = data.getStringExtra("selectedLocation");
                originButton.setText(selectedOrigin);
                this.origin = selectedOrigin;
            } else if (requestCode == DESTINATION_REQUEST_CODE) {
                // Handle the destination location selection
                String selectedDestination = data.getStringExtra("selectedLocation");
                destinationButton.setText(selectedDestination);
                this.destination = selectedDestination;
            }
        }
    }
}
