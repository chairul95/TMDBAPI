/**
 * @file MovieDetailsPresenter.java
 * @brief This is the presenter class for movie details functionality, it will act as
 * an intermediate between views and model
 * @author Shrikant
 * @date 15/04/2018
 */

package com.example.pacinterviewtesting.movieDetail;


import com.example.pacinterviewtesting.model.detailMovie.DetailMovie;
import com.example.pacinterviewtesting.model.movieList.Result;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter, MovieDetailsContract.Model.OnFinishedListener {

    private MovieDetailsContract.View movieDetailView;
    private MovieDetailsContract.Model movieDetailsModel;

    public MovieDetailsPresenter(MovieDetailsContract.View movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailsModel = new MovieDetailsModel();
    }

    @Override
    public void onDestroy() {

        movieDetailView = null;
    }

    @Override
    public void requestMovieData(int movieId) {

        if(movieDetailView != null){
            movieDetailView.showProgress();
        }
        movieDetailsModel.getMovieDetails(this, movieId);
    }


    @Override
    public void onFinished(DetailMovie result) {
        if(movieDetailView != null){
            movieDetailView.hideProgress();
        }
        movieDetailView.setDataToViews(result);
    }

    @Override
    public void onFailure(Throwable t) {
        if(movieDetailView != null){
            movieDetailView.hideProgress();
        }
        movieDetailView.onResponseFailure(t);
    }
}
