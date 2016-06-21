package com.neerajsingh.popularmovies;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.neerajsingh.popularmovies.Data.Movie;
import com.neerajsingh.popularmovies.Data.MovieList;
import com.neerajsingh.popularmovies.Network.PopularMoviesApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    public static final String ISPOPULAR = "ISPOPULAR";

    protected final int SPAN_2 = 2;
    protected final int SPAN_3 = 3;

    protected final String Loader_Message = "Please wait..";

    RecyclerView recyclerView;
    List<Movie> movieList;
    ProgressDialog progressDialog;
    MoviesAdapter moviesAdapter;
    boolean isPopular = true;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            isPopular =  savedInstanceState.getBoolean(ISPOPULAR);
        }
        originalView();
    }

    private void originalView( ) {
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.root_view);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, SPAN_2);
        }else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = new GridLayoutManager(this, SPAN_3);
        }
        recyclerView.setLayoutManager(layoutManager);
        getMovies(getMoviesHandler(isPopular));
    }

    private void getMovies(Call<MovieList> movieListCall) {
        if(progressDialog==null ) {
            progressDialog = ProgressDialog.show(this, null, Loader_Message);
            progressDialog.setCancelable(true);
        }

        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                progressDialog.dismiss();
                if(response!=null && response.isSuccessful()){
                    movieList = response.body().getPopularMovies();
                    moviesAdapter = new MoviesAdapter(movieList);
                    recyclerView.setAdapter(moviesAdapter);
                    moviesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                progressDialog.dismiss();
                setContentView(R.layout.no_net);
            }
        });
    }

    private Call<MovieList> getMoviesHandler(boolean isPopular) {
        if(isPopular) {
            return PopularMoviesApplication.getBaseRequestInterface().getPopularMovies();
        }else{
            return PopularMoviesApplication.getBaseRequestInterface().getTopRated();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.top_rated){
            if(isPopular) {
                isPopular = false;
                getMovies(getMoviesHandler(isPopular));
            }else{
                Toast.makeText(this,"Already showing top rated movies",Toast.LENGTH_SHORT).show();
            }
            return true;
        }else if(item.getItemId() == R.id.popular){
            if(!isPopular) {
                isPopular = true;
                getMovies(getMoviesHandler(isPopular));
            }else{
                Toast.makeText(this,"Already showing popular movies",Toast.LENGTH_SHORT).show();
            }
            return true;
        }else{
            return false;
        }
    }

    public void retryNetworkCall(View view){
        originalView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(ISPOPULAR,isPopular);
        super.onSaveInstanceState(outState);
    }
}
