package com.example.animedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animedex.Anime;
import com.example.animedex.R;

import java.util.List;

// Adapter for RecyclerView to display anime titles
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private Context context;// Needed for inflating layout
    private List<Anime> animeList;   // List of anime data

    // Constructor
    public AnimeAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    // Called when RecyclerView needs to create a new item view
    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the card layout (anime_card.xml) and return a ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.anime_card, parent, false);
        return new AnimeViewHolder(view);
    }

    // Called to display data for a specific position in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);  // Get anime at this position

        // Set the title in the TextView
        holder.title.setText(anime.getTitle());

        // ImageView is present in layout but we are NOT loading any image for now
        // Later we can use Glide/other libraries to load images
    }

    // Returns the total number of items
    @Override
    public int getItemCount() {
        return animeList.size();
    }

    // ViewHolder class: stores references to views for each item
    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        TextView title;  // TextView to show anime title

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the TextView in the layout
            title = itemView.findViewById(R.id.cardTitle);

            // The ImageView is ignored for now
            // ImageView image = itemView.findViewById(R.id.cardImage);
        }
    }
}
