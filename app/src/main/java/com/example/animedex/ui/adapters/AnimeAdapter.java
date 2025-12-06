package com.example.animedex.ui.adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animedex.R;
import com.example.animedex.data.model.Anime;

import java.util.List;
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private Context context;
    private List<Anime> animeList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Anime anime);
    }
    public AnimeAdapter(Context context, List<Anime> animeList, OnItemClickListener listener) {
        this.context = context;
        this.animeList = animeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.anime_card, parent, false);
        return new AnimeViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(anime);
                }
            }
        });
        holder.title.setText(anime.getTitle());
        Glide.with(context)
                .load(anime.getImage())
                .placeholder(R.color.dark_gray)
                .error(R.color.dark_gray)
                .into(holder.image);
        Log.d("Glide", "Loading image URL: " + anime.getImage());
    }
    @Override
    public int getItemCount() {
        return animeList.size();
    }
    static class AnimeViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardTitle);
            image = itemView.findViewById(R.id.cardImage);
        }
    }
}
