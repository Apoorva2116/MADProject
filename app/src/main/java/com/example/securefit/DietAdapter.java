package com.example.securefit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.securefit.Diet;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {

    private List<Diet> dietList;

    public DietAdapter(List<Diet> dietList) {
        this.dietList = dietList;
    }

    @NonNull
    @Override
    public DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diet_card, parent, false);
        return new DietViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DietViewHolder holder, int position) {
        Diet diet = dietList.get(position);
        holder.dietName.setText(diet.getName());
        holder.dietCalories.setText(diet.getCalories() + " Calories");
        holder.dietRating.setText("★ " + diet.getRating());
        holder.dietImage.setImageResource(diet.getImageResId());
    }

    @Override
    public int getItemCount() {
        return dietList.size();
    }

    static class DietViewHolder extends RecyclerView.ViewHolder {
        TextView dietName, dietCalories, dietRating;
        ImageView dietImage;

        public DietViewHolder(@NonNull View itemView) {
            super(itemView);
            dietName = itemView.findViewById(R.id.dietName);
            dietCalories = itemView.findViewById(R.id.dietCalories);
            dietRating = itemView.findViewById(R.id.dietRating);
            dietImage = itemView.findViewById(R.id.dietImage);
        }
    }
}
