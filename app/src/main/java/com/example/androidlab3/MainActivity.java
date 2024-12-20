package com.example.androidlab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FurnitureAdapter furnitureAdapter;
    private Button calculateButton;
    private Spinner categoryFilter;
    private List<FurnitureItem> furnitureItems;
    private List<FurnitureItem> filteredItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        calculateButton = findViewById(R.id.calculateButton);
        categoryFilter = findViewById(R.id.categoryFilter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize furniture items
        furnitureItems = new ArrayList<>();
        furnitureItems.add(new FurnitureItem("Стол обеденный", "стол", 5000, R.drawable.table));
        furnitureItems.add(new FurnitureItem("Стул офисный", "стул", 2000, R.drawable.chair));
        furnitureItems.add(new FurnitureItem("Шкаф для одежды", "шкаф", 12000, R.drawable.wardrobe));
        furnitureItems.add(new FurnitureItem("Стол письменный", "стол", 7000, R.drawable.desk));
        furnitureItems.add(new FurnitureItem("Стул деревянный", "стул", 2500, R.drawable.wooden_chair));
        furnitureItems.add(new FurnitureItem("Шкаф книжный", "шкаф", 8000, R.drawable.bookshelf));

        filteredItems = new ArrayList<>(furnitureItems);

        furnitureAdapter = new FurnitureAdapter(filteredItems);
        recyclerView.setAdapter(furnitureAdapter);

        // Set up category filter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryFilter.setAdapter(adapter);

        categoryFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                filterItemsByCategory(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filterItemsByCategory("Все категории");
            }
        });

        // Calculate total cost
        calculateButton.setOnClickListener(v -> {
            int totalCost = 0;
            List<FurnitureItem> selectedItems = new ArrayList<>();
            for (FurnitureItem item : furnitureItems) {
                if (item.isSelected()) {
                    totalCost += item.getCost() * item.getQuantity();
                    selectedItems.add(item);
                }
            }
            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Выберите хотя бы один товар.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("totalCost", totalCost);
            startActivity(intent);
        });
    }

    private void filterItemsByCategory(String category) {
        filteredItems.clear();
        if (category.equals("Все категории")) {
            filteredItems.addAll(furnitureItems);
        } else {
            for (FurnitureItem item : furnitureItems) {
                if (item.getCategory().equalsIgnoreCase(category)) {
                    filteredItems.add(item);
                }
            }
        }
        furnitureAdapter.notifyDataSetChanged();
    }
}
