package com.example.animedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DashboardAnimeAdapter extends RecyclerView.Adapter<DashboardAnimeAdapter.AnimeViewHolder> {

    private Context context;
    private List<Anime> animeList;

    public DashboardAnimeAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.anime_card_dashboard, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);

        // Load image if available
        if (anime.getImage() != null && !anime.getImage().isEmpty()) {
            Glide.with(context)
                    .load(anime.getImage())
                    .centerCrop()
                    .into(holder.ivAnimeImage);
        }

        // Set title and episodes
        holder.tvTitle.setText(anime.getTitle());
        holder.tvEpisodes.setText("Episodes: " + (anime.getEpisodes() != null ? anime.getEpisodes() : "--"));
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAnimeImage;
        TextView tvTitle, tvEpisodes;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnimeImage = itemView.findViewById(R.id.ivAnimeImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvEpisodes = itemView.findViewById(R.id.tvEpisodes);
        }
    }
}
