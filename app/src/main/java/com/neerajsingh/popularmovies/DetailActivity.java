package com.neerajsingh.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.neerajsingh.popularmovies.Data.Movie;
import com.neerajsingh.popularmovies.Data.MovieList;
import com.neerajsingh.popularmovies.Data.MovieReviews;
import com.neerajsingh.popularmovies.Data.MovieTrailer;
import com.neerajsingh.popularmovies.Data.MovieTrailerList;
import com.neerajsingh.popularmovies.Network.PopularMoviesApplication;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeraj.singh on 05/05/16.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_DETAIL = "MOVIE_DETAIL";
    public static final int TIME_ADJUSTER = 1900;
    private Movie movie;
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvRating;
    private TextView tvMovieDescription;
    private TextView tvVoteCount;
    private LinearLayout parentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();

        if(extra !=null && extra.containsKey(MOVIE_DETAIL) ){
            this.movie = extra.getParcelable(MOVIE_DETAIL);
        }
        String title = movie.getTitle();
        try {
            title = getMovieTitle();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTitle = (TextView) findViewById(R.id.movie_title);
        tvReleaseDate = (TextView) findViewById(R.id.release_date);
        tvRating = (TextView) findViewById(R.id.rating);
        tvMovieDescription = (TextView) findViewById(R.id.movie_description);
        tvVoteCount = (TextView) findViewById(R.id.vote_count);
        parentView = (LinearLayout)findViewById(R.id.parentView);

        populateData(title);


    }

    private void populateData(String title) {
        tvTitle.setText(title);
        tvReleaseDate.setText(movie.getReleaseDate());
        tvRating.setText(movie.getVoteAverage()+"/10");
        tvMovieDescription.setText(movie.getOverview());
        tvVoteCount.setText(String.format(getResources().getString(R.string.vote_count),movie.getVoteCount()));

        StringBuilder genre = new StringBuilder();
        for(Integer gen : movie.getGenreIds()){
            if(Utils.genresMap.containsKey(gen)){
                genre.append(" | "+Utils.genresMap.get(gen));
            }
        }
        TextView genreTextView = (TextView) findViewById(R.id.genre);
        if(genre.length()>0) {
            genreTextView.setText(genre.toString().substring(3));
        }else{
            genreTextView.setVisibility(View.GONE);
        }

        if(movie!=null) {
            getTrailers(movie.getId());
            getReviews(movie.getId());


            Picasso.with(this).load(
                    PopularMoviesApplication.BASE_IMAGE_URL + PopularMoviesApplication.POSTER_SIZE + "/" + movie.getPosterPath())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_error)
                    .into((ImageView) findViewById(R.id.imageView));
        }
    }

    private void getReviews(int id) {
        Call<MovieReviews> reviewCall = PopularMoviesApplication.baseRequestInterface.getReviews(id);
        reviewCall.enqueue(new Callback<MovieReviews>() {
            @Override
            public void onResponse(Call<MovieReviews> call, Response<MovieReviews> response) {
                if(response!=null) {
                    showReview(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieReviews> call, Throwable t) {

            }
        });
    }

    private void showReview(MovieReviews reviews) {

    }

    private void getTrailers(int id) {
        Call<MovieTrailerList> trailerCall = PopularMoviesApplication.baseRequestInterface.getTrailers(id);
        try {
            trailerCall.enqueue(new Callback<MovieTrailerList>(){
                @Override
                public void onResponse(Call<MovieTrailerList> call, Response<MovieTrailerList> response) {
                    if(response!=null){
                        showTrailers((ArrayList<MovieTrailer>) response.body().getTrailers());
                    }
                }

                @Override
                public void onFailure(Call<MovieTrailerList> call, Throwable t) {
                    Log.e("Test","Failed call");
                }


            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showTrailers(ArrayList<MovieTrailer> body) {
        if(body!=null){

            for(final MovieTrailer trailer : body) {

                        parentView.addView(getTrailerView(trailer));

            }
        }
    }

    private View getTrailerView(MovieTrailer trailer) {
        View view = LayoutInflater.from(this).inflate(R.layout.trailer_row,null);
        ((TextView)view.findViewById(R.id.trailer_title)).setText(trailer.getName());
        final String videoUrl ;
        if(trailer.getSite().equalsIgnoreCase("Youtube")){
            videoUrl = "https://www.youtube.com/watch?v="+trailer.getKey();
        }else{
            videoUrl = trailer.getSite()+"/"+trailer.getKey();
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(videoUrl));
                startActivity(intent);
            }
        });
        return view;
    }

    private String getMovieTitle() throws ParseException {
        String releaseDate = movie.getReleaseDate();
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date = format.parse(releaseDate);
        String name = movie.getOriginalTitle()+" ("+(date.getYear()+ TIME_ADJUSTER)+")";

        return name;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
