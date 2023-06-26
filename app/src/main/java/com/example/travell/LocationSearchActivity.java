package com.example.travell;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationSearchActivity extends AppCompatActivity implements LocationAdapter.OnItemClickListener {

    private List<String> locationList;
    private LocationAdapter locationAdapter;
    private EditText searchEditText;
    private ImageButton backButton;

    private String[] cityNames = {
            "Santa Cruz de la Sierra",
            "La Paz",
            "Cochabamba",
            "Sucre",
            "Potosí",
            "Oruro",
            "Tarija",
            "Sacaba",
            "Montero",
            "Trinidad"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationSearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Dummy location list for demonstration purposes
        locationList = new ArrayList<>(Arrays.asList(cityNames));

        // Initialize the RecyclerView and set its layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerview_locations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the LocationAdapter and set it on the RecyclerView
        locationAdapter = new LocationAdapter(locationList);
        recyclerView.setAdapter(locationAdapter);
        locationAdapter.setOnItemClickListener(this);
        // Initialize the search EditText
        searchEditText = findViewById(R.id.edittext_search);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used in this case
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Filter the location list based on the entered text
                locationAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used in this case
            }

        });

    }

    @Override
    public void onItemClick(String location) {
        // Pass the selected location back to the MainActivity
        Intent intent = new Intent();
        intent.putExtra("selectedLocation", location);
        setResult(RESULT_OK, intent);
        finish();
    }
}
