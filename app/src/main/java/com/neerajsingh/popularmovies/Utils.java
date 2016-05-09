package com.neerajsingh.popularmovies;

import com.neerajsingh.popularmovies.Data.Genres;
import com.neerajsingh.popularmovies.Data.GenresList;
import com.neerajsingh.popularmovies.Network.PopularMoviesApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeraj.singh on 06/05/16.
 */
public class Utils {
    public static Map<Integer,String> genresMap;

    public static void getGenres(){
        Call<GenresList> callHandler =  PopularMoviesApplication.getBaseRequestInterface().getGenres();
        callHandler.enqueue(new Callback<GenresList>() {
            @Override
            public void onResponse(Call<GenresList> call, Response<GenresList> response) {
                ArrayList<Genres>  genresArrayList = (ArrayList<Genres>) response.body().getGenresList();
                genresMap = new HashMap<>();
                if(genresArrayList!=null){
                    for(Genres genres : genresArrayList) {
                        genresMap.put(Integer.valueOf(genres.getId()), genres.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenresList> call, Throwable t) {

            }
        });
    }

    public static boolean isNullOrEmpty(String string){
        return string == null || string.length() ==0 || string.equalsIgnoreCase("null");
    }
}
