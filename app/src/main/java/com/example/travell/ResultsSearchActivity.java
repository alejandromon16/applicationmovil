package com.example.travell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultsSearchActivity extends AppCompatActivity implements ResultAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ResultAdapter adapter;

    private Button backButton;

    private TextView from;
    private TextView to;

    private TextView offerCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_search);

        Intent intent = getIntent();
        String origin = intent.getStringExtra("origin");
        String destination = intent.getStringExtra("destination");

        from = findViewById(R.id.textView_from);
        to = findViewById(R.id.textView_to);

        from.setText(origin);
        to.setText(destination);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<ResultItem> dummyData = createDummyData(origin, destination);

        offerCount = findViewById(R.id.textView_offer_count);

        adapter = new ResultAdapter(dummyData);
        adapter.setOnItemClickListener(this);

        offerCount.setText(String.valueOf(adapter.getItemCount())+" Ofertas encontradas");
        recyclerView.setAdapter(adapter);

        backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsSearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(int itemId ) {
        // Handle the item click event and navigate to SeatsSelectionActivity
        Intent intent = new Intent(this, SeatsSelectionActivity.class);
        intent.putExtra("itemId", itemId);
        startActivity(intent);
    }

    private List<ResultItem> createDummyData(String origin, String destination) {
        // Create the dummy data list
        List<ResultItem> dummyData = new ArrayList<>();

        // Add dummy result items to the list
        dummyData.add(new ResultItem(
                "10:00",
                "14:00",
                100,
                "Quijarreno",
                "url",
                "Normal",
                120,
                "Santa Cruz de la Sierra",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "10:00",
                "14:00",
                100,
                "Quijarreno",
                "url",
                "Normal",
                120,
                "Santa Cruz de la Sierra",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "11:00",
                "15:00",
                120,
                "Another Bus",
                "url",
                "Normal",
                110,
                "Santa Cruz de la Sierra",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "12:00",
                "16:00",
                90,
                "Yet Another Bus",
                "url",
                "Normal",
                150,
                "Santa Cruz de la Sierra",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "10:00",
                "14:00",
                100,
                "Quijarreno",
                "url",
                "Normal",
                120,
                "La Paz",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "11:00",
                "15:00",
                120,
                "Another Bus",
                "url",
                "Normal",
                110,
                "La Paz",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "12:00",
                "16:00",
                90,
                "Yet Another Bus",
                "url",
                "Normal",
                150,
                "La Paz",
                "Cochabamba"
        ));

        dummyData.add(new ResultItem(
                "10:00",
                "14:00",
                100,
                "Quijarreno",
                "url",
                "Normal",
                120,
                "Cochabamba",
                "La Paz"
        ));

        dummyData.add(new ResultItem(
                "11:00",
                "15:00",
                120,
                "Another Bus",
                "url",
                "Normal",
                110,
                "Cochabamba",
                "La Paz"
        ));

        dummyData.add(new ResultItem(
                "12:00",
                "16:00",
                90,
                "Yet Another Bus",
                "url",
                "Normal",
                150,
                "Cochabamba",
                "La Paz"
        ));

        // Filter the dummy data based on origin and destination
        List<ResultItem> filteredData = new ArrayList<>();
        for (ResultItem item : dummyData) {
            if (item.getOrigin().equals(origin) && item.getDestination().equals(destination)) {
                filteredData.add(item);
            }
        }

        return filteredData;
    }
}