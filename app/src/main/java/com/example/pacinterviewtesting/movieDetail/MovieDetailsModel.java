package com.example.pacinterviewtesting.movieDetail;

import static com.example.pacinterviewtesting.Global.API_KEY;

import android.util.Log;

import com.example.pacinterviewtesting.model.detailMovie.DetailMovie;
import com.example.pacinterviewtesting.model.movieList.Result;
import com.example.pacinterviewtesting.network.ApiClient;
import com.example.pacinterviewtesting.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetailsModel implements MovieDetailsContract.Model {

    private final String TAG = "Check MovieDetailsModel";

    @Override
    public void getMovieDetails(final OnFinishedListener onFinishedListener, int movieId) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DetailMovie> call = apiService.getMovieDetails(movieId, API_KEY, "credits");
        call.enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                DetailMovie result = response.body();
                Log.d(TAG, "Movie data received: " + result.toString());
                onFinishedListener.onFinished(result);
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }
}
