package com.example.androidlab3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FurnitureAdapter extends RecyclerView.Adapter<FurnitureAdapter.FurnitureViewHolder> {

    private List<FurnitureItem> furnitureItems;

    public FurnitureAdapter(List<FurnitureItem> furnitureItems) {
        this.furnitureItems = furnitureItems;
    }

    @NonNull
    @Override
    public FurnitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_item, parent, false);
        return new FurnitureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureViewHolder holder, int position) {
        FurnitureItem item = furnitureItems.get(position);
        holder.nameTextView.setText(item.getName());
        holder.costTextView.setText(String.format("%d ₽", item.getCost()));
        holder.imageView.setImageResource(item.getImageResource());
        holder.quantityEditText.setText(String.valueOf(item.getQuantity()));
        holder.checkBox.setChecked(item.isSelected());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> item.setSelected(isChecked));
        holder.quantityEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                try {
                    int quantity = Integer.parseInt(holder.quantityEditText.getText().toString());
                    item.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    holder.quantityEditText.setText(String.valueOf(item.getQuantity()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return furnitureItems.size();
    }

    static class FurnitureViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView costTextView;
        ImageView imageView;
        EditText quantityEditText;
        CheckBox checkBox;

        public FurnitureViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            costTextView = itemView.findViewById(R.id.costTextView);
            imageView = itemView.findViewById(R.id.imageView);
            quantityEditText = itemView.findViewById(R.id.quantityEditText);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
