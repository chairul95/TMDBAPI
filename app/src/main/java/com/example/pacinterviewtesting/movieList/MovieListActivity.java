package com.example.pacinterviewtesting.movieList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pacinterviewtesting.movieDetail.MovieDetailsActivity;
import com.example.pacinterviewtesting.R;
import com.example.pacinterviewtesting.adapter.MovieListAdapterRv;
import com.example.pacinterviewtesting.model.movieList.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity implements MovieList.View, MovieItemClickListener {

    private static final String TAG = "Check MainActivity";
    private MovieListPresenter movieListPresenter;
    private RecyclerView rvMovieList;
    private ArrayList<Result> dataMovieList;
    private MovieListAdapterRv rvAdapter;
    private ProgressBar pbLoading;
    private TextView txtPage;
    private ImageView ivNiext;
    private ImageView ivPrev;

    private int pager = 1;

    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        initUI();
        listener();

        movieListPresenter = new MovieListPresenter(this);

        movieListPresenter.requestData();
    }

    private void listener() {
        ivPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pager<=1){
                    Log.i(TAG,String.valueOf(pager)+"B");
                    return;
                }
                if (!loading ) {
                    pager = pager-1;
                    dataMovieList.clear();
                    movieListPresenter.getMoreData(pager);
                    Log.i(TAG,String.valueOf(pager));
                    loading = true;
                }
                Log.i(TAG,"out");
            }
        });

        ivNiext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pager>=500){
                    Log.i(TAG,String.valueOf(pager)+"B");
                    return;
                }
                if (!loading){
                    pager = pager+1;
                    dataMovieList.clear();
                    movieListPresenter.getMoreData(pager);
                    Log.i(TAG,String.valueOf(pager));
                    loading = true;
                }
                Log.i(TAG,"out");
            }
        });
    }

    private void initUI() {
        rvMovieList = findViewById(R.id.rv_fixlist);
        dataMovieList = new ArrayList<>();
        pbLoading = findViewById(R.id.pb_loading);
        rvAdapter = new MovieListAdapterRv(MovieListActivity.this, dataMovieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieListActivity.this);
        rvMovieList.setLayoutManager(layoutManager);
        rvMovieList.setAdapter(rvAdapter);
        txtPage = findViewById(R.id.txtPage);
        ivNiext = findViewById(R.id.ivNext);
        ivPrev = findViewById(R.id.ivPrev);
    }


    @Override
    public void showProgress() {

        pbLoading.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        pbLoading.setVisibility(View.GONE);
        loading = false;
        txtPage.setText(String.valueOf(pager));
    }

    @Override
    public void setData(List<Result> DataMovieList) {
        dataMovieList.addAll(DataMovieList);
        rvAdapter.notifyDataSetChanged();


    }

    @Override
    public void onResponseFailure(Throwable t) {
        Log.e(TAG, t.getMessage());
        Toast.makeText(this,"Sorry for this problem",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieListPresenter.onDestroy();
    }

    @Override
    public void onMovieItemClick(int position) {
//        Toast.makeText(this,"Click"+String.valueOf(position),Toast.LENGTH_SHORT).show();
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this, MovieDetailsActivity.class);
        detailIntent.putExtra("movie_id", dataMovieList.get(position).getId());
        startActivity(detailIntent);
    }
}