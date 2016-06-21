package com.neerajsingh.popularmovies.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by neeraj.singh on 22/06/16.
 */
public class MovieTrailerList {
    @SerializedName ("results")
    List<MovieTrailer> trailers ;

    public List<MovieTrailer> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<MovieTrailer> trailers) {
        this.trailers = trailers;
    }
}
