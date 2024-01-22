package com.example.pacinterviewtesting.movieList;

import android.os.Build;
import android.util.Log;


import com.example.pacinterviewtesting.BuildConfig;
import com.example.pacinterviewtesting.model.movieList.ResponseMovieList;
import com.example.pacinterviewtesting.model.movieList.Result;
import com.example.pacinterviewtesting.network.ApiClient;
import com.example.pacinterviewtesting.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieList.Model {

    private final String TAG = "check MovieListModel";
    @Override
    public void getMovieList(OnFinishedListener onFinishedListener, int pager) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseMovieList> call = apiInterface.getMovieList(BuildConfig.AppKey,pager);
        call.enqueue(new Callback<ResponseMovieList>() {
            @Override
            public void onResponse(Call<ResponseMovieList> call, Response<ResponseMovieList> response) {
                List<Result> movieList = response.body().getResults();
                Log.d(TAG, "Data Size: " + movieList.size());

                onFinishedListener.onFinished(movieList);

            }

            @Override
            public void onFailure(Call<ResponseMovieList> call, Throwable t) {
                Log.e(TAG,t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
