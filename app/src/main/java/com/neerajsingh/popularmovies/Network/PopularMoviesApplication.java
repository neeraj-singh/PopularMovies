package com.neerajsingh.popularmovies.Network;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neeraj.singh on 28/04/16.
 */
public class PopularMoviesApplication extends Application {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static Retrofit retrofit;
    public static BaseRequestInterface baseRequestInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeRetrofit(BASE_URL);
    }

    public static void initializeRetrofit(String BASE_URL) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseRequestInterface =
                PopularMoviesApplication.getRetrofit().create(BaseRequestInterface.class);
    }


    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static BaseRequestInterface getBaseRequestInterface() {
        return baseRequestInterface;
    }
}
