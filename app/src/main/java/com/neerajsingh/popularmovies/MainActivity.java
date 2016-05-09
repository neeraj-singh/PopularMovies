package com.neerajsingh.popularmovies;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    RecyclerView recyclerView;
    List<Movie> movieList;
    ProgressDialog progressDialog;
    MoviesAdapter moviesAdapter;
    boolean isPopular = true;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        originalView();
    }

    private void originalView() {
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.root_view);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovies(getMoviesHandler(isPopular));
    }

    private void getMovies(Call<MovieList> movieListCall) {
        progressDialog = ProgressDialog.show(this,null,"Please wait..");
        progressDialog.setCancelable(true);
        movieListCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                Log.e("Response recieved",response.isSuccessful()+" "+response.body());
                if(response!=null && response.isSuccessful()){
                    progressDialog.dismiss();
                    movieList = response.body().getPopularMovies();
                    moviesAdapter = new MoviesAdapter(movieList);
                    recyclerView.setAdapter(moviesAdapter);
                    moviesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.e("Response recieved","call failed");
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,"Network call faield",Toast.LENGTH_LONG).show();
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
        getMovies(getMoviesHandler(isPopular));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        if(moviesAdapter!=null) {
            recyclerView.setAdapter(moviesAdapter);
            moviesAdapter.notifyDataSetChanged();
        }
    }

}
