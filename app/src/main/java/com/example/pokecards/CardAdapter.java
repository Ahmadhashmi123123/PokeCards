package com.example.pokecards;

import android.content.Context;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private List<Card> cardList;
    private boolean showFavButton; // true = Home section, false = Favourites section

    public CardAdapter(Context context, List<Card> cardList, boolean showFavButton) {
        this.context = context;
        this.cardList = cardList;
        this.showFavButton = showFavButton;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cardList.get(position);

        holder.name.setText(card.getName());

        Glide.with(context)
                .load(card.getImage())
                .centerCrop()
                .into(holder.image);

        // Click to enlarge image
        holder.itemView.setOnClickListener(v -> {
            showImageDialog(context, card.getImage());
        });

        // Show or hide favourite button based on section
        if (showFavButton) {
            holder.favButton.setVisibility(View.VISIBLE);
            holder.favButton.setOnClickListener(v -> {
                FavouriteManager favManager = FavouriteManager.getInstance();
                if (!favManager.isFavourite(card)) {
                    favManager.addToFavourites(card);
                    Toast.makeText(context, card.getName() + " added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, card.getName() + " is already in favourites", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.favButton.setVisibility(View.GONE); // Hide in favourites section
        }
    }

    private void showImageDialog(Context context, String imageUrl) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_image);

        ImageView imageView = dialog.findViewById(R.id.dialog_image_view);

        Glide.with(context)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);

        imageView.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public void updateList(List<Card> newList) {
        cardList = newList;
        notifyDataSetChanged();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        ImageView favButton; // Add this in card_item.xml

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_name);
            image = itemView.findViewById(R.id.card_image);
            favButton = itemView.findViewById(R.id.card_fav_button); // ⚠️ ye id card_item.xml me add karo
        }
    }
}
