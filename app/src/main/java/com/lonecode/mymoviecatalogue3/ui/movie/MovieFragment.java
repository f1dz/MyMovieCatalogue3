package com.lonecode.mymoviecatalogue3.ui.movie;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListMovieAdapter listMovieAdapter;
    private ProgressBar progressBar;

    private MovieViewModel movieViewModel;
    private View root;
    
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();
    private TypedArray dataPhotoMovie;
    private String[] dataNameMovie;
    private String[] dataDescriptionMovie;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_movie, container, false);

        rvMovies = root.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        listMovieAdapter = new ListMovieAdapter();
        listMovieAdapter.notifyDataSetChanged();
        rvMovies.setAdapter(listMovieAdapter);

        movieViewModel =
                ViewModelProviders.of(this).get(MovieViewModel.class);

        movieViewModel.setMovie();

        movieViewModel.getMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                if (movies != null) {
                    listMovieAdapter.setData(movies);
                }
            }
        });

        listMovieAdapter.setOnItemClickCallback(new ListMovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });

        return root;
    }

    private void showSelectedMovie(Movie movie) {
        Toast.makeText(getActivity(), "Movie: " + movie.getName() + "\nDescription: " + movie.getDescription(), Toast.LENGTH_SHORT).show();
    }
}