package com.example.securefit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.MealItemViewHolder> {

    private List<MealItem> originalList;
    private List<MealItem> filteredList;

    public MealItemAdapter(List<MealItem> itemList) {
        this.originalList = itemList;
        this.filteredList = new ArrayList<>(itemList);
    }

    public void filter(String query) {
        filteredList.clear();
        for (MealItem item : originalList) {
            if (item.name.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public void resetSelection() {
        for (MealItem item : originalList) {
            item.isSelected = false;
            item.totalCalories = 0;
        }
        notifyDataSetChanged();
    }

    public int calculateTotalCalories() {
        int total = 0;
        for (MealItem item : originalList) {
            if (item.isSelected) {
                total += item.totalCalories;
            }
        }
        return total;
    }

    public void markItemSelected(String foodName, int totalCalories) {
        for (MealItem item : originalList) {
            if (item.name.equalsIgnoreCase(foodName)) {
                item.isSelected = true;
                item.totalCalories = totalCalories;
                break;
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MealItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal_checkbox, parent, false);
        return new MealItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealItemViewHolder holder, int position) {
        MealItem item = filteredList.get(position);
        holder.foodName.setText(item.name + " (" + item.calories + " cal)");
        holder.checkBox.setChecked(item.isSelected);

        holder.checkBox.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, FoodQuantityActivity.class);
            intent.putExtra("FOOD_NAME", item.name);
            intent.putExtra("CALORIES_PER_UNIT", item.calories);
            ((MealItemsActivity) context).startActivityForResult(intent, 101);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    static class MealItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        CheckBox checkBox;

        public MealItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.textFoodName);
            checkBox = itemView.findViewById(R.id.checkboxFood);
        }
    }
}
