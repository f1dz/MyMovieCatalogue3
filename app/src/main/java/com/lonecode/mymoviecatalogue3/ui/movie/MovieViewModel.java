package com.lonecode.mymoviecatalogue3.ui.movie;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lonecode.mymoviecatalogue3.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "e4621b68dcd1fa1de4a66cfd0664dc28";
    private MutableLiveData<ArrayList<Movie>> list = new MutableLiveData<>();

//    public MovieViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is Movie fragment");
//    }

//    public LiveData<String> getText() {
//        return mText;
//    }

    void setMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listMovies = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("ResponseBody", result);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray listMv = responseObject.getJSONArray("results");
                    for (int i = 0; i < listMv.length(); i++) {
                        JSONObject movie = listMv.getJSONObject(i);
                        Movie movieItems = new Movie();
                        movieItems.setName(movie.getString("title"));
                        movieItems.setDescription(movie.getString("overview"));
                        listMovies.add(movieItems);
                    }
                    list.postValue(listMovies);

                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<ArrayList<Movie>> getMovies() { return list; }
}