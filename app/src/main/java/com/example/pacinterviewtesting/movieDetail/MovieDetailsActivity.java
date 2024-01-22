package com.example.pacinterviewtesting.movieDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pacinterviewtesting.R;
import com.example.pacinterviewtesting.adapter.CastAdapter;
import com.example.pacinterviewtesting.adapter.MovieListAdapterRv;
import com.example.pacinterviewtesting.model.detailMovie.Cast;
import com.example.pacinterviewtesting.model.detailMovie.DetailMovie;
import com.example.pacinterviewtesting.model.movieList.Result;
import com.example.pacinterviewtesting.movieList.MovieListActivity;
import com.example.pacinterviewtesting.network.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsContract.View {

    private ImageView ivBackdrop;
    private TextView tvMovieTitle;
    private TextView tvMovieReleaseDate;
    private TextView tvMovieRatings;
    private TextView tvOverview;
    private CastAdapter castAdapter;
    RecyclerView rvCast;
    private ArrayList<Cast> castList;

    private MovieDetailsPresenter movieDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        initUI();

        Intent mIntent = getIntent();
        int movieId = mIntent.getIntExtra("movie_id", 0);

        movieDetailsPresenter = new MovieDetailsPresenter(this);
        movieDetailsPresenter.requestMovieData(movieId);
    }

    private void initUI() {
        ivBackdrop = findViewById(R.id.iv_backdrop);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieReleaseDate = findViewById(R.id.tv_release_date);
        tvMovieRatings = findViewById(R.id.tv_movie_ratings);
        tvOverview = findViewById(R.id.tv_movie_overview);
        rvCast = findViewById(R.id.rv_cast);

        castList = new ArrayList<>();
        castAdapter = new CastAdapter(MovieDetailsActivity.this, castList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieDetailsActivity.this,LinearLayoutManager.HORIZONTAL, false);
        rvCast.setLayoutManager(layoutManager);
        rvCast.setAdapter(castAdapter);


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToViews(DetailMovie result) {
        if (result != null) {

            tvMovieTitle.setText(result.getTitle());
            tvMovieReleaseDate.setText(result.getReleaseDate());
            tvMovieRatings.setText(String.valueOf(result.getVoteCount()));
            tvOverview.setText(result.getOverview());

            Picasso.get().load(ApiClient.BACKDROP_BASE_URL + result.getBackdropPath()).into(ivBackdrop);

            castList.addAll(result.getCredits().getCast());
            castAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}