package com.example.pacinterviewtesting.network;


import com.example.pacinterviewtesting.model.detailMovie.DetailMovie;
import com.example.pacinterviewtesting.model.movieList.ResponseMovieList;
import com.example.pacinterviewtesting.model.movieList.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/popular")
    Call<ResponseMovieList> getMovieList(@Query("api_key") String api_key, @Query("page") int page);
    @GET("movie/{movie_id}")
    Call<DetailMovie> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("append_to_response") String credits);

}
