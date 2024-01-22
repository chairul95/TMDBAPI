package com.example.pacinterviewtesting.movieList;


import com.example.pacinterviewtesting.model.movieList.Result;

import java.util.List;

public interface MovieList {

    interface Model {
        interface OnFinishedListener{
            void onFinished(List<Result> DataMovieList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pager);
    }

    interface View {

        void showProgress();
        void hideProgress();
        void setData(List<Result> DataMovieList);
        void onResponseFailure(Throwable t);

    }

    interface Presenter{

        void onDestroy();
        void getMoreData(int pager);
        void requestData();
    }
}
