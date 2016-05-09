package com.neerajsingh.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.neerajsingh.popularmovies.Data.Movie;
import com.neerajsingh.popularmovies.Network.PopularMoviesApplication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neeraj.singh on 28/04/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    private ArrayList<Movie> list;
    Context context;
    public MoviesAdapter(List<Movie> list){
        this.list = (ArrayList)list;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        Glide.with(context).load(PopularMoviesApplication.BASE_IMAGE_URL+PopularMoviesApplication.POSTER_SIZE+list.get(position).getPosterPath()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra(DetailActivity.MOVIE_DETAIL,list.get(position));
                ((Activity)context).startActivityForResult(intent,0);
            }
        });
        Picasso.with(context).load(PopularMoviesApplication.BASE_IMAGE_URL+PopularMoviesApplication.POSTER_SIZE+"/"+list.get(position).getPosterPath()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.poster_image);
        }
    }



}
