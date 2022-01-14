package com.example.latihan3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewHolderSaya> {

    private Context context;
    private ArrayList music_id, music_titleTEXT, music_artistTEXT, music_albumTEXT, music_genreTEXT,
            music_year_releaseINTEGER;


    BukuAdapter(

            Context context,
            ArrayList music_id,
            ArrayList music_titleTEXT,
            ArrayList music_artistTEXT,
            ArrayList music_albumTEXT,
            ArrayList music_genreTEXT,
            ArrayList music_year_releaseINTEGER
    ){
            this.context = context;
            this.music_id = music_id;
            this.music_titleTEXT = music_titleTEXT;
            this.music_artistTEXT = music_artistTEXT;
            this.music_albumTEXT = music_albumTEXT;
            this.music_genreTEXT = music_genreTEXT;
            this.music_year_releaseINTEGER = music_year_releaseINTEGER;

    }

    @NonNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View viewKita = inflaterKita.inflate(R.layout.row_saya, parent, false);
        return new ViewHolderSaya(viewKita);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSaya holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_id_music.setText(String.valueOf(music_id.get(position)));
        holder.txt_title_music.setText(String.valueOf(music_titleTEXT.get(position)));
        holder.txt_artist_music.setText(String.valueOf(music_artistTEXT.get(position)));
        holder.txt_album_music.setText(String.valueOf(music_albumTEXT.get(position)));
        holder.txt_genre_music.setText(String.valueOf(music_genreTEXT.get(position)));
        holder.txt_year_release_music.setText(String.valueOf(music_year_releaseINTEGER.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentKita = new Intent(context, UbahMusicActivity.class);

                intentKita.putExtra("id", String.valueOf(music_id.get(position)));
                intentKita.putExtra("title", String.valueOf(music_titleTEXT.get(position)));
                intentKita.putExtra("artist", String.valueOf(music_artistTEXT.get(position)));
                intentKita.putExtra("album", String.valueOf(music_albumTEXT.get(position)));
                intentKita.putExtra("genre", String.valueOf(music_genreTEXT.get(position)));
                intentKita.putExtra("year_release", String.valueOf(music_year_releaseINTEGER.get(position)));

                context.startActivity(intentKita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return music_id.size();
    }


    public class ViewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_id_music, txt_title_music, txt_artist_music, txt_album_music, txt_genre_music, txt_year_release_music;
        LinearLayout layoutUtama;

        public ViewHolderSaya(@NonNull View itemView) {
            super(itemView);

            txt_id_music            = itemView.findViewById(R.id.txt_music_id);
            txt_title_music         = itemView.findViewById(R.id.txt_music_title);
            txt_artist_music        = itemView.findViewById(R.id.txt_music_artist);
            txt_album_music         = itemView.findViewById(R.id.txt_music_album);
            txt_genre_music         = itemView.findViewById(R.id.txt_music_genre);
            txt_year_release_music   = itemView.findViewById(R.id.txt_music_year_release);
            layoutUtama             = itemView.findViewById(R.id.layout_utama);

        }
    }
}
