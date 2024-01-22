package com.example.pacinterviewtesting.movieList;


import com.example.pacinterviewtesting.model.movieList.Result;

import java.util.List;

public class MovieListPresenter implements MovieList.Presenter, MovieList.Model.OnFinishedListener{

    private MovieList.View movieListView;
    private MovieList.Model movieListModel;

    public MovieListPresenter(MovieList.View MovieListView){
        this.movieListView = MovieListView;
        movieListModel = new MovieListModel();
    }

    @Override
    public void onFinished(List<Result> DataMovieList) {
        movieListView.setData(DataMovieList);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void getMoreData(int pager) {
        if (movieListView != null){
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this,pager);
    }

    @Override
    public void requestData() {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this, 1);
    }
}
