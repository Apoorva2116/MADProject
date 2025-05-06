package com.example.securefit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {
    Context context;
    List<Diet> dietList;

    public DietAdapter(Context context, List<Diet> dietList) {
        this.context = context;
        this.dietList = dietList;
    }

    public void updateDietList(List<Diet> newList) {
        this.dietList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_diet_card, parent, false);
        return new DietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DietViewHolder holder, int position) {
        Diet diet = dietList.get(position);
        holder.mealName.setText(diet.name);
        holder.mealCalories.setText(diet.calories + " kcal");
        holder.mealRating.setText("⭐ " + diet.rating);
        holder.imageView.setImageResource(diet.imageResId);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MealDetailActivity.class);
            intent.putExtra("name", diet.name);
            intent.putExtra("calories", diet.calories);
            intent.putExtra("rating", diet.rating);
            intent.putExtra("image", diet.imageResId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dietList.size();
    }

    static class DietViewHolder extends RecyclerView.ViewHolder {
        TextView mealName, mealCalories, mealRating;
        ImageView imageView;

        public DietViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            mealCalories = itemView.findViewById(R.id.mealCalories);
            mealRating = itemView.findViewById(R.id.mealRating);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
