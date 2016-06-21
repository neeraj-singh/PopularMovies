package com.neerajsingh.popularmovies.Network;

import com.neerajsingh.popularmovies.Config;
import com.neerajsingh.popularmovies.Data.GenresList;
import com.neerajsingh.popularmovies.Data.MovieList;
import com.neerajsingh.popularmovies.Data.MovieReviews;
import com.neerajsingh.popularmovies.Data.MovieTrailer;
import com.neerajsingh.popularmovies.Data.MovieTrailerList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by neeraj.singh on 28/04/16.
 */
public interface BaseRequestInterface {
    @Headers("Accept: application/json")
    @GET("movie/popular?api_key="+ Config.API_KEY)
    Call<MovieList> getPopularMovies();

    @Headers("Accept: application/json")
    @GET("movie/popular?api_key="+ Config.API_KEY)
    Call<MovieList> getPopularMovies(@Query("page") int page);

    @Headers("Accept: application/json")
    @GET("movie/top_rated?api_key="+ Config.API_KEY)
    Call<MovieList> getTopRated();

    @Headers("Accept: application/json")
    @GET("movie/top_rated?api_key="+ Config.API_KEY)
    Call<MovieList> getTopRated(@Query("page") int page);

    @Headers("Accept: application/json")
    @GET("genre/movie/list?api_key="+ Config.API_KEY)
    Call<GenresList> getGenres();

    @Headers("Accept: application/json")
    @GET("movie/{id}/videos?api_key="+ Config.API_KEY)
    Call<MovieTrailerList> getTrailers(@Path("id") int id);

    @Headers("Accept: application/json")
    @GET("movie/{id}/reviews?api_key="+ Config.API_KEY)
    Call<MovieReviews> getReviews(@Path("id") int id);

}
