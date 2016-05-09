package com.neerajsingh.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neerajsingh.popularmovies.Data.Movie;
import com.neerajsingh.popularmovies.Network.PopularMoviesApplication;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by neeraj.singh on 05/05/16.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String MOVIE_DETAIL = "MOVIE_DETAIL";
    Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();

        if(extra !=null && extra.containsKey(MOVIE_DETAIL) ){
            movie = (Movie)extra.getSerializable(MOVIE_DETAIL);
        }
        String title = movie.getTitle();
        try {
            title = getMovieTitle();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((TextView)findViewById(R.id.movie_title)).setText(title);
        ((TextView)findViewById(R.id.release_date)).setText(movie.getReleaseDate());
        ((TextView)findViewById(R.id.rating)).setText(movie.getVoteAverage()+"/10");
        ((TextView)findViewById(R.id.movie_description)).setText(movie.getOverview());
        TextView textView = ((TextView)findViewById(R.id.vote_count));
        if(textView!=null) {
            textView.setText(String.format(getResources().getString(R.string.vote_count),movie.getVoteCount()));
        }

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

        Picasso.with(this).load(
                PopularMoviesApplication.BASE_IMAGE_URL+PopularMoviesApplication.POSTER_SIZE+"/"+movie.getPosterPath())
                .into((ImageView) findViewById(R.id.imageView));

    }

    private String getMovieTitle() throws ParseException {
        String releaseDate = movie.getReleaseDate();
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date date = format.parse(releaseDate);
        String name = movie.getOriginalTitle()+"("+(date.getYear()+1900)+")";

        return name;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
