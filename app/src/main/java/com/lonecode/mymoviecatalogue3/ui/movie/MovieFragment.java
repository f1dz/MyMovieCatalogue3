package com.lonecode.mymoviecatalogue3.ui.movie;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lonecode.mymoviecatalogue3.ListMovieAdapter;
import com.lonecode.mymoviecatalogue3.Movie;
import com.lonecode.mymoviecatalogue3.R;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private View root;
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();
    private TypedArray dataPhotoMovie;
    private String[] dataNameMovie;
    private String[] dataDescriptionMovie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieViewModel =
                ViewModelProviders.of(this).get(MovieViewModel.class);

        root = inflater.inflate(R.layout.fragment_movie, container, false);

        rvMovies = root.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        //        final TextView textView = root.findViewById(R.id.text_movie);

        movieViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        prepareData();
        list.addAll(addItem());
        showRecyclerList();

        return root;
    }

    private void prepareData() {
        dataNameMovie = getResources().getStringArray(R.array.data_name_movie);
        dataDescriptionMovie = getResources().getStringArray(R.array.data_description_movie);
    }

    private ArrayList<Movie> addItem() {
        ArrayList<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < dataNameMovie.length; i++) {
            Movie movie = new Movie();

            movie.setName(dataNameMovie[i]);
            movie.setDescription(dataDescriptionMovie[i]);

            movieList.add(movie);
        }

        return movieList;
    }

    private void showRecyclerList() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
        rvMovies.setAdapter(listMovieAdapter);

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {

            }
        });
    }
}