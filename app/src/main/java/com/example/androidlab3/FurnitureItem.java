package com.example.androidlab3;

public class FurnitureItem {
    private String name;
    private String category;
    private int cost;
    private int imageResource;
    private int quantity;
    private boolean selected;

    public FurnitureItem(String name, String category, int cost, int imageResource) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.imageResource = imageResource;
        this.quantity = 1;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getCost() {
        return cost;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
