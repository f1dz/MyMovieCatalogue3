package com.lonecode.mymoviecatalogue3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private ArrayList<Movie> listMovie = new ArrayList<>();
    private Context context;
    private OnItemClickCallback onItemClickCallback;

    public ListMovieAdapter(Context context) {
        this.context = context;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

//    public ListMovieAdapter(ArrayList<Movie> list) {
//        this.listMovie = list;
//    }

    public void setData(ArrayList<Movie> movies) {
        listMovie.clear();
        listMovie.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movie, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
//        Movie movie = listMovie.get(position);
//
//        Glide.with(holder.itemView.getContext())
//                .load(movie.getPhoto())
//                .apply(new RequestOptions().override(100, 150))
//                .into(holder.imgPhoto);
//
//        holder.tvName.setText(movie.getName());
//        holder.tvDescription.setText(movie.getDescription());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
//            }
//        });

        holder.bind(listMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDescription;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.img_movie_photo);
            tvName = itemView.findViewById(R.id.txt_movie_name);
            tvDescription = itemView.findViewById(R.id.txt_movie_description);
        }

        void bind(Movie movie) {
            tvName.setText(movie.getName());
            tvDescription.setText(movie.getDescription());
            Glide.with(context).load(movie.getPosterPath()).into(imgPhoto);
        }
    }
}
