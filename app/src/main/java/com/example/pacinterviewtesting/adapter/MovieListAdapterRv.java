package com.example.pacinterviewtesting.adapter;

import android.annotation.SuppressLint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pacinterviewtesting.R;
import com.example.pacinterviewtesting.model.movieList.Result;
import com.example.pacinterviewtesting.movieList.MovieListActivity;
import com.example.pacinterviewtesting.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MovieListAdapterRv extends RecyclerView.Adapter<MovieListAdapterRv.CustomViewHolder> {

    ApiClient apiClient;
    private ArrayList<Result> dataList;
    private List<Result> dataListFull;
    private Context context;
    private MovieListActivity movieListActivity;


    public MovieListAdapterRv(MovieListActivity context, ArrayList<Result> dataList) {

        if (dataList == null) {
            return;
        }
        this.movieListActivity = context;
        this.context = context;
        this.dataList = dataList;
        dataListFull = new ArrayList<>(dataList);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView txtTitil,txtPrice;
        private ImageView ivRowData;
        private LinearLayout lnRowDataAct;
        public TextView tvMovieTitle;

        public TextView tvMovieRatings;

        public TextView tvReleaseDate;

        public ImageView ivMovieThumb;


        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            tvMovieRatings = itemView.findViewById(R.id.tv_movie_ratings);
            ivMovieThumb = itemView.findViewById(R.id.iv_movie_thumb);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(apiClient.IMAGE_BASE_URL+dataList.get(position).getPosterPath()).into(holder.ivMovieThumb);

        Result dataMovie = dataList.get(position);

        holder.tvMovieTitle.setText(dataMovie.getTitle());
        holder.tvMovieRatings.setText(String.valueOf(dataMovie.getVoteAverage()));
        holder.tvReleaseDate.setText(dataMovie.getReleaseDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieListActivity.onMovieItemClick(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return (dataList == null) ? 0 : dataList.size();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Result item, int position) {
        dataList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<Result> getData() {
        return dataList;
    }

}
